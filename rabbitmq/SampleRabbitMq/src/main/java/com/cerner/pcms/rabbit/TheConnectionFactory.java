/*
 * 
 */
package com.cerner.pcms.rabbit;
import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * A factory for creating TheConnection objects.
 */
public class TheConnectionFactory
	{
		
		/** The factory. */
		private static ConnectionFactory factory = null;
		
		/**
		 * Gets the connection.
		 *
		 * @return the connection
		 * @throws IOException
		 *             Signals that an I/O exception has occurred.
		 */
		public static Connection getConnection() throws IOException
		{
			if (factory == null)
				{
					factory = new ConnectionFactory();
					factory.setHost("localhost");
				}
			return factory.newConnection();
		}
		
	}
