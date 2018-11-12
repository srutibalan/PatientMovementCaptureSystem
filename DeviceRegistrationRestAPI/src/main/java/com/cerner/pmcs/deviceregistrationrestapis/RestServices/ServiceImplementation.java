package com.cerner.pmcs.deviceregistrationrestapis.RestServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cerner.pmcs.deviceregistrationrestapis.datamodel.Device;
import com.cerner.pmcs.deviceregistrationrestapis.database.Database;

public class ServiceImplementation implements RestInterface {

	private Map<String, Device> devices = Database.getDevices();

	/**
	 * Default constructor
	 */
	public ServiceImplementation() {

		}

	/**
	 * Gets the list of all registered devices
	 */
	public List<Device> getAllDevices() {

		List<Device> deviceList = new ArrayList<Device>(devices.values());
		return deviceList;
	}

	/**
	 * gets a single device based on the device ID that is input
	 * 
	 * @param deviceId
	 * @return returns an object of type Device
	 */
	public Device getOneDevice(String deviceName) {
		Device device = devices.get(deviceName);

		return device;
	}

	/**
	 * add a newly registered device
	 * 
	 * @param device
	 * @return returns an object of type Device
	 */
	public Device addDevice(Device device) {
		
		device.setDeviceId();
		device.setRegisteredDate();
		devices.put(device.getDeviceName(), device);
		return device;
	}

	/**
	 * modifies device property(s) based on the device ID that is input
	 * 
	 * @param device
	 * @return returns an object of type Device
	 */
	public Device updateDevice(String deviceName, Device device) {
		Device existingDevice = null;

		if (devices.containsKey(deviceName)) {
			existingDevice = devices.get(deviceName);
			existingDevice.setDeviceType(device.getDeviceType());
			existingDevice.setInputPins(device.getInputPins());
			existingDevice.setOutputPins(device.getOutputPins());
			devices.put(deviceName, existingDevice);

		}
		return existingDevice;
	}

	/**
	 * unregisters a device based on the device ID that is input
	 * 
	 * @param deviceId
	 * @return returns the unregistered result - boolean
	 */
	public boolean removeDevice(String deviceName) {
		boolean result = false;
		if (devices.containsKey(deviceName)) {
			result = true;
			devices.remove(deviceName);
		}
		return result;

	}

	/**
	 * unregisters all devices
	 */
	public void removeAllDevices() {
		devices.clear();
	}

	

}