package com.cerner.pmcs.deviceregistrationrestapis.DeviceRegistrationRestAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cerner.careaware.core.rest.resources.AbstractServiceResource;
import com.cerner.careaware.datamodel.realtimelocation.Trackable;
import com.cerner.careaware.datamodel.realtimelocation.request.GetTrackablesForIdsQualifier;
import com.cerner.careaware.datamodel.realtimelocation.request.RtlsTagAssignmentQualifier;
import com.cerner.careaware.datamodel.realtimelocation.request.RtlsTagUnassignQualifier;
import com.cerner.careaware.ibus.core.IBusUtils;
import com.cerner.careaware.ibus.core.bind.annotation.InjectValue;
import com.cerner.careaware.rest.realtimelocation.v1.domain.AssignTag;
import com.cerner.careaware.rest.realtimelocation.v1.domain.AssignTagResult;
import com.cerner.careaware.rest.realtimelocation.v1.domain.GetTrackable;
import com.cerner.careaware.rest.realtimelocation.v1.domain.RestTrackable;
import com.cerner.careaware.rest.realtimelocation.v1.domain.UnassignTag;
import com.cerner.careaware.rest.realtimelocation.v1.domain.UnassignTagResult;
import com.cerner.careaware.service.client.serviceproxy.realtimelocation.RealTimeLocationServiceProxy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

// JAX-RS implementation of Realtimelocation resource V1.

@Path("v1/realtimelocation")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Api("Realtimelocation")
public class TagAssignmentResource extends AbstractServiceResource {

	@InjectValue
	RealTimeLocationServiceProxy realTimeLocationServiceProxy;

	@InjectValue
	Validator validator;

	public TagAssignmentResource(ServletContext _context) {
		super(_context);
	}

	@GET
	@Path("/tagassignment")
	@ApiOperation(value = "Get trackable by internal identifier")
	public RestTrackable getTrackable(//
			@Size(min = 1, max = 255) //
			@ApiParam(value = "Unique internal identifier", required = true) //
			final GetTrackable getTrackable //
	) throws Exception {
		if (getTrackable == null || getTrackable.getTag() == null || getTrackable.getType() == null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		Collection<String> ids = new ArrayList<String>();
		ids.add(getTrackable.identifier.toString());
		final Map<String, Trackable> ibusTrackable = realTimeLocationServiceProxy
				.getTrackablesForIds(new GetTrackablesForIdsQualifier(ids)).getTrackables();

		if (IBusUtils.isEmpty(ibusTrackable)) {
			return null;''
		}
		return new RestTrackable(ibusTrackable.get(getTrackable.identifier.toString()));
	}

	/**
	 * Assigns new tag
	 * 
	 * @param restTrackable
	 * @param response
	 * @return Response
	 * @throws Exception
	 */
	@PUT
	@Path("/tagassignment/_assignTag")
	@ApiOperation(value = "assign a new tag", notes = "Trackables are assigned to a tag")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "assign tag", responseHeaders = @ResponseHeader(name = "Trackable", response = RestTrackable.class)) })
	public Response assign(//
			@NotNull @Valid //
			@ApiParam(value = "Assign Tag", required = true) //
			final AssignTag assignTag, //
			@Context final HttpServletResponse response //
	) throws Exception {
		if (assignTag == null //
				|| (assignTag.getTag() != null && assignTag.getType() != null && assignTag.getIdentifier() != null) //
				|| validator.validate(assignTag).size() > 0) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		Trackable trackable = assignTag.toTrackable(assignTag);
		AssignTagResult assignTagResult = new AssignTagResult(realTimeLocationServiceProxy
				.assignTag(new RtlsTagAssignmentQualifier(trackable, trackable.getId(), true)));

		return Response.status(HttpServletResponse.SC_OK).entity(assignTagResult).build();
	}

	/**
	 * Unassigns Tag
	 * 
	 * @param restTrackable
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PUT
	@Path("/tagassignment/_unassignTag")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "unassigned Tag Successfully", responseHeaders = @ResponseHeader(name = "Trackable", response = RestTrackable.class)) })
	@ApiOperation(value = "Unassigns tag")
	public Response unassignTag(//
			@Size(min = 1, max = 255) //
			@ApiParam(value = "trackable", required = true) //
			final UnassignTag unassignTag, //
			@Context final HttpServletResponse response //
	) throws Exception {
		if (unassignTag == null //
				|| (unassignTag.getTag() != null && unassignTag.getType() != null
						&& unassignTag.getIdentifier() != null) //
				|| validator.validate(unassignTag).size() > 0) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		Trackable trackable = unassignTag.toTrackable(unassignTag);
		UnassignTagResult unassignTagResult = new UnassignTagResult(realTimeLocationServiceProxy
				.unassignTag(new RtlsTagUnassignQualifier(trackable.getTag(), trackable.getId(), null)));

		return Response.status(HttpServletResponse.SC_OK).entity(unassignTagResult).build();
	}
}
