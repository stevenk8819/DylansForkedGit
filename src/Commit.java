import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

public class Commit {
	private File parentPointer;
	private File otherPointer;
	private String pTree;
	private String summary;
	private String author;
	private String date;
	private String shawed;
	
	public Commit(File parent, String toBecomePTree, String toSummary, String toAuthor) throws NoSuchAlgorithmException {
		parentPointer = parent;
		otherPointer = null;
		pTree = toBecomePTree;
		summary = toSummary;
		author = toAuthor;
		date = ""+ java.time.LocalDate.now();//Year-Month-Day
		shawed = GenerateHash(summary + date + author + pTree);
	}
	
	public void writeFile() throws IOException {
		File toWrite = new File("./objects/" +shawed);
		toWrite.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(toWrite));
		writer.write(pTree+"\n"+parentPointer.getPath()+"\n"+otherPointer.getPath()+"\n"+author+"\n"+date+"\n"+summary);
		writer.close();
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
