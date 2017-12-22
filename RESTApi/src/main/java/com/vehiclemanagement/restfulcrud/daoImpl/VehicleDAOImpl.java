package com.vehiclemanagement.restfulcrud.daoImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.vehiclemanagement.restfulcrud.dao.VehicleDAO;
import com.vehiclemanagement.restfulcrud.model.Vehicle;
import com.vehiclemanagement.restfulcrud.service.VehicleService;

public class VehicleDAOImpl implements VehicleDAO {
	private static final Logger logger = LogManager.getLogger(VehicleService.class);
	private static final Map<Integer, Vehicle> vehicleMap = new HashMap<Integer, Vehicle>();
	static int idGen = 0;

	static {
		try {
			initEmps();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void initEmps() throws ValidationException {
		Vehicle v1 = new Vehicle(++idGen, 2015, "Audi", "Q5");
		Vehicle v2 = new Vehicle(++idGen, 2016, "BMW", "S");
		Vehicle v3 = new Vehicle(++idGen, 2017, "Merc", "A");

		vehicleMap.put(v1.getId(), v1);
		vehicleMap.put(v2.getId(), v2);
		vehicleMap.put(v3.getId(), v3);
	}
	
	 public  List<Vehicle> getAllVehicles() {
	        Collection<Vehicle> c = vehicleMap.values();
	        List<Vehicle> list = new ArrayList<Vehicle>();
	        list.addAll(c);
	        return list;
	    } 
	 
	 public Vehicle getVehicle(int id)  {
	    	logger.debug("In getVehicle with id : " +id);
	    	if(vehicleMap==null) {
	    		throw new RuntimeException("No Vehicals Present");
	    	}
	    	Vehicle vehicleById=vehicleMap.get(id);
	    	if(vehicleById==null)
	    	{
	    		throw new RuntimeException("No Vehicals With id : " + id);
	    	}
	        return vehicleById;
	    }
	 
	 public  List<Vehicle> getVehiclesByMakeYear(int year,String make){
	    	if(year== 0  || make==null || make.equalsIgnoreCase(""))
	    	{
	    		throw new RuntimeException("Invalid Search Cretirea Data");
	    	}
	    	Collection<Vehicle> c = vehicleMap.values();
	        List<Vehicle> lis = new ArrayList<Vehicle>();
	        lis.addAll(c);   
	   
	    	List<Vehicle> finalList = new ArrayList<Vehicle>();
	    	if(lis != null && lis.size() > 0)
	    	{
	    		for(Vehicle vh : lis)
	    		{
	    			if(vh.getMake().equalsIgnoreCase(make) && vh.getYear() == year)
	    			{
	    				finalList.add(vh);
	    			}
	    		}
	    	}
	    	if(finalList.size() == 0) {
	    		throw new RuntimeException("No Vehical with Make " + make + " and Year : " +year+" is present");
	    	}
	    	return finalList;
	    }
	    public  List<Vehicle> getVehiclesByModelYear(int year,String model){
	    	if(year== 0 || model==null || model.equalsIgnoreCase("") )
	    	{
	    		throw new RuntimeException("Invalid Search Cretirea Data");
	    	}
	    	Collection<Vehicle> c = vehicleMap.values();
	        List<Vehicle> lis = new ArrayList<Vehicle>();
	        lis.addAll(c);   
	   
	    	List<Vehicle> finalList = new ArrayList<Vehicle>();
	    	if(lis != null && lis.size() > 0)
	    	{
	    		for(Vehicle vh : lis)
	    		{
	    			if(vh.getModel().equalsIgnoreCase(model) && vh.getYear() == year)
	    			{
	    				finalList.add(vh);
	    			}
	    		}
	    	}
	    	if(finalList.size() == 0) {
	    		throw new RuntimeException("No Vehical with model " + model + " and Year : " +year+" is present");
	    	}
	    	return finalList;
	    }
	    public  List<Vehicle> getVehiclesByMakeModel(String make,String model){
	    	
	    	if(make==null || make.equalsIgnoreCase("")  || model==null || model.equalsIgnoreCase("") )
	    	{
	    		throw new RuntimeException("Invalid Search Cretirea Data");
	    	}
	    	Collection<Vehicle> c = vehicleMap.values();
	        List<Vehicle> lis = new ArrayList<Vehicle>();
	        lis.addAll(c);   
	   
	    	List<Vehicle> finalList = new ArrayList<Vehicle>();
	    	if(lis != null && lis.size() > 0)
	    	{
	    		for(Vehicle vh : lis)
	    		{
	    			if(vh.getMake().equalsIgnoreCase(make) && vh.getModel().equalsIgnoreCase(model))
	    			{
	    				finalList.add(vh);
	    			}
	    		}
	    	}
	    	if(finalList.size() == 0) {
	    		throw new RuntimeException("No Vehical with Make " + make + " and Model : " +model +" is present");
	    	}
	    	return finalList;
	    }
	    
	    public Vehicle addVehicle(Vehicle pVehicle)  {
	    	pVehicle.setId(idGen+1);
	    	logger.debug("In addVehicle : " +pVehicle);
	    	try {
				validateInput(pVehicle.getYear(),pVehicle.getMake(),pVehicle.getModel());
			} catch (ValidationException e) {
				throw new RuntimeException(e.getMessage());
			}
	    	if(vehicleMap==null) {
	    		throw new RuntimeException("No Vehicals to be Added");
	    	}
	    	vehicleMap.put(pVehicle.getId(), pVehicle);
	        return pVehicle;
	    }
	 
	    public Vehicle updateVehicle(Vehicle pVehicle)  {
	    	logger.debug("In UpdateVehicle : " +pVehicle);
	    	try {
				validateInput(pVehicle.getYear(),pVehicle.getMake(),pVehicle.getModel());
			} catch (ValidationException e) {
				throw new RuntimeException(e.getMessage());
			}
	    	if(vehicleMap==null) {
	    		throw new RuntimeException("No Vehicals to be udated");
	    	}
	    	if(vehicleMap.get(pVehicle.getId())== null)
	    	{
	    		throw new RuntimeException("Vehical with id " + pVehicle.getId() + " is not present,Hence can not be updated ");
	    	}
	    	vehicleMap.put(pVehicle.getId(), pVehicle);
	        return pVehicle;
	    }
	 
	    public int deleteVehicle(int id) {
	    	logger.debug("In Delete Vehicle : " +id);
	    	if(vehicleMap==null) {
	    		throw new RuntimeException("No Vehicals to be deleted");
	    	}
	    	if(vehicleMap.get(id)== null)
	    	{
	    		throw new RuntimeException("Vehical with id " + id + " is not present,Hence can not be deleted ");
	    	}
	    	vehicleMap.remove(id);
	    	return 1;
	    }
	 public boolean validateInput(int Year,String Make,String Model) throws ValidationException
	    {
	    	if (Year < 1950 || Year > 2050) {
				logger.debug("Year is"+Year);
				throw new ValidationException("Year should be between 1950 and 2050, but is: " + Year);
			}
	    	if (Make == null || Make.equalsIgnoreCase("")) {
				throw new ValidationException("Make should not be null or empty, but is: " + Make);
			}
	    	if (Model == null || Model.equalsIgnoreCase("")) {
				throw new ValidationException("Make should not be null or empty, but is: " + Make);
			}
	    	return true;
	    }

}
