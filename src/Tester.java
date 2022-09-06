import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tester {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		Blob23 bloo = new Blob23("TheTextFile");
		bloo.shaTheFile();
		System.out.println(bloo.getFileContents());
		System.out.println(bloo.getShawedString());
		System.out.println(bloo.getFilePath());
		bloo.createTheNewSha1File();
		String str = "\\n";
		System.out.println(str);
		
	}
}
