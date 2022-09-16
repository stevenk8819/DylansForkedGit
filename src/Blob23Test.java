import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class Blob23Test {

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		new File("objects").mkdir();
		File tester = new File("blobTest.txt");
		BufferedWriter writeInTest = new BufferedWriter(new FileWriter(tester));
		writeInTest.write("This is the test");
		writeInTest.close();
		
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		File deleteFile = new File("blobTest.txt");
		deleteFile.delete();
	}

	@Test
	public void testBlob23() throws Exception {
		//fail("Not yet implemented");
		setUpBeforeClass();
		boolean check = false;
		Blob23 blobby = new Blob23("blobTest.txt");
		if(blobby.getFileContents().equals("This is the test")) {
			check = true;
			assertTrue(check);
		}
		tearDownAfterClass();
		
	}
	
	public static String getSHA1(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

	    MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	    crypt.reset();
	    crypt.update(password.getBytes("UTF-8"));

	    return new BigInteger(1, crypt.digest()).toString(16);
	}

	@Test
	public void testShaTheFile() throws NoSuchAlgorithmException, IOException {
		//fail("Not yet implemented");
		boolean check = false;
		Blob23 blob = new Blob23("blobTest.txt");
		blob.shaTheFile();
		String str = "";
		str = blob.getShawedString();
		String test = getSHA1("This is the test");
		//System.out.println(str.length());
		if(str.equals(test)) {
			check = true;
			assertTrue(check);
		}
		
	}

	@Test
	public void testCreateTheNewSha1File() throws Exception {
		//fail("Not yet implemented");
		setUpBeforeClass();
		boolean test = false;
		Blob23 blob = new Blob23("blobTest.txt");
		blob.shaTheFile();
		blob.createTheNewSha1File();
		String str = blob.getShawedString();
		File file = new File("objects/" + str + ".txt");
		file.createNewFile();
		if(file.exists()) {
			test = true;
			assertTrue(test);
		}

		
		
	}

}
