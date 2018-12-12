package com.cerner.pcms.rabbit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;

import com.cerner.pmcs.datamodel.Message;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;


/**
 * The Class The Receiver.
 */
public class TheReceiver
	{
		
		/** The Constant EXCHANGE_NAME. */
		private static final String EXCHANGE_NAME = "message_logs";
		
		/** The log. */
		static Logger log = Logger.getLogger(TheReceiver.class.getName());
		
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
			Channel channel = TheChannelFactory.getChannelWithExchange(EXCHANGE_NAME);
			final String queueName = channel.queueDeclare().getQueue();
			
			String bindingKey = "*.NEW_MESSAGE_POST";
			channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
			
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			
			Consumer consumer = new DefaultConsumer(channel)
				{
					@Override
					public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
							byte[] body) throws IOException
					{
						ByteArrayInputStream bis = new ByteArrayInputStream(body);
						ObjectInput in = null;
						try {
						  in = new ObjectInputStream(bis);
						  Message o = (Message) in.readObject(); 
							String message = new String(body, "UTF-8");
							log.info("Message received " + message.toString());
							System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message.toString() + "'");
							URL url = new URL("http://localhost:8080/messenger/webapi/messages");
							PostMessages(url, o);
						}
						catch (ClassNotFoundException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} finally {
						  try {
						    if (in != null) {
						      in.close();
						    }
						  } catch (IOException ex) {
						    // ignore close exception
						  }
						}

						log.info("Posted message ");
					}
				};
			channel.basicConsume(queueName, true, consumer);
		}
		
		/**
		 * Post messages.
		 *
		 * @param url
		 *            the url
		 * @param o
		 *            the o
		 * @return the int
		 * @throws IOException
		 *             Signals that an I/O exception has occurred.
		 */
		public static int PostMessages(URL url, Message o) throws IOException
		{
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			//Message messageObject = new Message();
			//messageObject.setMessageBody(o);
			Gson gson = new Gson();
			String json = gson.toJson(o);
			System.out.println("the json   "+json);
			
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(json.getBytes());
			outputStream.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
				{
					log.error("Failed : HTTP error code : " + conn.getResponseCode());
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					
				}
				
			return conn.getResponseCode();
		}
		
	}
