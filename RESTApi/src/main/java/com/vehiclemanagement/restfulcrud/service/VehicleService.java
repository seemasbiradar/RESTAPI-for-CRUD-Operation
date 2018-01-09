package com.vehiclemanagement.restfulcrud.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.vehiclemanagement.restfulcrud.dao.VehicleDAO;
import com.vehiclemanagement.restfulcrud.daoImpl.VehicleDAOImpl;
import com.vehiclemanagement.restfulcrud.model.Vehicle;

@Path("/vehicles")
public class VehicleService {

	private static final Logger logger = LogManager.getLogger(VehicleService.class);
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";
	VehicleDAO vehicalDao = new VehicleDAOImpl();
	static {
		BasicConfigurator.configure();
	}

	/* GET - Get all Vehicles */
	@GET
	   @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	   public List<Vehicle> getVehicles() {
	       List<Vehicle> listOfVehicle = vehicalDao.getAllVehicles();
	       logger.debug("Get all Vehicles");
	       return listOfVehicle;	     
	}
	
	/* GET -Get Vehicles by id */
	@GET
	@Path("/{Id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		   public Vehicle getVehicle(@PathParam("Id") int Id) {
			   Vehicle vehicleById= vehicalDao.getVehicle(Id);
			   logger.debug("The id of the vehicle to be retrived is"+Id);
			   return vehicleById;
		   }
	
	/* GET -Get Vehicles by Year and Make */
	@GET
	@Path("/Year/{Year}/Make/{Make}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Vehicle> getVehiclesByMakeYear(@PathParam("Year") int Year, @PathParam("Make") String Make) {
		logger.debug("The search criteria for vehicle to be retrived is Year::" + Year + " Make::" + Make);
		List<Vehicle> listOfVehicle = vehicalDao.getVehiclesByMakeYear(Year, Make);
		return listOfVehicle;
		}

	/* GET -Get Vehicles by Year and Model */
	@GET
	@Path("/Year/{Year}/Model/{Model}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Vehicle> getVehiclesByModelYear(@PathParam("Year") int Year, @PathParam("Model") String Model) {
		logger.debug("The search criteria for vehicle to be retrived is Year::" + Year + " Model::" + Model);
		List<Vehicle> listOfVehicle = vehicalDao.getVehiclesByModelYear(Year, Model);
		return listOfVehicle;
		}

	/* GET -Get Vehicles by Make and Model */
	@GET
	@Path("/Make/{Make}/Model/{Model}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Vehicle> getVehiclesByMakeModel(@PathParam("Make") String Make, @PathParam("Model") String Model) {
		logger.debug("The search criteria for vehicle to be retrived is Make::" + Make + " Model::" + Model);
		List<Vehicle> listOfVehicle = vehicalDao.getVehiclesByMakeModel(Make, Model);
		return listOfVehicle;
		}

	/* Add New Vehicle */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })	
	public Vehicle addVehicle(Vehicle addVh) {
		Vehicle addVehicle = new Vehicle();
		try {
			if (addVh != null) {
				logger.debug("In Adding of the vehical" + addVehicle);
				addVh = vehicalDao.addVehicle(addVh);
			} else {
				logger.debug("Empty vehicle object in add");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error in Adding Vehicle" + e.getMessage());
		}
		return addVh;
	}

	/* Update - Vehicle */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Vehicle updateVehicle(Vehicle vh) {
		try {
			if (vh != null) {
				vh = vehicalDao.updateVehicle(vh);
			} else
				logger.debug("no vehicle object");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error in Updating Vehicle" + e.getMessage());
		}
		return vh;
	} 

	/* Delete - Vehicle */
	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean  deleteVehicle(@PathParam("id") int id) {
		logger.debug("Id of te vehical to be deleted is" + id);
		int result = 0;
		try {
			result = vehicalDao.deleteVehicle(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

}
