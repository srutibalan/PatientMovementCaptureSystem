package com.cerner.pmcs.testsforRest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.pmcs.deviceregistrationrestapis.DeviceRegistrationRestAPI.DeviceResource;
import com.cerner.pmcs.deviceregistrationrestapis.datamodel.Device;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class DeviceResourceTest.
 */
public class DeviceResourceTest extends JerseyTest {

	
	
    /* (non-Javadoc)
     * @see org.glassfish.jersey.test.JerseyTest#configure()
     */
    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(DeviceResource.class);
    }
    
    /**
     * Post device.
     */
    public void postDevice() {
    	Device device = new Device();
        List<Integer> inputPins = new ArrayList<Integer>();
        List<Integer> outputPins = new ArrayList<Integer>();
        inputPins.add(1);inputPins.add(5);
        outputPins.add(1);outputPins.add(5);
        device.setDeviceName("Alpha");
        device.setInputPins(inputPins);
        device.setOutputPins(outputPins);
        
        target("/devices/addDevice")
                .request()
                .post(Entity.entity(device, MediaType.APPLICATION_JSON));

    	
    }

    /**
     * Test get all devices.
     */
    @Test
    @After
    public void testGetAllDevices() {
    	    	
        Response output = target("/devices").request().get();
        
        assertEquals("should return status 200", 200, output.getStatus());
        
        
    }

    /**
     * Test get one device.
     */
    @Test
    public void testGetOneDevice(){
    	postDevice();
        Response output = target("/devices/Alpha").request().get();
        assertEquals("Should return status 202", 202, output.getStatus());
        assertNotNull("Should return notification", output.getEntity());
    }

    /**
     * Test get one device DB does not have device id.
     */
    @Test
    @Before
    public void testGetOneDevice_DBDoesNotHaveDeviceId(){
        Response output = target("/devices/12345").request().get();
        assertEquals("Should return status 204", 204, output.getStatus());
    }
    
    
    /**
     * Test create device with post.
     */
    @Test
    
    public void testCreateDeviceWithPost(){
        Device device = new Device();
        List<Integer> inputPins = new ArrayList<Integer>();
        List<Integer> outputPins = new ArrayList<Integer>();
        inputPins.add(1);inputPins.add(5);
        outputPins.add(1);outputPins.add(5);
        device.setDeviceName("Alpha");
        device.setInputPins(inputPins);
        device.setOutputPins(outputPins);
        
        Response output = target("/devices/addDevice")
                .request()
                .post(Entity.entity(device, MediaType.APPLICATION_JSON));

        assertEquals("Should return status 201", 201, output.getStatus());
        
    }

    /**
     * Test update device with put.
     */
    @Test
    public void testUpdateDeviceWithPut(){
        Device device = new Device();
        List<Integer> inputPins = new ArrayList<Integer>();
        List<Integer> outputPins = new ArrayList<Integer>();
        inputPins.add(2);inputPins.add(3);
        outputPins.add(5);outputPins.add(8);
        device.setInputPins(inputPins);
        device.setOutputPins(outputPins);
        Response output = target("devices/Alpha")
                .request()
                .put(Entity.entity(device, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 200", 200, output.getStatus());
    }

    /**
     * Test delete one device.
     */
    @Test
    public void testDeleteOneDevice(){
    	postDevice();
        Response output = target("/devices/Alpha").request().delete();
        assertEquals("Should return status 204", 204, output.getStatus());
    }
    
    /**
     * Test delete all devices.
     */
    @Test
    public void testDeleteAllDevices(){
        Response output = target("/devices").request().delete();
        assertEquals("Should return status 204", 204, output.getStatus());
    }

}


