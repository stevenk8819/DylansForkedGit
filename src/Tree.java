
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
	 ArrayList<String> list = new ArrayList<String>();
	 HashMap<String, String> objs = new HashMap<String, String>();
	 String shadStr;
	 
	public Tree(ArrayList<String> list) {
		String str = "";
		this.list = list;
//		for(int i = 0;i < list.size(); i++) {
//			for(int a = 0; a<5; a++) {
//				str = str + list.get(i).charAt(a);
//			}
//			System.out.println(str);
//			System.out.println(list.get(i).substring(7));
//			objs.put(str,list.get(i).substring(7));
//			
//		}
		
		
	}
	
	public static String getSHA1(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

	    MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	    crypt.reset();
	    crypt.update(password.getBytes("UTF-8"));

	    return new BigInteger(1, crypt.digest()).toString(16);
	}
	
	public void shaTheList() throws NoSuchAlgorithmException, UnsupportedEncodingException {//checker method
		shadStr = getSHA1(list.toString());
		System.out.println("sucuessful");
		
	}
	
	public void makeNewFile() throws IOException, NoSuchAlgorithmException {
		//PrintWriter printer = new PrintWriter();
		
		
//		System.out.println(output.exists());
//		
		
//		writer.close();
		String fileNameS = "";
		
		for (int i = 0; i < list.size(); i++) {
			fileNameS += list.get(i);
			if (i + 1 != list.size()) {
				fileNameS += "\n";
			}
			
			//System.out.println(fileNameString);
		}
		
	 shadStr = getSHA1(fileNameS);
		
		File output = new File("objects/" + shadStr);
		//output.mkdirs();
		output.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		for(int i = 0; i < list.size();i++) {
			writer.write(list.get(i));
			writer.newLine();
		} 
		writer.close(); 
	}
	public String getTreeName() {
		return shadStr; 
	}
	
	

//public static void main(String []args) throws NoSuchAlgorithmException, IOException {
//	ArrayList<String> myList = new ArrayList<String>();
//	myList.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
//	Tree apple = new Tree(myList);
//	apple.shaTheList();
//	apple.makeNewFile();
//	
//}

}