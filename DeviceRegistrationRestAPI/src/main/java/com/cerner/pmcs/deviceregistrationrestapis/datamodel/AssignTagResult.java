package com.cerner.pmcs.deviceregistrationrestapis.datamodel;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cerner.careaware.datamodel.realtimelocation.reply.RtlsTagAssignmentResult;

/**
 * The Class AssignTagResult, a java class corresponding to RESTful
 * representation.
 */
@XmlRootElement(name = "AssignTagResult")
@ApiModel("AssignTagResult")
public class AssignTagResult {

	@XmlElement
	@ApiModelProperty
	public boolean successful;

	@XmlElement
	@ApiModelProperty
	public String failureReason;

	@XmlElement
	@ApiModelProperty
	public List<RestTrackable> trackables;

	public boolean isSuccessful() {
		return successful;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public List<RestTrackable> getTrackables() {
		return trackables;
	}

	public AssignTagResult(RtlsTagAssignmentResult rtlsTagAssignmentResult) {
		this.successful = rtlsTagAssignmentResult.isSuccess();
		this.failureReason = rtlsTagAssignmentResult.getFailureType().toString();
		this.trackables.add(new RestTrackable(rtlsTagAssignmentResult.getOtherTrackable()));
	}

}
