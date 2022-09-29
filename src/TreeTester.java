import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TreeTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testTree() throws Exception {
		ArrayList<String> strs = new ArrayList<String>();
		String check="";

		strs.add("foo.txt : 7a6ef001667f196bbd53f73f4ef2448bbb68b860");
		strs.add("foo1.txt : 4d66eaab0e57944aca8e8b56f9fec5e31906768a");
		strs.add("foo2.txt : a131ecfc68b736a3752c410d62f4876853d7dca6");
		strs.add("foo3.txt : f13b4e31340d09c061b8f89288242810e1c9bf89");
		strs.add("foo4.txt : 9226ae3a96d73e882b27cd3c34058402338b93b4");		
		strs.add("foo5.txt : 271e0cb0a6eae52c9e56c72c1a7ec5707c49a064");		
		strs.add("foo6.txt : e6829fa026e73b1f6ae9ebecfc9ef9175d3a7bf9");		
		strs.add("foo7.txt : e05673ed46f7d6cbf8634064f2870b39bf957e5f");

		for (int i = strs.size()-1; i>=0; i--) {
			check+=strs.get(i);
			if (i-1!=0) {
				check+="\n";
			}
		}

		Tree t = new Tree(strs);
		t.makeNewFile(); 
		

//		if (check.equals(contents)) {
//			System.out.println(contents);
//		}

	}

}
