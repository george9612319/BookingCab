package com.example.BookingCab.model;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cab {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("cabId")
	private int cabId;
	
	@JsonProperty("toLocation")
	private	String toLocation;
	
	@JsonProperty("fromLocation")
	private	String fromLocation;
	
	@JsonProperty("typeOfCab")
	private String typeOfCab;
	
	@JsonProperty("rate")
	private double rate;
	
	@JsonProperty("passenger")
	private int passenger;
	
	
	public int getCabId() {
		return cabId;
	}
	public void setCabId(int cabId) {
		this.cabId = cabId;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getTypeOfCab() {
		return typeOfCab;
	}
	public void setTypeOfCab(String typeOfCab) {
		this.typeOfCab = typeOfCab;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getPassenger() {
		return passenger;
	}
	public void setPassenger(int passenger) {
		this.passenger = passenger;
	}
	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", toLocation=" + toLocation + ", fromLocation=" + fromLocation + ", typeOfCab="
				+ typeOfCab + ", rate=" + rate + ", passenger=" + passenger + "]";
	}
	/**
	 * @param cabId
	 * @param toLocation
	 * @param fromLocation
	 * @param typeOfCab
	 * @param rate
	 * @param passenger
	 */
	public Cab(int cabId, String toLocation, String fromLocation, String typeOfCab, double rate, int passenger) {
		this.cabId = cabId;
		this.toLocation = toLocation;
		this.fromLocation = fromLocation;
		this.typeOfCab = typeOfCab;
		this.rate = rate;
		this.passenger = passenger;
	}
	/**
	 * 
	 */
	public Cab() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
