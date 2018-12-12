package com.cerner.pcms.rabbit;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.Connection;


/**
 * The Class TheConnectionFactoryTest.
 */
public class TheConnectionFactoryTest
	{
		
		/**
		 * Test get connection.
		 *
		 * @throws Exception
		 *             the exception
		 */
		@Test
		public void testGetConnection() throws Exception
		{
			Connection testConnection =  TheConnectionFactory.getConnection();
			Assert.assertEquals("amqp://guest@127.0.0.1:5672/",testConnection.toString());
		}
		
	}
