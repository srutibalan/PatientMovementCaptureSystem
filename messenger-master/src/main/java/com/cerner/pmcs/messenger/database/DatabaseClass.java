package com.cerner.pmcs.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.cerner.pmcs.datamodel.Message;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();

	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	

	
	
	
}
