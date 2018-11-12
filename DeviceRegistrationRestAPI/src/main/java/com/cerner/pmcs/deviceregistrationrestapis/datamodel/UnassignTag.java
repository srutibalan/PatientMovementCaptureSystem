package com.cerner.pmcs.deviceregistrationrestapis.datamodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cerner.careaware.core.rest.v1.domain.Identifier;
import com.cerner.careaware.datamodel.realtimelocation.RtlsTag;
import com.cerner.careaware.datamodel.realtimelocation.Trackable;
import com.cerner.careaware.datamodel.realtimelocation.TrackableType;

/**
 * The Class UnassignTag, a java class corresponding to RESTful representation.
 */
@XmlRootElement(name = "UnassignTag")
@ApiModel("UnassignTag")
public class UnassignTag {

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

	public Trackable toTrackable(UnassignTag unassignTag) {
		RestTag restTag = unassignTag.tag;

		TrackableType t = null;
		if (unassignTag.type == "EQUIPMENT")
			t = TrackableType.EQUIPMENT;
		else if (unassignTag.type == "PERSONNEL")
			t = TrackableType.PROVIDER;
		else if (unassignTag.type == "PATIENT")
			t = TrackableType.PATIENT;

		Trackable trackable = new Trackable(t, unassignTag.identifier.toString(),
				new RtlsTag(restTag.vendor, restTag.tagId));
		return trackable;

	}
}
