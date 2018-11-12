package com.cerner.pmcs.deviceregistrationrestapis.RestServices;

import java.util.List;

import com.cerner.pmcs.deviceregistrationrestapis.datamodel.Device;

/**
 * The Interface ServiceInterface.
 */
interface RestInterface {

	/**
	 * Gets the all devices.
	 *
	 * @return the all devices
	 */
	public List<Device> getAllDevices();
	
	public Device getOneDevice(String deviceId);

	/**
	 * Adds the device.
	 *
	 * @param device
	 *            the device
	 * @return the device
	 */
	public Device addDevice(Device device);

	/**
	 * Update device.
	 *
	 * @param deviceName
	 *            the device name
	 * @param device
	 *            the device
	 * @return the device
	 */
	public Device updateDevice(String deviceName, Device device);

	/**
	 * Removes the device.
	 *
	 * @param deviceName the device name
	 * @return the long
	 */
	public boolean removeDevice(String deviceName);

	/**
	 * Removes the all devices.
	 */
	public void removeAllDevices();

}