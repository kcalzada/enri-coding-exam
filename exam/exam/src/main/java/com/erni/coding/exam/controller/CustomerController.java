package com.erni.coding.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erni.coding.exam.entity.CustomerBillEntity;
import com.erni.coding.exam.entity.CustomerEntity;
import com.erni.coding.exam.entity.ParkingSpotsEntity;
import com.erni.coding.exam.service.CustomerService;
import com.erni.coding.exam.service.ParkingSpotsService;

@RestController
@RequestMapping("parking")
public class CustomerController {
	
	@Autowired
	ParkingSpotsService parkingSpotsService;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping(value="all-customers")
	public List<CustomerEntity> fetchCustomers() {
		
		return customerService.fetchAllCustomer();
	}
	
	@GetMapping(value="current-customers")
	public List<CustomerEntity> fetchCurrentCustomers() {
		
		return customerService.fetchCurrentCustomers();
	}
	
	@GetMapping(value="parking-spots")
	public List<ParkingSpotsEntity> fetchParkingSpots() {
		
		return parkingSpotsService.fetchAllParkingSpots();
	}
	
	@PostMapping(value="save-customer")
	public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customer) {
		
		return customerService.saveCustomerVehicle(customer);
	}
	
	@PutMapping(value="customer-bill")
	public ResponseEntity<CustomerBillEntity> customerBill(@RequestParam String plateNumber) {
		
		return customerService.customerTimeOut(plateNumber);
	}
}