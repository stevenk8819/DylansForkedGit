import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private String parentPointer;
	private String otherPointer;
	private String pTree;
	private String summary;
	private String author;
	private String date;
	private String shawed;
	private File thisFile; 
	
	public Commit(String parent, String toSummary, String toAuthor) throws NoSuchAlgorithmException, IOException {
//		if (parent == null) {
//			parentPointer = null; 
//		}
		
		
		otherPointer = null;
		summary = toSummary;
		author = toAuthor;
		date = ""+ java.time.LocalDate.now();//Year-Month-Day
		shawed = GenerateHash(summary + date + author);
		
		if (parent != null) {
			shawed = GenerateHash(summary + date + author + parent);
			
			
			//rewriting parent file 
			File f = new File ("objects/" + parent); 
			FileReader fw = new FileReader(f); 
			BufferedReader in = new BufferedReader(fw); 
//			Scanner in = new Scanner(f); 
			String newContents = ""; 
			newContents += in.readLine() + "\n"; 
			newContents += in.readLine() + "\n"; 
			newContents += shawed + "\n"; 
			in.readLine(); 
			newContents += in.readLine() + "\n"; 
			newContents += in.readLine() + "\n"; 
			newContents += in.readLine() + "\n"; 
			
			
			PrintWriter pw = new PrintWriter (f); 
			pw.append(newContents); 
//			System.out.println ("\n" + newContents + "\n"); 
			pw.close(); 
			parentPointer = parent; 
			in.close(); 
		}
		else {
			parentPointer = "";
		}
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
	
//	public String test() {
//		return parentPointer.getPath();
//	}
	
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
