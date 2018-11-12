package com.cerner.pmcs.deviceregistrationrestapis.datamodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cerner.careaware.core.rest.v1.domain.Identifier;
import com.cerner.careaware.datamodel.realtimelocation.Trackable;

/**
 * The Class GetTrackable, a java class corresponding to RESTful representation.
 */
@XmlRootElement(name = "GetTrackable")
@ApiModel("GetTrackable")
public class GetTrackable {

	@XmlElement
	@ApiModelProperty
	public RestTag tag;

	@XmlElement
	@ApiModelProperty(allowableValues = "EQUIPMENT, PERSONNEL, PATIENT")
	public String type;

	@XmlElement
	@ApiModelProperty
	public Identifier identifier;

	public RestTag getTag() {
		return tag;
	}

	public String getType() {
		return type;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public GetTrackable(Trackable trackable) {
		this.tag = new RestTag(trackable.getTag());
		this.type = trackable.getType().toString();
		this.identifier = new Identifier(trackable.getId());
	}

}
