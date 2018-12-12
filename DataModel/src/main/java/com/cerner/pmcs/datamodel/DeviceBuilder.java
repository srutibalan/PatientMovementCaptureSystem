package com.cerner.pmcs.datamodel;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class DeviceBuilder.
 *
 * @author SB051046
 */
public class  DeviceBuilder {

	
	/** The device id. */
	long deviceId;

	/** The input pins. */
	 List<Integer> inputPins = new ArrayList<Integer>();

	/** The output pins. */
	 List<Integer> outputPins = new ArrayList<Integer>();

	/** The device type. */
	 String deviceType;

	/** The registered date. */
	 long registeredDate;


	/**
	 * Instantiates a new device builder.
	 *
	 * @param deviceId
	 *            the device id
	 * @param deviceType
	 *            the device type
	 * @param registeredDate
	 *            the registered date
	 */
	public DeviceBuilder(long deviceId, String deviceType) {
		
		this.deviceId = deviceId;
		this.deviceType = deviceType;
		this.registeredDate = System.currentTimeMillis()/1000;
	}
	
	/**
	 * Input pins.
	 *
	 * @param newInputPins
	 *            the new input pins
	 * @return the device builder
	 */
	public DeviceBuilder inputPins(List<Integer> newInputPins)
	{
		this.inputPins=newInputPins;
		return this;
		
	}
	
	/**
	 * Output pins.
	 *
	 * @param newOutputPins
	 *            the new output pins
	 * @return the device builder
	 */
	public DeviceBuilder outputPins(List<Integer> newOutputPins)
	{
		this.outputPins=newOutputPins;
		return this;
		
	}
    
    /**
	 * Builds device
	 *
	 * @return the device
	 */
    public Device build() {
        Device device =  new Device(this);
        return device;
    }
}
