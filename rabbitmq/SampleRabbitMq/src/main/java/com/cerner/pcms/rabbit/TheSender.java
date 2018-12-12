/*
 * 
 */
package com.cerner.pcms.rabbit;

import java.util.Scanner;

import com.rabbitmq.client.Channel;

/**
 * The Class TheSender.
 */
public class TheSender
	{
		
		/**
		 * The main method.
		 *
		 * @param argv
		 *            the arguments
		 * @throws Exception
		 *             the exception
		 */
		public static void main(String[] argv) throws Exception
		{
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter your message");
			String message = scanner.nextLine();
			String routingKey = "send.NEW_MESSAGE_POST";
			
			Channel rabbitChannel = TheChannelFactory.getChannelWithExchange("topic_logs");
			rabbitChannel.basicPublish("message_logs", routingKey, null, message.getBytes("UTF-8"));
			
			System.out.println(" [x] Sent '" + message + "'");
			
			scanner.close();
			
		}
	}
