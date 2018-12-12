package com.cerner.pcms.rabbit;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import junit.framework.Assert;


/**
 * The Class TheChannelFactoryTest.
 */
public class TheChannelFactoryTest
	{
		
		/**
		 * Test get channel with exchange.
		 *
		 * @throws Exception
		 *             the exception
		 */
		@Test
		public void testGetChannelWithExchange() throws Exception
		{
			Channel testChannel = TheChannelFactory.getChannelWithExchange("TestExchangeName");
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			channel.exchangeDeclare("TestExchangeName", "topic");
			
			Assert.assertEquals(testChannel.getChannelNumber(), channel.getChannelNumber());
			
		}
		
	}
