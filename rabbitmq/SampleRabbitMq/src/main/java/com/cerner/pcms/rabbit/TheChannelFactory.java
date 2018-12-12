package com.cerner.pcms.rabbit;
import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * A factory for creating TheChannel objects.
 */
public class TheChannelFactory
	{
		
		/** The channel. */
		private static Channel channel;
		
		/**
		 * Gets the channel with exchange.
		 *
		 * @param exchangeName
		 *            the exchange name
		 * @return the channel with exchange
		 * @throws IOException
		 *             Signals that an I/O exception has occurred.
		 */
		public static Channel getChannelWithExchange(String exchangeName) throws IOException
		{
			Connection rabbitConnection = TheConnectionFactory.getConnection();
			channel = rabbitConnection.createChannel();
			
			channel.exchangeDeclare(exchangeName, "topic");
			return channel;
		
		}
	}
