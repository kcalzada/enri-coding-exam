package com.erni.coding.exam.service;

import java.util.List;

import com.erni.coding.exam.entity.ParkingSpotsEntity;

public interface ParkingSpotsService {
	
	public List<ParkingSpotsEntity> fetchAllParkingSpots();
}