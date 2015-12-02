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
	private List<String> message = new List<String>();
	
	/** Linked list of commands that altered the message */ 
	private List<String> commands = new List<String>();
	
	/*******************************************************************
	 * This takes the file with the valid commands used to alter the 
	 * message along with the final result of the message and undoes 
	 * the commands to get the original text
	 * @param filename the name of the file with commands used
	 * @param userMessage the final result of the altered message
	 * @return the original message/text 
	 ******************************************************************/
	public String UnMixUsingFile(String filename, String userMessage) {
		
		//The final/unaltered message 
		String originalText = "";
		
		//used to ensure the entered message is correct
		for(int i=0; i<userMessage.length(); i++){
			message.addAtEnd(userMessage.substring(i,i+1));
		}
		System.out.println("The altered message reads: \n"+ 
		message.toString()+"\n------------------------");
		
		//the file that has the commands is transferred to local string
		String file = filename;
		
		//used to read in the file line by line
		try (BufferedReader br = new BufferedReader(new 
			FileReader(file))) {
		    	String line;

		    	//breaks if the read in file is null/at the end
		    	while ((line = br.readLine()) != null) {
		    		
		    		//Stores the commands used in reverse order in 
		    		//a separate list
		    		commands.addfirst(line);
		    	}
		} catch (FileNotFoundException e) {
			System.out.println("Not a proper file or not altered");
		} catch (IOException e) {
			System.out.println("Not a proper file or not altered");
		}
		
		//Used to undo alterations based on order in commands list
		for(int i=0; i<commands.toString().length();i=i+5){;
			if(commands.toString().substring(0+i, 1+i).equals("r")){
				
				int position = Integer.valueOf(
						commands.toString().substring(2+i, 3+i));
				if(position == 0){
					message.addfirst(commands.toString().
							substring(4, 5));
				}
				else{
					message.addAt(commands.toString().
							substring(4+i, 5+i), position-1);
				}
			}
			if(commands.toString().substring(0+i, 1+i).equals("b")){
				int position = Integer.valueOf(
						commands.toString().substring(4+i, 5+i));
				if(position == 0){
					message.delete(0,message.size());
				}
				else{
					message.delete(position, message.size());
				}
			}
		}
		
		return message.toString();
	}
	public static void main (String[] args){
		UnMix decode = new UnMix();
		String hiddenText;
		String reverseFile;
		String origin;
		Scanner textScan = new Scanner(System.in);
		System.out.println("Message to unencrypt: ");
		hiddenText = textScan.nextLine();
		System.out.println("File used to unencrypt: ");
		reverseFile = textScan.nextLine();
		origin = decode.UnMixUsingFile(reverseFile, hiddenText);
		System.out.println("The original message read: \n"+origin);
	}
}