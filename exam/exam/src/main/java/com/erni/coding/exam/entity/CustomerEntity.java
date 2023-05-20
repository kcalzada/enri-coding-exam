package com.erni.coding.exam.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="parking_customer")
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="entrance")
	private int entrance;
	
	@JoinColumn(name="parking_spot")
	@OneToOne
	private ParkingSpotsEntity parkingSpot;
	
	@Column(name="v_type")
	private String vType;
	
	@Column(name="plate_number")
	private String plateNumber;
	
	@Column(name="time_in")
	private Date timeIn;
	
	@Column(name="time_out")
	private Date timeOut;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEntrance() {
		return entrance;
	}

	public void setEntrance(int entrance) {
		this.entrance = entrance;
	}

	public ParkingSpotsEntity getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpotsEntity parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}
}