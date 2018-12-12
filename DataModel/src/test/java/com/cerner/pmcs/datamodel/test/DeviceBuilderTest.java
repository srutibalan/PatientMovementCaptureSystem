/*
 * 
 */
package com.cerner.pmcs.datamodel.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cerner.pmcs.datamodel.Device;

/**
 * The Class DeviceBuilderTest.
 */
public class DeviceBuilderTest {
	
	/**
	 * Device builder test object creation.
	 */
	@Test 
	public void DeviceBuilderTest_ObjectCreation()
	{
		List<Integer> listOfPins= new ArrayList<Integer>();
		listOfPins.add(1);listOfPins.add(3);
		Device device = new Device.DeviceBuilder("Sensor").inputPins(listOfPins).outputPins(listOfPins).build();
		Assert.assertEquals("The deviceId is checked",device.getDeviceId(),device.getDeviceId());
		Assert.assertEquals("The deviceType is checked","Sensor",device.getDeviceType());
		Assert.assertEquals("The inputPins is checked",listOfPins,device.getInputPins());
		Assert.assertEquals("The deviceId is checked",listOfPins,device.getOutputPins());
		//System.out.println(device.getRegisteredDate());
		
	}


}
