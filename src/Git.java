import java.util.*; 
import java.io.*;
import java.security.NoSuchAlgorithmException; 
public class Git {
	
	//instance variables
	Index index = new Index(); 
	String treeName; 
	String commitName; 
	Commit c; 
	
	
	//git object plan 
		//makes an index file 
		//add blobs with add method 
		//makes tree object
			//has blobs (from index)
			//potential previous tree pointer
		//make tree pointer for commit
		//clear out index file 
		
			

	public static void main (String [] orangutan) throws IOException, NoSuchAlgorithmException {
		
		Git g = new Git(); 
		Git g2 = new Git(); 
		
		//TESTING pre stuff add
		File foo = new File("something.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(foo));
		bw.write("some content");
		bw.close();
		
		File bar = new File("bar.txt");
		BufferedWriter writeInTest = new BufferedWriter(new FileWriter(bar));
		writeInTest.write("bar content");
		writeInTest.close();
		
		
		
		
		System.out.println ("G1"); 
		Blob23 b1 = new Blob23("something.txt"); 
		g.addBlob(b1); 
		
		//TESTING making tree 
		g.makeTree(); 
		
		g.makeCommit(null, "TEST1", "AUTHOR1");
		
		
		
		System.out.println ("COMMIT: " + g.getCommit().getString()) ;
		
		
		
		
		System.out.println ("G2"); 
		Blob23 b2 = new Blob23("bar.txt"); 
		g2.addBlob(b2); 
		g2.makeTree(); 
		
		g2.makeCommit("158e66f032dd7022f153977e4f050924aea8142b", "TEST2", "AUTHOR2");
//		
//		
//		
//		System.out.println ("COMMIT: " + g2.getCommit().getString()) ;

	}
	public Git() throws IOException, NoSuchAlgorithmException{
		
		//make index file and objects folder 
		index.init();
		
		
	}
	
	public void addBlob(Blob23 b) throws NoSuchAlgorithmException, IOException {
		index.addBlobByName(b); 
	}
	
	public void add(String fileName) throws NoSuchAlgorithmException, IOException {
		index.addBlob(fileName); 
	}
	
	public void makeTree() throws IOException, NoSuchAlgorithmException {
		//index is filename : SHA
		//tree is type : SHA : filename
		ArrayList<String> indexList = new ArrayList<String>(); 
		ArrayList<String> list = new ArrayList<String>(); 
		
		
		
		
		//reading index file 
		File f = new File ("index"); 
		FileReader fw = new FileReader(f); 
		BufferedReader read = new BufferedReader(fw); 
		while (read.ready()) {
			String s = read.readLine(); 
			indexList.add(s); 
		}
		read.close(); 
		System.out.println (indexList); 
		
		
		
		
		//adding to tree file 
		for (int i = 0; i < indexList.size(); i++) {
			
			String[] temp = indexList.get(i).split (" : "); 
//			System.out.println (temp[1] + "::::" + temp[0]); 
			list.add("BloB : " + temp[1] + " : " + temp[0]); 
		}
		Tree t = new Tree(list); 
		t.makeNewFile(); 
		treeName = t.getTreeName(); 
		System.out.println ("TreeName" + treeName); 
		
		File f2 = new File ("index"); 
		f2.delete(); 
		File fil = new File("Index.java");
		String path = fil.getAbsolutePath();
		path = path.substring(0, path.length()-10);
		File indx = new File(path +"index");
		indx.createNewFile();
		
	}

	public void makeCommit(String parent, String toSummary, String toAuthor) throws NoSuchAlgorithmException, IOException {
		c = new Commit(parent, toSummary, toAuthor);
		c.setTree(treeName);
		c.writeFile();
	}
	public Commit getCommit() {
		return c; 
	}
	
	
	
}
