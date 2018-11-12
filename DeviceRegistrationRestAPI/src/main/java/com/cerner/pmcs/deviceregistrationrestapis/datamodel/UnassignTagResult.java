package com.cerner.pmcs.deviceregistrationrestapis.datamodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cerner.careaware.datamodel.realtimelocation.reply.RtlsTagUnassignResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class UnassignTagResult, a java class corresponding to RESTful
 * representation.
 */
@XmlRootElement(name = "UnassignTagResult")
@ApiModel("UnassignTagResult")
public class UnassignTagResult {

	@XmlElement
	@ApiModelProperty
	public boolean successful;

	public boolean isSuccessful() {
		return successful;
	}

	public UnassignTagResult(RtlsTagUnassignResult rtlsTagUnassignResult) {
		this.successful = rtlsTagUnassignResult.isSuccess();
	}

}
