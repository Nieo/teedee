package com.me.teedee.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.me.teedee.Status;

public class StatusTest {	
	@Test
	public void testReduceTimeLeft(){
		Status status = new Status(0.4f,3.1f,90.3f);
		status.reduceTimeLeft(1.3f);
		status.reduceTimeLeft(44f);
		assertEquals(45f,status.getTimeLeft(), 0);
	}
}
