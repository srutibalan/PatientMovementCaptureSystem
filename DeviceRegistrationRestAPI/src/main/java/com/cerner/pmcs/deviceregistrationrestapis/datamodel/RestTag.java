package com.cerner.pmcs.deviceregistrationrestapis.datamodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cerner.careaware.datamodel.realtimelocation.RtlsTag;

/**
 * The Class RestTag, a java class corresponding to RESTful representation.
 */
@XmlRootElement(name = "Tag")
@ApiModel("Tag")
public class RestTag {

	@XmlElement
	@ApiModelProperty
	public String tagId;

	@XmlElement
	@ApiModelProperty
	public String vendor;

	public String getTagId() {
		return tagId;
	}

	public String getVendor() {
		return vendor;
	}

	public RestTag(RtlsTag rtlstag) {
		this.tagId = rtlstag.getTagId();
		this.vendor = rtlstag.getVendor();
	}
}
