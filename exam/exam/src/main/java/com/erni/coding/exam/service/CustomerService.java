package com.erni.coding.exam.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.erni.coding.exam.entity.CustomerBillEntity;
import com.erni.coding.exam.entity.CustomerEntity;

public interface CustomerService {
	
	public ResponseEntity<List<CustomerEntity>> fetchAllCustomer();
	
	public ResponseEntity<List<CustomerEntity>> fetchCurrentCustomers();
	
	public ResponseEntity<CustomerEntity> saveCustomerVehicle(CustomerEntity Customer);
	
	public ResponseEntity<CustomerBillEntity> customerTimeOut(String plateNumber);
}