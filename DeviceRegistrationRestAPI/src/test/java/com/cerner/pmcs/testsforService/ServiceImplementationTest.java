package com.cerner.pmcs.testsforService;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.cerner.pmcs.deviceregistrationrestapis.RestServices.ServiceImplementation;
import com.cerner.pmcs.deviceregistrationrestapis.datamodel.Device;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceImplementationTest extends ServiceImplementation {
	
	
	@Test
	public void AtestGetAllDevicesInitially() {
		
		List<Device> deviceList  = getAllDevices();
		    
		    assertNotNull(deviceList);
		  }
	@Test
	public void DtestGetAllDevicesAfterAddingDevice() {
		
		List<Device> deviceList  = getAllDevices();
		    
		    assertNotNull(deviceList);
		  }
	
	@Test
	public void BtestGetOneDevice_doesNotExist() {
		Device sampleDevice = getOneDevice("alpha");
		
		assertNull(sampleDevice);
	}
	
	@Test
	public void CtestAddDevice() {
		
		Device device = new Device();
        List<Integer> inputPins = new ArrayList<Integer>();
        List<Integer> outputPins = new ArrayList<Integer>();
        inputPins.add(1);inputPins.add(5);
        outputPins.add(1);outputPins.add(5);
        device.setDeviceName("Alpha");
        device.setInputPins(inputPins);
        device.setOutputPins(outputPins);
		assertNotNull(addDevice(device));
	}
	
	@Test
	public void EtestGetOneDevice_hasDeviceObject() {
		Device sampleDevice = getOneDevice("Alpha");
		
		assertNotNull(sampleDevice);
	}
	
	@Test
	public void FtestUpdateDevice() {
		Device sampleDevice = new Device();
		sampleDevice.setDeviceType("PIR sensor");
		 List<Integer> inputPins = new ArrayList<Integer>();
	        List<Integer> outputPins = new ArrayList<Integer>();
	        inputPins.add(2);
	        outputPins.add(7);
		sampleDevice.setInputPins(inputPins);
		sampleDevice.setOutputPins(outputPins);
		
		
		assertNotNull(updateDevice("Alpha", sampleDevice));
			
	}
	
	@Test
	public void GtestRemoveOneDevice_doesNotExist() {
		assertFalse(removeDevice("Beta"));
	}
	
	@Test
	public void HtestRemoveOneDevice_hasDeviceObject() {
		assertTrue(removeDevice("Alpha"));
	}
	
	}


