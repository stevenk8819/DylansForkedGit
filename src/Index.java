import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Index {
	private String path;
	private File indx;
	private File dir;
	private String indexContents;
	
	public Index() throws IOException {
		File fil = new File("Index.java");
		path = fil.getAbsolutePath();
		path = path.substring(0, path.length()-10);
		indx = new File(path +"index");
		indx.createNewFile();
		dir = new File(path +"objects");
		dir.mkdir();
		indexContents="";
	}
	
	public void addBlob(String fileName) throws NoSuchAlgorithmException, IOException {
		Blob23 blob = new Blob23(fileName);
		blob.shaTheFile();
		blob.createTheNewSha1File();
		indexContents+= fileName +" : " + blob.getShawedString() + "\n";
		FileWriter fw = new FileWriter(indx);
		fw.write(indexContents);
		fw.close();
	}
	
	public String getPath() {
		return path;
	}
	//initialize function: create new "objects" folder and empty file names "index"
	//add blobs, creates list inside index file with associated names
	//remove blobs
}
