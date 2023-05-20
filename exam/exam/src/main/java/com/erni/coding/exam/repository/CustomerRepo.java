package com.erni.coding.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erni.coding.exam.entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{
	
	@Query(value = "SELECT * FROM parking_customer WHERE parking_customer.plate_number = :plateNumber AND parking_customer.time_out IS NULL LIMIT 1", nativeQuery = true)
	public CustomerEntity fetchCustomerByPlateNymber(@Param("plateNumber") String plateNumber);
	
	@Query(value = "SELECT * FROM parking_customer WHERE parking_customer.time_out IS NULL", nativeQuery = true)
	public List<CustomerEntity> fetchAllCurrentCustomers();
}