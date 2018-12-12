package com.cerner.pmcs.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cerner.pmcs.datamodel.Message;
import com.cerner.pmcs.messenger.database.DatabaseClass;
import com.cerner.pmcs.messenger.exception.DataNotFoundException;


public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values()); 
	}
	
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setMessageId(messages.size() + 1);
		messages.put(message.getMessageId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getMessageId() <= 0) {
			return null;
		}
		messages.put(message.getMessageId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	

	
	
	
}
