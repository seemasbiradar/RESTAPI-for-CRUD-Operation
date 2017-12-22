package com.vehiclemanagement.restfulcrud.model;

import javax.xml.bind.ValidationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle {
	private static final long serialVersionUID = 1L;
	private int Id;
	private int Year;
	private String Make;
	private String Model;

	public Vehicle() {
	}

	public Vehicle(int id, int year, String make, String model) {
		super();
		this.Id = id;
		this.Year = year;
		this.Make = make;
		this.Model = model;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public int getYear() {
		return Year;
	}
/*
	public void setYear(int year) {
		this.year = year;
	}*/
	public void setYear(int Year) throws ValidationException {
		if (Year < 1950 || Year > 2050) {
			System.out.println("Year is"+Year);
			throw new ValidationException("Year should be between 1950 and 2050, but is: " + Year);
		}
		this.Year = Year;
	}

	public String getMake() {
		return Make;
	}

	/*public void setMake(String make) {
		this.make = make;
	}*/
	public void setMake(String Make) throws ValidationException {
		if (Make == null || Make.equalsIgnoreCase("")) {
			throw new ValidationException("Make should not be null or empty, but is: " + Make);
		}
		this.Make = Make;
	}
	

	public String getModel() {
		return Model;
	}

	/*public void setModel(String model) {
		this.model = model;
	}*/
	public void setModel(String Model) throws ValidationException {
		if (Model == null || Model.equalsIgnoreCase("")) {
			throw new ValidationException("Model should not be null or empty, but is: " + Model);
		}
		this.Model = Model;
	}

}
