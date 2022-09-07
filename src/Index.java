import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Index {
	private String path;
	private File indx;
	private File dir;
	private HashMap<String, String> fils;
	
	public Index() throws IOException {
		File fil = new File("Index.java");
		path = fil.getAbsolutePath();
		path = path.substring(0, path.length()-10);
		indx = new File(path +"index");
		indx.createNewFile();
		dir = new File(path +"objects");
		dir.mkdir();
		fils = new HashMap<String, String>();
	}
	
	public void addBlob(String fileName) throws NoSuchAlgorithmException, IOException {
		Blob23 blob = new Blob23(fileName);
		blob.shaTheFile();
		blob.createTheNewSha1File();
		fils.put(fileName, blob.getShawedString());
		FileWriter fw = new FileWriter(indx, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(fileName + " : " + blob.getShawedString() + "\n");
		pw.close();
		fw.close();
	}
	
	public void removeBlob(String fileName) {
		
	}
	
	public void clearIndexFile() throws IOException {
		FileWriter fw = new FileWriter(indx);
		fw.write("");
		fw.close();
	}
	
	public String getPath() {
		return path;
	}
	//initialize function: create new "objects" folder and empty file names "index"
	//add blobs, creates list inside index file with associated names
	//remove blobs
}
