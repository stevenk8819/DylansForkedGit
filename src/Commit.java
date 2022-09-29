import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.Scanner;  

public class Commit {
	private File parentPointer;
	private File otherPointer;
	private String pTree;
	private String summary;
	private String author;
	private String date;
	private String shawed;
	private File thisFile; 
	
	public Commit(File parent, String toSummary, String toAuthor) throws NoSuchAlgorithmException, FileNotFoundException {
//		if (parent == null) {
//			parentPointer = null; 
//		}
		
		if (parent != null) {
			File f = new File ("test/objects/" + parent); 
			Scanner in = new Scanner(f); 
			String newContents = ""; 
			newContents += in.nextLine() + "\n"; 
			newContents += in.nextLine() + "\n"; 
			newContents += pTree + "\n"; 
			in.nextLine(); 
			newContents += in.nextLine() + "\n"; 
			newContents += in.nextLine() + "\n"; 
			newContents += in.nextLine() + "\n"; 
			
			
			PrintWriter pw = new PrintWriter (f); 
			pw.append(newContents); 
			pw.close(); 
			
		}
		else {
			parentPointer = parent;
		}
		
		otherPointer = null;
		summary = toSummary;
		author = toAuthor;
		date = ""+ java.time.LocalDate.now();//Year-Month-Day
		shawed = GenerateHash(summary + date + author + pTree);
	}
	
	public File getThisFile() {
		return thisFile; 
	}
	
	public void writeFile() throws IOException {
		thisFile = new File("./objects/" + shawed);
//		thisFile = new File(shawed);
		thisFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(thisFile));
		//adding to file 
		
		String text = ""; 
		text += pTree + "\n"; 
		if (parentPointer != null) {
			text += parentPointer + "\n"; 
		}
		else {
			text += "\n"; 
		}
		if (otherPointer != null) {
			text += otherPointer + "\n"; 
		}
		else {
			text += "\n"; 
		}
		text += author + "\n"; 
		text += date + "\n"; 
		text += summary + "\n"; 
				
				
				
		writer.write(text); 
		writer.close();
	}
	
	public String getString() {
		return shawed; 
	}
	
	public void setTree(String s) {//setting pointer for tree of commit 
		pTree = s; 
	}
	
	public String test() {
		return parentPointer.getPath();
	}
	
	public String getDate() {
		return date;
	}
	
    public String GenerateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest objSHA = MessageDigest.getInstance("SHA-1");
        byte[] bytSHA = objSHA.digest(input.getBytes());
        BigInteger intNumber = new BigInteger(1, bytSHA);
        String strHashCode = intNumber.toString(16);
		
        // pad with 0 if the hexa digits are less then 40.
        while (strHashCode.length() < 40) {
            strHashCode = "0" + strHashCode;
        }
        return strHashCode;
    }
}
