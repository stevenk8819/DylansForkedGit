import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.*;   // instead of  import org.junit.Test;
import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class IndexTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testIndex() throws IOException {
		Index the = new Index();
		File test = new File("index");
		assertTrue(test.exists());
		
	}

	@Test
	public void testAddBlob() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBlob() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearIndexFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPath() {
		fail("Not yet implemented");
	}

}
