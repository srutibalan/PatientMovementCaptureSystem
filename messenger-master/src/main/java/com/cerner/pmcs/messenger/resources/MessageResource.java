package com.cerner.pmcs.messenger.resources;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.cerner.pmcs.datamodel.Message;
import com.cerner.pmcs.messenger.service.MessageService;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public String getMessages() {
		
		List<Message> messageList = messageService.getAllMessages();
		Gson gson = new Gson();
		
		String jsonCartList = gson.toJson(messageList);
		
		return jsonCartList;
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getMessageId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		Gson gson = new Gson();
		String json = gson.toJson(newMessage);
		System.out.println(json);
		try (FileWriter writer = new FileWriter("C:\\Users\\SB051046\\Documents\\newWorkspace\\file.json"))
			{
				
				gson.toJson(messageService.getAllMessages(), writer);
				
			}
		catch (IOException e)
			{
				e.printStackTrace();
			}
			
		return Response.created(uri).entity(newMessage).build();
	}
	@POST
	@Path("emitMessage")
	public Response emitMessage(Message message, @Context UriInfo uriInfo) throws IOException
	{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		channel.exchangeDeclare("topic_logs", "topic");
		String nameOfDevice = message.getDeviceName();
		String RoutingKey = nameOfDevice + ".NEW_MESSAGE_POST";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try
			{
				out = new ObjectOutputStream(bos);
				out.writeObject(message);
				out.flush();
				byte[] yourBytes = bos.toByteArray();
				channel.basicPublish("topic_logs", RoutingKey, null, yourBytes);
			}
		finally
			{
				try
					{
						bos.close();
					}
				catch (IOException ex)
					{
						// ignore close exception
					}
			}
			
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
		Response r = Response.created(uri).status(Status.CREATED).entity(message).build();
		
		return r;
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setMessageId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		
		return message;
		
	}

	
	
	
}
