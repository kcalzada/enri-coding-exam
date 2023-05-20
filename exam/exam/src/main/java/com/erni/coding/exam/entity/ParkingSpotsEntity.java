package com.erni.coding.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="parking_spots")
public class ParkingSpotsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="v_type")
	private String vType;
	
	@Column(name="occupied")
	private boolean occupied;
	
	@Column(name="first_entrance_distance")
	private int firstEntranceDistance;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getFirstEntranceDistance() {
		return firstEntranceDistance;
	}

	public void setFirstEntranceDistance(int firstEntranceDistance) {
		this.firstEntranceDistance = firstEntranceDistance;
	}

	public int getSecondEntranceDistance() {
		return secondEntranceDistance;
	}

	public void setSecondEntranceDistance(int secondEntranceDistance) {
		this.secondEntranceDistance = secondEntranceDistance;
	}

	public int getThirdEntranceDistance() {
		return thirdEntranceDistance;
	}

	public void setThirdEntranceDistance(int thirdEntranceDistance) {
		this.thirdEntranceDistance = thirdEntranceDistance;
	}

	@Column(name="second_entrance_distance")
	int secondEntranceDistance;
	
	@Column(name="third_entrance_distance")
	int thirdEntranceDistance;
}