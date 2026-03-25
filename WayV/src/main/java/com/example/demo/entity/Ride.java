package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String fromCity;

	private String toCity;

	private String date;
	private String time;
	private String timeOfDeparture;

	
	public String getTimeOfDeparture() {
		return timeOfDeparture;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setTimeOfDeparture(String timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}

	private int seats;

	private double price;

	public Ride(){}

	public Ride(String driverName,String fromCity,String toCity,String date,int seats,double price,String time,String timeOfDeparture){
	this.fromCity=fromCity;
	this.toCity=toCity;
	this.date=date;
	this.seats=seats;
	this.price=price;
	this.time=time;
	this.timeOfDeparture=timeOfDeparture;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getId(){ return id;}


	public String getFromCity(){ return fromCity;}

	public String getToCity(){ return toCity;}

	public String getDate(){ return date;}

	public int getSeats(){ return seats;}

	public double getPrice(){ return price;}

	public void setSeats(int seats){ this.seats=seats;}

	}

