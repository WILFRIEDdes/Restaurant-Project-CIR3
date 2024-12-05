import java.io.*;

public class WritingToFiles {
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("out.txt"); //created a File object called file
		FileWriter fw = new FileWriter(file); //created a FileWriter object called fw
		PrintWriter pw = new PrintWriter(fw); //created a PrintWriter object called pw
		
		pw.println("Line 1");
		pw.println("Line 2");
		pw.println("Line 3");
		
		pw.close();
	}

}
