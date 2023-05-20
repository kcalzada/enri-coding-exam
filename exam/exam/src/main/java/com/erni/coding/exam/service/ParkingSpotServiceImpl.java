package com.erni.coding.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erni.coding.exam.entity.ParkingSpotsEntity;
import com.erni.coding.exam.repository.ParkingSpotsRepo;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotsService {
	
	@Autowired
	ParkingSpotsRepo parkingSpotsRepo;

	@Override
	public List<ParkingSpotsEntity> fetchAllParkingSpots() {
		
		return parkingSpotsRepo.findAll();
	}
}