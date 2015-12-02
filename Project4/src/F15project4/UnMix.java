/**
 * 
 */
package F15project4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/***********************************************************************
 * @author Evan Stedman
 * @version Fall 2015
 * This application takes in a filename and message and using an outside
 * file decrypts the message based on the transformations located in 
 * the outside file.
 **********************************************************************/
public class UnMix implements IUnMix {
	
	/** Linked list of characters representing a message (string) */ 
	private List message;
	@Override
	public String UnMixUsingFile(String filename, String userMessage) {
		String file = filename;
		try (BufferedReader br = new BufferedReader(new 
				FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       System.out.println(line);
		    }
		} catch (FileNotFoundException e) {
			System.out.println("Not a proper file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Not a proper file!");
			e.printStackTrace();
		}
		return null;
	}
	public static void main (String[] args){
		UnMix decode = new UnMix();
		String hiddenText;
		String reverseFile;
		Scanner textScan = new Scanner(System.in);
		System.out.println("Message to unencrypt: ");
		hiddenText = textScan.nextLine();
		System.out.println("File used to unencrypt: ");
		reverseFile = textScan.nextLine();
		decode.UnMixUsingFile(reverseFile, hiddenText);
	}
}