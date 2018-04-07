package it.fdf.goap;

import junit.framework.TestCase;

public class GStateTest extends TestCase {
	
	public void testEquals() {
		GState state1 = new GState("state1");
		state1.set("key1", false);
		state1.set("key2", false);
		
		GState state2 = new GState("state2");
		state2.set("key1", false);
		state2.set("key2", false);
		
		GState state3 = new GState("state3");
		state3.set("key1", false);
		state3.set("key2", false);
		state3.set("key3", false);
		
		GState state4 = new GState("state4");
		state4.set("key1", false);
		state4.set("key2", false);
		state4.set("key3", false);
		
		assertEquals(state1, state2);
		assertTrue(!state1.equals(state3));
		assertTrue(!state1.equals(state4));
	}
	
	public void testMerge() {
		GState state1 = new GState("state1");
		state1.set("key1", false);
		state1.set("key2", false);
		
		GState state2 = new GState("state2");
		state1.set("key1", true);
		state1.set("key3", true);
		
		GState expected = new GState("expected");
		expected.set("key1", true);
		expected.set("key2", false);
		expected.set("key3", true);
		assertEquals(expected, state1.mergeWith(state2));
	}

}
