import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Commit {
	private DoublyLinkedList theList;
	private String pTree;
	private String summary;
	private String author;
	private String date;
	private String shawed;
	
	public Commit(Commit parent, String toBecomePTree, String toSummary, String toAuthor) throws NoSuchAlgorithmException {
		theList = new DoublyLinkedList();
		pTree = toBecomePTree;
		summary = toSummary;
		author = toAuthor;
		shawed = makeHash();
	}
	
	public String makeHash() throws NoSuchAlgorithmException {
		return GenerateHash(pTree + summary);
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
