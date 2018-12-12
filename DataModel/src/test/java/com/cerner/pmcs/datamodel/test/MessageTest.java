package com.cerner.pmcs.datamodel.test;

import org.junit.Assert;
import org.junit.Test;

import com.cerner.pmcs.datamodel.Message;

/**
 * The Class MessageTest.
 */
public class MessageTest
	{
	
		/**
		 * Test message creation.
		 *
		 * @throws Exception
		 *             the exception
		 */
		@Test
		public void testMessageCreation() throws Exception
		{
			Message testMessage=new Message(1L,"Im a test Message");
			Assert.assertEquals(1L, testMessage.getMessageId());
			Assert.assertEquals("Im a test Message",testMessage.getMessageBody());
		}
		
	}
