import java.io.File;
import java.io.IOException;

public class Index {
	private String path;
	
	public Index() throws IOException {
		File fil = new File("Index.java");
		path = fil.getAbsolutePath();
		path = path.substring(0, path.length()-10);
		File indx = new File(path +"index");
		indx.createNewFile();
		File dir = new File(path +"objects");
		dir.mkdir();
	}
	
	public String getPath() {
		return path;
	}
	//initialize function: create new "objects" folder and empty file names "index"
	//add blobs, creates list inside index file with associated names
	//remove blobs
}
