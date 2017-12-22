package com.vehiclemanagement.restfulcrud.dao;
import java.util.List;

import com.vehiclemanagement.restfulcrud.model.Vehicle;

public interface VehicleDAO {
    public  List<Vehicle> getAllVehicles();
    public  Vehicle getVehicle(int id)  ;
    public  List<Vehicle> getVehiclesByMakeYear(int year,String make);
    public  List<Vehicle> getVehiclesByModelYear(int year,String Model);
    public  List<Vehicle> getVehiclesByMakeModel(String make,String modelr);
    public  Vehicle addVehicle(Vehicle pVehicle) ;
    public  Vehicle updateVehicle(Vehicle pVehicle) ; 
    public  int deleteVehicle(int id);    
    

}
