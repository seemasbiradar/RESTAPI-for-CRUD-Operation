package com.vehiclemanagement.restfulcrud;

import org.glassfish.jersey.server.ResourceConfig;

import com.vehiclemanagement.restfulcrud.model.Vehicle;

public class RestDemoJaxRsApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public RestDemoJaxRsApplication() {
		register(Vehicle.class);
	}
}
