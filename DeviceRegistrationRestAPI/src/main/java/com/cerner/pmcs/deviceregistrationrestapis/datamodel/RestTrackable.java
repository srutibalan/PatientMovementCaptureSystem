package com.cerner.pmcs.deviceregistrationrestapis.datamodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cerner.careaware.core.rest.v1.domain.Identifier;
import com.cerner.careaware.datamodel.realtimelocation.RtlsTag;
import com.cerner.careaware.datamodel.realtimelocation.Trackable;
import com.cerner.careaware.datamodel.realtimelocation.TrackableType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class RestTrackable, a java class corresponding to RESTful
 * representation.
 */
@XmlRootElement(name = "Trackable")
@ApiModel("Trackable")
public class RestTrackable {

	@XmlElement
	@ApiModelProperty(allowableValues = "EQUIPMENT, PERSONNEL, PATIENT")
	public String type;

	@XmlElement
	@ApiModelProperty
	public RestTag tag;

	@XmlElement
	@ApiModelProperty
	public Identifier identifier;

	public String getType() {
		return type;
	}

	public RestTag getTag() {
		return tag;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public RestTrackable(Trackable trackable) {
		this.type = trackable.getType().toString();
		this.tag = new RestTag(trackable.getTag());
		this.identifier = new Identifier(trackable.getId());
	}

}
