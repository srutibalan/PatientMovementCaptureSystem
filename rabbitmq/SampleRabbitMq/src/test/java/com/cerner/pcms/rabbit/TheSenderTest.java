package com.cerner.pcms.rabbit;

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import junit.framework.Assert;

/**
 * The Class TheSenderTest.
 */
public class TheSenderTest
	{
		
		/**
		 * Test main.
		 */
		@Test
		public void testMain()
		{
			try
				{
					
					Channel rabbitChannel = TheChannelFactory.getChannelWithExchange("test_channel");
					final String messageToSend = "hi";
					
					rabbitChannel.basicPublish("test_channel", "test", null, messageToSend.getBytes("UTF-8"));
					final String queueName = rabbitChannel.queueDeclare().getQueue();
					
					rabbitChannel.queueBind(queueName, "test_channel", "test");
					
					Consumer consumer = new DefaultConsumer(rabbitChannel)
						{
							@Override
							public void handleDelivery(String consumerTag, Envelope envelope,
									AMQP.BasicProperties properties, byte[] body) throws IOException
							{
								
								String messageReceived = new String(body, "UTF-8");
								Assert.assertEquals(messageToSend, messageReceived);
								
							}
						};
					
					rabbitChannel.basicConsume(queueName, true, consumer);
					
				}
			catch (Exception e)
				{
					System.out.print(e);
				}
		}
	}
