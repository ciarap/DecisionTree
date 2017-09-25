/**
 * @author Ciara Power
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	public static FileInputStream in;
	private boolean debug=true;
	private DecisionTree tree;
	
	public FileReader(String fileName) throws FileNotFoundException{
		in = new FileInputStream(fileName);
		tree=new DecisionTree();
		}
		
		/*
		 * method to read the txt file and create Rule Objects , takes filename in as parameter
		 */
		public void readTextFile(String fileName) throws Exception{  
			File inFile=new File(fileName);
			Scanner inLine = new Scanner(inFile);
			Scanner inTerm = new Scanner(in);
			String delims = "[:]";//each field in the file is separated(delimited) by a :.
			while (inTerm.hasNextLine()) {     //while the txt file has not reached an end
				String termDetails = inTerm.nextLine();
				// parse term details string
				String[] termTokens = termDetails.split(delims);// split the line of data into a String[] by the : divider
				if(termTokens.length==4){
				if(debug) System.out.println(termTokens[0]+" : "+termTokens[1]+" : "+termTokens[2]+" : "+termTokens[3]);
				tree.createRule(termTokens[0],termTokens[1],termTokens[2],termTokens[3]);
				}
				else {
					tree.createRule(termTokens[0],termTokens[1]);
					if(debug) System.out.println(termTokens[0]+" : "+termTokens[1]);
				}
			}
			inLine.close(); // close scanner
		}
		
}
