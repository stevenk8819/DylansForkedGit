import java.util.*; 
import java.io.*;
import java.security.NoSuchAlgorithmException; 
public class Git {

	//instance variables
	Index index = new Index(); 
	String treeName; 
	String commitName; 
	Commit c; 
	ArrayList<String> afterDeleted = new ArrayList<String>(); 

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

		//TESTING pre stuff add
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





//		System.out.println ("C1"); 
		Blob23 b1 = new Blob23("something.txt"); 
		g.addBlob(b1); 
		g.makeCommit(null, "TEST1", "AUTHOR1");
		System.out.println ("COMMIT1: " + g.getCommit().getString()) ;

		
//		System.out.println ("C2"); 
		Blob23 b2 = new Blob23("bar.txt"); 
		g.addBlob(b2); 
		g.makeCommit(g.getString(), "TEST2", "AUTHOR2");
		System.out.println ("COMMIT2: " + g.getCommit().getString()) ;

		
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

	public Index getIndex() {
		return index; 
	}


	public Git() throws IOException, NoSuchAlgorithmException{

		//make index file and objects folder 
		index.init();
		File f = new File ("objects/head"); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		clearBranches(); 
		File f2 = new File("objects/branches");  
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(f2));

	}


	




	public void delete(String toDelete) throws IOException {
		File f = new File ("index"); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
		bw.append("* " + toDelete + "\n"); 
		bw.close(); 
	}
	public void deleteFile(String toDelete) throws IOException {
		//for adding blobs to put into tree 
		File f = new File ("objects/blobList"); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		

		//getting to first tree (of head)
		File headFile = new File("objects/head"); 
		BufferedReader br = new BufferedReader(new FileReader(headFile)); 
		String firstCommitName = br.readLine(); 
		BufferedReader br2 = new BufferedReader(new FileReader("objects/" + firstCommitName)); 
		String firstTreeName = br2.readLine(); 
		//		System.out.println ("FIRST TREE:" + firstTreeName); 

		//traversal 
		traverseTreeForBlobs(firstTreeName, toDelete, "objects/blobList"); 
		
	}

	public void traverseTreeForBlobs(String treeName, String deleteName, String blobListFile) throws IOException {
		
		
//		System.out.println ("ADDTREELIST:" + treeName); 
//		File f1 = new File ("objects/treeList"); 
//		BufferedWriter bw1 = new BufferedWriter(new FileWriter(f1, true)); 
//		bw1.append(treeName + "\n"); 
//		bw1.close(); 
		
		
		
		BufferedReader br = new BufferedReader(new FileReader("objects/" + treeName)); 
		while(br.ready()) {
			String line = br.readLine(); 
			String firstFour = line.substring(0,4); 


			if (firstFour.equals("Blob")) {//if reads in blob line 
//				System.out.println (line.substring(50)); 
				
				if (line.substring(50).equals(deleteName)){ //if the blob is the deleted one 
//					System.out.println ("FOUND IT IN THIS TREE:" + treeName);
//					System.out.println (); 
//					
					/*
					once we find the blob, we go to the tree that has it
					- tree called treeName
					- then we read from the treeName to get the next Tree
					- we add that next Tree to BloblistFile
					
					
					
					
					
					
					*/
					
					BufferedReader in = new BufferedReader(new FileReader("objects/" + treeName)); 
					while(in.ready()) {
						String temp = in.readLine(); 
						if (temp.substring(0,4).equals("Tree")) {
//							System.out.println ("YAY" + temp.substring(4)); 
							File f1 = new File ("objects/blobList"); 
							BufferedWriter bw = new BufferedWriter(new FileWriter(f1, true)); 
							bw.append(temp + "\n"); 
							bw.close(); 
							return; 
						}
					
					}
					
					//remove this tree from treeList
//					ArrayList<String> remainingTrees = new ArrayList<String>(); 
//					BufferedReader treeListReader = new BufferedReader(new FileReader("objects/treeList")); 
//					while(treeListReader.ready()) {
//						String newLine = treeListReader.readLine(); 
//						if (newLine.equals(treeName)){
//							
//						}
//						else {
//							remainingTrees.add(newLine); 
//						}
//					}
//					File f = new File ("objects/treeList"); 
//					BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//					for (int i = 0; i < remainingTrees.size(); i++) {
//						bw.append(remainingTrees.get(i)); 
//					}
//					bw.close(); 
					
					
				}
				else {//if it doesn't match 
					File f = new File ("objects/blobList"); 
					BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
					bw.append("Blob : " + line.substring(7) + "\n"); 
					bw.close(); 

				}
			}
			else if (firstFour.equals("Tree")){ //reads in a tree line
				
				
//				System.out.println ("ADDTREELIST:" + line.substring(7,47)); 
//				f = new File ("objects/treeList"); 
//				bw = new BufferedWriter(new FileWriter(f, true));
//				bw.append(line.substring(7,47)); 
//				bw.close(); 
				
				
				
				traverseTreeForBlobs(line.substring(7,47), deleteName, blobListFile); 
				
			}

		}
	}






	public String getString() {
		return c.getString(); 
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
//		System.out.println (indexList); 




		//adding to tree file 
		for (int i = 0; i < indexList.size(); i++) {

			String[] temp = indexList.get(i).split (" : "); 
			//			System.out.println (temp[1] + "::::" + temp[0]); 
			list.add("Blob : " + temp[1] + " : " + temp[0]); 
		}
		Tree t = new Tree(list); 
		t.makeNewFile(); 
		treeName = t.getTreeName(); 
//		System.out.println ("TREENAME:" + treeName); 

		File f2 = new File ("index"); 
		f2.delete(); 
		File fil = new File("Index.java");
		String path = fil.getAbsolutePath();
		path = path.substring(0, path.length()-10);
		File indx = new File(path +"index");
		indx.createNewFile();

	}
	public void makeTreeWithTree(String parentCom) throws IOException, NoSuchAlgorithmException {
		//index is filename : SHA
		//tree is type : SHA : filename
		ArrayList<String> indexList = new ArrayList<String>(); 
		ArrayList<String> list = new ArrayList<String>(); 


		


		//reading index file 
		File f = new File ("index"); 
		FileReader fw = new FileReader(f); 
		BufferedReader read = new BufferedReader(fw); 
		boolean deleting = false; 
		
		
		
		while (read.ready()) {
			String s = read.readLine(); 
			if (s.substring(0,1).equals("*")){
				deleting = true; 
//				System.out.println (s.substring(2) + "!!!!!!!"); 
				deleteFile(s.substring(2)); 
//				BufferedReader reader = new BufferedReader(new FileReader("objects/blobList")); 
//				while(reader.ready()) {
//					System.out.println ("READ IN:" + reader.readLine()); 
//				}
			}
			else {
				indexList.add(s); 
			}
		}
		read.close(); 
//		System.out.println (indexList); 

		//adding blobs to file 
		for (int i = 0; i < indexList.size(); i++) {

			String[] temp = indexList.get(i).split (" : "); 
			//			System.out.println (temp[1] + "::::" + temp[0]); 
			list.add("Blob : " + temp[1] + " : " + temp[0]); 
			
		}
		
		
		
		if (deleting == false) {//add the parent tree and other files
			//adding parent tree to file 
			String commitName = parentCom; 
			File f2 = new File("objects/" + commitName); 
			FileReader fw2 = new FileReader(f2); 
			BufferedReader read2 = new BufferedReader(fw2); 
			String parentTree = read2.readLine(); 
			list.add("Tree : " + parentTree); 
		}
		else {//add files from blobList
			ArrayList<String> temp = new ArrayList<String>(); 
			BufferedReader afterReader = new BufferedReader(new FileReader("objects/blobList")); 
			while(afterReader.ready()) {
				list.add(afterReader.readLine()); 
			}
//			System.out.println ("\n" + list); 
		}
		deleteBlobList(); 
	


		
		

		
		Tree t = new Tree(list); 
		t.makeNewFile(); 
		treeName = t.getTreeName(); 
//		System.out.println ("TREENAME:" + treeName); 
		
		
		
		
		File fdelete = new File ("index"); 
		fdelete.delete(); 
		//call delete and rewrite to tree
		File fil = new File("Index.java");
		String path = fil.getAbsolutePath();
		path = path.substring(0, path.length()-10);
		File indx = new File(path +"index");
		indx.createNewFile();

	}

	public void makeCommit(String parent, String toSummary, String toAuthor) throws NoSuchAlgorithmException, IOException {
		if (parent == null) {
			c = new Commit(parent, toSummary, toAuthor);
			makeTree(); 
			c.setTree(treeName);
			c.writeFile();
			writeToHead(c); 
			writeToBranches(c); 
		}
		else {
			c = new Commit(parent, toSummary, toAuthor);
			makeTreeWithTree(parent); 
			c.setTree(treeName);
			c.writeFile();
			writeToHead(c); 
			writeToBranches(c); 
		}

	}
	public Commit getCommit() {
		return c; 
	}

	
	public void writeToBranches(Commit c) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("objects/branches",true)); 
		bw.append("branchReferences : " + c.getString() + "\n"); 
		bw.close(); 
	}
	
	public void clearBranches() throws IOException{
		File f = new File("objects/branches"); 
		f.delete(); 
		BufferedWriter bw = new BufferedWriter(new FileWriter("objects/branches")); 
		bw.append(""); 
		bw.close(); 
	}
	
	public void writeToHead(Commit c) throws IOException {
		String content = c.getString(); 
		File f = new File("objects/head"); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(f)); 
		bw.write(content); 
		bw.close(); 
	}
	
	public void switchBranch(int number) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("objects/branches")); 
		for (int i = 0; i < number; i++) {
			br.readLine(); 
		}
		String s = br.readLine(); 
		File f = new File("objects/head"); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(f)); 
		bw.write(s.substring(19)); 
		bw.close(); 
		//branches done
	}
	
	public void deleteBlobList() {
		File f = new File("objects/blobList"); 
		f.delete(); 
	}



}