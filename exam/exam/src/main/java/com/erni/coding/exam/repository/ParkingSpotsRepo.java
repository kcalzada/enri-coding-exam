package com.erni.coding.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erni.coding.exam.entity.ParkingSpotsEntity;

public interface ParkingSpotsRepo extends JpaRepository<ParkingSpotsEntity, Integer>{
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"S\" OR parking_spots.v_type = \"M\" OR parking_spots.v_type = \"L\" ORDER BY parking_spots.v_type DESC,parking_spots.first_entrance_distance,parking_spots.second_entrance_distance,parking_spots.third_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchSmallParkingSpotNearestToFirstEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"S\" OR parking_spots.v_type = \"M\" OR parking_spots.v_type = \"L\" ORDER BY parking_spots.v_type DESC,parking_spots.second_entrance_distance,parking_spots.first_entrance_distance,parking_spots.third_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchSmallParkingSpotNearestToSecondEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"S\" OR parking_spots.v_type = \"M\" OR parking_spots.v_type = \"L\" ORDER BY parking_spots.v_type DESC,parking_spots.third_entrance_distance,parking_spots.first_entrance_distance,parking_spots.second_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchSmallParkingSpotNearestToThirdEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"M\" OR parking_spots.v_type = \"L\" ORDER BY parking_spots.v_type DESC,parking_spots.first_entrance_distance,parking_spots.second_entrance_distance,parking_spots.third_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchMediumParkingSpotNearestToFirstEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"M\" OR parking_spots.v_type = \"L\" ORDER BY parking_spots.v_type DESC,parking_spots.second_entrance_distance,parking_spots.first_entrance_distance,parking_spots.third_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchMediumParkingSpotNearestToSecondEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"M\" OR parking_spots.v_type = \"L\" ORDER BY parking_spots.v_type DESC,parking_spots.second_entrance_distance,parking_spots.first_entrance_distance,parking_spots.third_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchMediumParkingSpotNearestToThirdEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"L\" ORDER BY parking_spots.first_entrance_distance,parking_spots.second_entrance_distance,parking_spots.third_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchLargeParkingSpotNearestToFirstEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"L\" ORDER BY parking_spots.second_entrance_distance,parking_spots.first_entrance_distance,parking_spots.third_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchLargeParkingSpotNearestToSecondEntrance();
	
	@Query(value = "SELECT * FROM parking_spots WHERE parking_spots.occupied = FALSE AND parking_spots.v_type = \"L\" ORDER BY parking_spots.third_entrance_distance,parking_spots.first_entrance_distance,parking_spots.second_entrance_distance LIMIT 1", nativeQuery = true)
	public ParkingSpotsEntity fetchLargeParkingSpotNearestToThirdEntrance();
}