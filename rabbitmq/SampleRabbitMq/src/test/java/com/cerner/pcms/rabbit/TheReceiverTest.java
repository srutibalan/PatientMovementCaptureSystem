package com.cerner.pcms.rabbit;

import java.net.URL;

import org.junit.Test;

import com.cerner.pmcs.datamodel.Message;

import junit.framework.Assert;


/**
 * The Class TheReceiverTest.
 */
public class TheReceiverTest
	{
		
		/**
		 * Test post messages.
		 *
		 * @throws Exception
		 *             the exception
		 */
		@Test
		public void testPostMessages() throws Exception
		{
			Message m = new Message();
			m.setMessageBody("test");
			URL url = new URL("http://localhost:8080/messenger/webapi/messages/");
			Assert.assertEquals(201, TheReceiver.PostMessages(url, m));
		}
		
	}
