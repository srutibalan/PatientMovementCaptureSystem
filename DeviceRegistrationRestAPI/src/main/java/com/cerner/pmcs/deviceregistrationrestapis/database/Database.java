package com.cerner.pmcs.deviceregistrationrestapis.database;

import java.util.HashMap;
import java.util.Map;

import com.cerner.pmcs.deviceregistrationrestapis.datamodel.Device;


/**
 * The Class Database.
 */
public class Database {
	
	/** The devices. */
	private static Map<String, Device> devices = new HashMap<>();
	
	/**
	 * Gets the devices.
	 *
	 * @return the devices
	 */
	public static Map<String, Device> getDevices() {
		return devices;
	}

}
