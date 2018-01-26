import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CaseTest {

	@Before
	public void setUp() throws Exception {
		op=new Case();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		Case c1 = new Case (3,3);
		Case c2 = c1.clone();
		assertNotSame(c1,c2);
		assertEquals(c1,c2);
	}
	public static Test suite (){
		
	}
}
