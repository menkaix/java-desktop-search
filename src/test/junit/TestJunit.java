/**
 * 
 */
package test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author mendrika
 *
 */
public class TestJunit {
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test 
	public void Foo(){
		assertEquals(1, 1);
	}
	
	@Test 
	public void Foo2(){
		assertEquals(1, 2);
	}

}
