import java.io.File;
import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class Blob23 {
	private String fileStringed;
	private String shawedString;
	private File fil;
	private SHA1 shaw;
	
	public Blob23(String fileName) throws IOException, NoSuchAlgorithmException {
		fileStringed ="";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		while(br.ready()) {
			String line = br.readLine();
			fileStringed+=line;
		}
		br.close();
	}
	
	public String getFileContents() {
		return fileStringed;
	}
	
	public void shaTheFile() throws NoSuchAlgorithmException{
		shaw = new SHA1();
		shawedString = shaw.GenerateHash(fileStringed);
	}
	
	public String getShawedString() {
		return shawedString;
	}
}
