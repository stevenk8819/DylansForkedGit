import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File foo = new File("something.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(foo));
		bw.write("some content");
		bw.close();

		File bar = new File("bar.txt");
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(bar));
		bw2.write("bar content");
		bw2.close();

		File foobar = new File("foobar.txt");
		BufferedWriter bw3 = new BufferedWriter(new FileWriter(foobar));
		bw3.write("foobar content");
		bw3.close();

		File stuff = new File("stuff.txt");
		BufferedWriter bw4 = new BufferedWriter(new FileWriter(stuff));
		bw4.write("stuff content");
		bw4.close();
		
		File lastFile = new File("things.txt");
		BufferedWriter bw5 = new BufferedWriter(new FileWriter(lastFile));
		bw5.write("things content");
		bw5.close();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		
	}

	@Test
	void test() throws NoSuchAlgorithmException, IOException {
		Git g = new Git();



//		System.out.println ("C1"); 
		Blob23 b1 = new Blob23("something.txt"); 
		g.addBlob(b1); 
		g.makeCommit(null, "TEST1", "AUTHOR1");
		System.out.println ("COMMIT1: " + g.getCommit().getString());

		
//		System.out.println ("C2"); 
		Blob23 b2 = new Blob23("bar.txt"); 
		g.addBlob(b2); 
		g.makeCommit(g.getString(), "TEST2", "AUTHOR2");
		System.out.println ("COMMIT2: " + g.getCommit().getString());

		
//		System.out.println ("C3"); 
		Blob23 b3 = new Blob23 ("foobar.txt"); 
		g.addBlob(b3);
		g.makeCommit(g.getString(), "TEST3", "AUTHOR3");
		System.out.println ("COMMIT3: " + g.getCommit().getString()); 


//		System.out.println ("C4"); 
		Blob23 b4 = new Blob23("stuff.txt"); 
		g.delete("bar.txt");
		g.addBlob(b4);
		g.makeCommit(g.getString(), "TEST4", "AUTHOR4");
		System.out.println ("COMMIT4: " + g.getCommit().getString()); 
		
		
//		System.out.println ("C5"); 
		Blob23 b5 = new Blob23("things.txt"); 
		g.addBlob(b5);
		g.delete("something.txt");
		g.makeCommit(g.getString(), "TEST5", "AUTHOR5");
		System.out.println ("COMMIT5: " + g.getCommit().getString()); 
		
		g.switchBranch(2); //goes to the index 2 commit (in human terms is the 3rd commit)
	}

}
