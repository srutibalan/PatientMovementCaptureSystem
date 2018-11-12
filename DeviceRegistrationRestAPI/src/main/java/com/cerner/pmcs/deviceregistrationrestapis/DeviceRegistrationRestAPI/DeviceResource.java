package com.cerner.pmcs.deviceregistrationrestapis.DeviceRegistrationRestAPI;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.cerner.pmcs.deviceregistrationrestapis.RestServices.ServiceImplementation;
import com.cerner.pmcs.deviceregistrationrestapis.datamodel.Device;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

// TODO: Auto-generated Javadoc
//import org.json.JSONException;
//import org.json.JSONObject;


/**
 * The Class DeviceResource.
 *
 * @author SS051010
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/devices")
public class DeviceResource {

	/** The service implementation. */
	ServiceImplementation serviceImplementation = new ServiceImplementation();

	/**
	 * Gets the devices.
	 *
	 * @return the devices
	 */
	@GET
	public String getDevices() {
		
		List<Device> deviceList = serviceImplementation.getAllDevices();
		 Gson gson = new Gson();
		 
		 String jsonCartList = gson.toJson(deviceList);
		 
		 
		
		return jsonCartList;
	}

	/**
	 * Gets the one device.
	 *
	 * @param DeviceId
	 *            the device name
	 * @return the one device
	 */
	@GET
	@Path("/{DeviceName}")
	public Response getOneDevice(@PathParam("DeviceName") String DeviceName) {
		Device newDevice = serviceImplementation.getOneDevice(DeviceName);

		if (newDevice == null)
			return Response.status(Status.NO_CONTENT).build();
		
		Gson gson = new Gson();
		String jsonDevice = gson.toJson(newDevice);

		return Response.status(Status.ACCEPTED)
				.entity(jsonDevice)
				.build();

	}

	/**
	 * Adds the device.
	 *
	 * @param device            the device
	 * @param uriInfo            the uri info
	 * @return the response
	 * @throws JsonIOException the json IO exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/addDevice")
	public Response addDevice(Device device, @Context UriInfo uriInfo) throws JsonIOException, IOException {
		Device newDevice = serviceImplementation.addDevice(device);
		String newDeviceName = device.getDeviceName();
		
		Gson gson = new Gson();
		String jsonDevice = gson.toJson(newDevice);
		try (FileWriter writer = new FileWriter("C:\\FinalWorkspace\\DeviceRegistrationRestAPI\\devices.json", true))
        {
              
              gson.toJson(newDevice, writer);
              
        }
 catch (IOException e)
        {
              e.printStackTrace();
        } 

		
		URI uri = uriInfo.getAbsolutePathBuilder().path(newDeviceName).build();

		return Response.created(uri)
				.status(Status.CREATED)
				.entity(jsonDevice)
				.build();
	}

	/**
	 * Update device.
	 *
	 * @param deviceId
	 *            the device name
	 * @param device
	 *            the device
	 * @param uriInfo
	 *            the uri info
	 * @return the response
	 */
	@PUT
	@Path("/{deviceName}")
	public Response updateDevice(@PathParam("deviceName") String deviceName, Device device, @Context UriInfo uriInfo) {
		Device updatedDevice = serviceImplementation.updateDevice(deviceName, device);

		if (updatedDevice == null)
			return Response.status(Status.NO_CONTENT).build();

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		
		Gson gson = new Gson();
		String jsonDevice = gson.toJson(updatedDevice);

		return Response.created(uri)
				.status(Status.OK)
				.entity(jsonDevice)
				.build();
	}

	/**
	 * Delete device.
	 *
	 * @param deviceId the device name
	 * @param uriInfo the uri info
	 * @return the response
	 */
	@DELETE
	@Path("/{deviceName}")
	public Response deleteDevice(@PathParam("deviceName") String deviceName, @Context UriInfo uriInfo) {
		serviceImplementation.removeDevice(deviceName);

		
		URI uri = uriInfo.getAbsolutePathBuilder().build();

		return Response.created(uri)
				.status(Status.NO_CONTENT)
				.entity(deviceName)
				.build();
	}

	/**
	 * Delete all devices.
	 *
	 * @return the response
	 */
	@DELETE
	public Response deleteAllDevices() {
		serviceImplementation.removeAllDevices();
		
		return Response.status(Status.NO_CONTENT)
				.build();
	}

}
