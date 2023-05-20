package com.erni.coding.exam.service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.erni.coding.exam.entity.CustomerBillEntity;
import com.erni.coding.exam.entity.CustomerEntity;
import com.erni.coding.exam.entity.ParkingSpotsEntity;
import com.erni.coding.exam.repository.CustomerRepo;
import com.erni.coding.exam.repository.ParkingSpotsRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepo CustomerRepo;
	
	@Autowired
	ParkingSpotsRepo parkingSpotsRepo;
	
	@Override
	public List<CustomerEntity> fetchAllCustomer() {
		
		return CustomerRepo.findAll();
	}
	
	@Override
	public List<CustomerEntity> fetchCurrentCustomers() {
		
		return CustomerRepo.fetchAllCurrentCustomers();
	}

	@Override
	public ResponseEntity<CustomerEntity> saveCustomerVehicle(CustomerEntity customer) {
		
		if(plateNumberCheck(customer.getPlateNumber())) {
			return new ResponseEntity<CustomerEntity>(HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity<CustomerEntity> response = null;
		
		if(customerNullChecker(customer) == false) {
			switch(customer.getvType()) {
				case "S": 
					response = saveSmallVehicleCustomer(customer);
					break;
				case "M": 
					response = saveMediumVehicleCustomer(customer);
					break;
				case "L": 
					response = saveLargeVehicleCustomer(customer);
					break;
				default:
					return new ResponseEntity<CustomerEntity>(HttpStatus.BAD_REQUEST);
			}
		}
		else {
			response = new ResponseEntity<CustomerEntity>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	
	@Override
	public ResponseEntity<CustomerBillEntity> customerTimeOut(String plateNumber) {
		
		if(plateNumberCheck(plateNumber)) {
			CustomerEntity customer = CustomerRepo.fetchCustomerByPlateNymber(plateNumber);
			ParkingSpotsEntity parkingSpot = customer.getParkingSpot();
			
			customer.setTimeOut(getCurrentTimeAndDate());
			
			CustomerBillEntity customerBill = new CustomerBillEntity();
			customerBill.setId(customer.getId());
			customerBill.setBill(calculateBill(customer));
			customerBill.setPlateNumber(customer.getPlateNumber());
			customerBill.setTimeIn(customer.getTimeIn());
			customerBill.setTimeOut(customer.getTimeOut());
			
			parkingSpot.setOccupied(false);
			
			CustomerRepo.save(customer);
			parkingSpotsRepo.save(parkingSpot);
			
			return new ResponseEntity<CustomerBillEntity>(customerBill,HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<CustomerBillEntity>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	private ResponseEntity<CustomerEntity> saveSmallVehicleCustomer(CustomerEntity customer) {
		
		ParkingSpotsEntity parkingSpot = null;
		
		switch(customer.getEntrance()) {
			case 1:
				 parkingSpot = parkingSpotsRepo.fetchSmallParkingSpotNearestToFirstEntrance();
				break;
			case 2:
				parkingSpot = parkingSpotsRepo.fetchSmallParkingSpotNearestToSecondEntrance();
				break;
			case 3:
				parkingSpot = parkingSpotsRepo.fetchSmallParkingSpotNearestToThirdEntrance();
				break;
			default:
				return new ResponseEntity<CustomerEntity>(HttpStatus.BAD_REQUEST);
		}
		
		if(parkingSpot == null) {
			return new ResponseEntity<CustomerEntity>(HttpStatus.CONFLICT); 
		}
		
		return saveCustomer(parkingSpot,customer);
	}
	
	private ResponseEntity<CustomerEntity> saveMediumVehicleCustomer(CustomerEntity customer) {
		
		ParkingSpotsEntity parkingSpot = null;
		
		switch(customer.getEntrance()) {
			case 1:
				 parkingSpot = parkingSpotsRepo.fetchMediumParkingSpotNearestToFirstEntrance();
				break;
			case 2:
				parkingSpot = parkingSpotsRepo.fetchMediumParkingSpotNearestToSecondEntrance();
				break;
			case 3:
				parkingSpot = parkingSpotsRepo.fetchMediumParkingSpotNearestToThirdEntrance();
				break;
			default:
				return new ResponseEntity<CustomerEntity>(HttpStatus.BAD_REQUEST);
		}
		
		return saveCustomer(parkingSpot,customer);
	}
	
	private ResponseEntity<CustomerEntity> saveLargeVehicleCustomer(CustomerEntity customer) {
		
		ParkingSpotsEntity parkingSpot = null;
		
		switch(customer.getEntrance()) {
			case 1:
				 parkingSpot = parkingSpotsRepo.fetchLargeParkingSpotNearestToFirstEntrance();
				break;
			case 2:
				parkingSpot = parkingSpotsRepo.fetchLargeParkingSpotNearestToSecondEntrance();
				break;
			case 3:
				parkingSpot = parkingSpotsRepo.fetchLargeParkingSpotNearestToThirdEntrance();
				break;
			default:
				return new ResponseEntity<CustomerEntity>(HttpStatus.BAD_REQUEST);
		}
		
		return saveCustomer(parkingSpot,customer);
	}
	
	private ResponseEntity<CustomerEntity> saveCustomer(ParkingSpotsEntity parkingSpot, CustomerEntity customer){
		
		if(parkingSpot == null) {
			return new ResponseEntity<CustomerEntity>(HttpStatus.CONFLICT); 
		}
		
		parkingSpot.setOccupied(true);
		
		customer.setParkingSpot(parkingSpot);
		customer.setTimeIn(getCurrentTimeAndDate());
		
		parkingSpotsRepo.save(parkingSpot);
		
		
		return new ResponseEntity<CustomerEntity>(CustomerRepo.save(customer), HttpStatus.OK); 
	}
	
	private Date getCurrentTimeAndDate() {
		
		LocalDateTime now = LocalDateTime.now();
		Date dueDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		
		return dueDate;
	}
	
	private boolean plateNumberCheck(String plateNumber) {
		
		CustomerEntity customerCheck = CustomerRepo.fetchCustomerByPlateNymber(plateNumber);
		
		if(customerCheck != null) {
			System.out.println("ID: "+customerCheck.getId());
			return true;
		}
		
		return false;
	}
	
	private boolean customerNullChecker(CustomerEntity customer) {
		if(customer.getvType() == null || customer.getPlateNumber() == null || customer.getEntrance() == 0) {
			return true;
		}
		
		return false;
	}
	
	private int calculateBill(CustomerEntity customer) {
		
		int bill = 40;
		
		Date now = getCurrentTimeAndDate();
		
		long diffInMillies = Math.abs(now.getTime() - customer.getTimeIn().getTime());
		long diffInHours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		if(diffInHours <= 3) {
			return bill;
		}
		else {
			
			if(diffInHours >= 24) {
				long multiplier = diffInHours / 24;
				bill = (int) (bill + (multiplier * 5000));
			}
			
			int billPerHour = 0;
			
			switch(customer.getvType()) {
				case "S": 
					billPerHour = 20;
					break;	
				case "M": 
					billPerHour = 60;
					break;
				case "L": 
					billPerHour = 100;
					break;	
			}
			
			bill = (int) (bill + (billPerHour * ((diffInHours-3) % 24)));
		}
		
		return bill;
	}
}