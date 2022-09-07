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
	private String filePath;
	private String fileName2;
	
	public Blob23(String fileName) throws IOException, NoSuchAlgorithmException {
		fileName2 = fileName;
		fileStringed ="";
		fil = new File(fileName);
		filePath = fil.getAbsolutePath();
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		while(br.ready()) {
			String line = br.readLine() +"\n";
			fileStringed+=line;
		}
		fileStringed = fileStringed.substring(0, fileStringed.length()-1);
		br.close();
		fr.close();
	}
	
	
	public void shaTheFile() throws NoSuchAlgorithmException{
		shaw = new SHA1();
		shawedString = shaw.GenerateHash(fileStringed);
	}
	
	public void createTheNewSha1File() throws IOException {
		String newPath = filePath.replaceFirst(fileName2, "objects\\\\" + shawedString);
		File noob = new File(newPath);
		noob.createNewFile();
		FileWriter fw = new FileWriter(newPath);
		fw.write(fileStringed);
		fw.close();
	}
	
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getShawedString() {
		return shawedString;
	}
	
	public String getFileName() {
		return fileName2;
	}
	
	public String getFileContents() {
		return fileStringed;
	}
}
