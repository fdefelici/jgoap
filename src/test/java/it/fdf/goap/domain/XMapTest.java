package it.fdf.goap.domain;

import it.fdf.goap.util.XMap;
import junit.framework.TestCase;

public class XMapTest extends TestCase {
	
	public void testEquals() {
		XMap<String, Boolean> map1 = new XMap<>();
		map1.put("key1", false);
		map1.put("key2", false);
		
		XMap<String, Boolean> map2 = new XMap<>();
		map2.put("key1", false);
		map2.put("key2", false);
		
		assertEquals(map1, map2);
	}
	
}
