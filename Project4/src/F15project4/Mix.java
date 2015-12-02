package F15project4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/***********************************************************************
 * @author Evan Stedman
 * @version Fall 2015
 * This application takes a message the user enters and allows the user 
 * to use pre-programmed methods to alter the message then save those
 * alterations in a seperate file so the message can be returned to 
 * its original state. This message is held in a linked list
 **********************************************************************/
public class Mix implements IMix{

	/** This is where the message or encrypted text is kept */
	private List<String> secretText = new List<String>();
	
	/** This is the list that stores valid commands to be undone */
	private List<String> validCommands = new List<String>();
	
	/** Used to hold characters to be copied or pasted */
	private String clipboard = "";

	/*******************************************************************
	 * Given certain commands it will alter the message or copy certain 
	 * elements of the linked list in various ways then saves valid
	 * commands that worked completely
	 * @param command string used to convey how to alter the message
	 * @return the progress or current state of the message
	 ******************************************************************/
	public String processCommand(String command) {
		
		//Used later to break out of the loop to quit entering commands
		if(command.equals("Q")){
			System.out.println(secretText.toString());
			return "Q";
		}	
		
		//No commands can be shorter than 3 characters so this 
		//helps eliminate some of user error
		if(command.length() <= 2){
			System.out.println("\nYou entered an incorrect command,"
					+ "please try again");
		}
		
		/***************************************************************
		 * Doesn't alter the message but copies the characters from a
		 * given position to a new one
		 **************************************************************/
		if(command.startsWith("c")){
			
			//Checks to make sure the command was entered correctly to 
			//eliminate some user error. If not sends user back to 
			//command entry
			if(!command.substring(1,2).equals(" ")||
					!command.substring(3,4).equals(" ")||
					command.length() != 5){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			
			//Used to eliminate the use of non integers in the command
			try{
				int position = Integer.valueOf(command.substring(2,3));
				int position2 = Integer.valueOf(command.substring(4,5));
				if(secretText.size()<position || 
						secretText.size()<position2||
						position > position2){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				else{
					
					//copies elements of the linked list into a separate
					//string to be pasted possibly
					clipboard = secretText.toString();
					clipboard = clipboard.substring
							(position, position2+1);
					//Working command is added to separate list
					validCommands.addAtEnd(command);
				}
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
				}catch(NumberFormatException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}catch(NullPointerException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
		}
		
		/***************************************************************
		 * alters the list by inserting the characters in the clip board
		 * into the list at a given location.
		 **************************************************************/
		if(command.startsWith("p")){
			
			//Eliminates incorrect commands
			if(!command.substring(1,2).equals(" ")||
					command.length()!=3){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			try{
				int position = Integer.valueOf(command.substring(2,3));
				if(secretText.size()<position){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				
				//If trying to add the clip board elements to the first
				//position then a different tactic is needed
				if(position == 0){
					secretText.addfirst(command.substring(2,3));
					validCommands.addAtEnd(command);
				}
				
				//If adding clip board characters to non 0 position then
				//this is the code used
				else{
					if(position == 0){
						for(int i = 0; i < clipboard.length();i++){
							
							//since adding to the list affects the 
							//numbers we do it backwards
							secretText.addfirst(clipboard.substring(
									clipboard.length()-i-1,
									clipboard.length()-i));
						}
					}
					
					//since adding to the list affects the 
					//numbers we do it backwards
					for(int i = 0; i < clipboard.length();i++){
						secretText.addAt(clipboard.substring(
								clipboard.length()-i-1,
								clipboard.length()-i), position);
					}
					
					//Working command is sent to list
					validCommands.addAtEnd(command);
				}
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
				}catch(NumberFormatException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
		}
		
		/***************************************************************
		 * alters the list by removing some elements in the list and 
		 * saving those removed elements into the clip board
		 **************************************************************/
		if(command.startsWith("x")){
			
			//eliminates user error from the command and send them back
			//to try the same command again or a different command
			if(!command.substring(1,2).equals(" ")||
					!command.substring(3,4).equals(" ")||
					command.length() != 5){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			
			//used to ensure proper command with integers was used
			try{
				int position = Integer.valueOf(command.substring(2,3));
				int position2 = Integer.valueOf(command.substring(4,5));
				if(secretText.size()<position || 
						secretText.size()<position2||
						position > position2){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				else{
					
					//converts the elements to be removed into a string
					//for the clip board
					clipboard = secretText.toString();
					clipboard = clipboard.substring
							(position, position2+1);
					
					//then goes through and deletes the links to those
					//nodes
					secretText.deleteFrom(position, position2);
					
					//adds the valid command to the list
					validCommands.addAtEnd(command);
				}
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
				}catch(NumberFormatException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}catch(NullPointerException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
		}
		
		/***************************************************************
		 * alters the list by swapping the data of one element with that
		 * of another element.
		 **************************************************************/
		if(command.startsWith("w")){
			
			//elimnates user error
			if(!command.substring(1,2).equals(" ")||
					!command.substring(3,4).equals(" ")||
					command.length() != 5||
					secretText.size()<2){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			try{
				int position = Integer.valueOf(command.substring(2,3));
				int position2 = Integer.valueOf(command.substring(4,5));
				if(secretText.size()<position || position == position2||
						secretText.size()<position2){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				else{
					
					//uses the swap function of the linked list to 
					//modify the element(s) data
					secretText.swap(position, position2);
					validCommands.addAtEnd(command);
				}
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
				}catch(NumberFormatException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}catch(NullPointerException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
		}
		
		/***************************************************************
		 * Saves all of the valid commands into a separate text file
		 **************************************************************/
		if(command.startsWith("s")){
			if(!command.substring(1,2).equals(" ")){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			else{
				
				//uses save method in list to save commands
				validCommands.saveList(command.substring(2));
				return secretText.toString();
			}
		}
		
		/***************************************************************
		 * alters the list by removing any links to the specified node
		 * in a particular position
		 **************************************************************/
		if(command.startsWith("r")){
			
			//eliminates user error and redirects
			if(!command.substring(1,2).equals(" ") ||
					command.length()!= 3){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			else{
				try{
				int position = Integer.valueOf(command.substring(2,3));
				if(secretText.size()<position){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				
				//if the first node is deleted it is handled differently
				if(position == 0){
					String temp;
					temp = secretText.toString().substring
							(position, position+1);
					secretText.delete(0,secretText.size());
					validCommands.addAtEnd(command + " " + temp );
				}
				
				//If the node to be deleted isn't the first then it 
				//deleted the node at a certain location
				else{
					
					//uses the delete with a position marker to 
					//delete a node in a particular position
					String temp2;
					temp2 = secretText.toString().substring
							(position, position+1);
					secretText.delete(position, secretText.size());
					
					
					//saves the valid command to other list
					validCommands.addAtEnd(command + " " + temp2 );
				}
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
				}catch(NumberFormatException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}catch(NullPointerException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
			}
		}
		
		/***************************************************************
		 * alters the list by adding a character to the specified spot
		 **************************************************************/
		if(command.startsWith("b")){
			
			//eliminate error and redirect
			if(!command.substring(1,2).equals(" ") || 
					!command.substring(3,4).equals(" ")||
					command.length()!= 5){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			else{
				try{
					
				//gets the integer of the last part of the command
				int position = Integer.valueOf(command.substring(4,5));
				if(secretText.size()<position){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				
				//if adding to front the then this is used
				if(position == 0){
					secretText.addfirst(command.substring(2,3));
					validCommands.addAtEnd(command);
				}
				
				//Otherwise this is used
				else{
					
					//used addat method to add element to specific spot
					secretText.addAt(command.substring(2,3), position);
					
					//adds working command to list
					validCommands.addAtEnd(command);
				}
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
				}catch(NumberFormatException nfe){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
			}
		}
		
		//fallback in case nothing is entered or wrong command is used
		else{
			System.out.println("\nYou entered an incorrect "
					+ "command,please try again\n");
			System.out.println("-------------------------------------"
					+ "\nCurrent Message: ");
			return secretText.toString();
		}
	}
	
	/*******************************************************************
	 * Takes the first/actual message to be adjusted and stores it into
	 * a linked list
	 * @param message is the text to be encrypted
	 ******************************************************************/
	public void setInitialMessage(String message) {
		
		//adds to otherwise it would be in reverse order
		for(int i=0; i<message.length(); i++){
			secretText.addAtEnd(message.substring(i,i+1));
		}
		System.out.println("-----------------------------------------");
		System.out.println("Your message is: " + secretText.toString());
		System.out.println("-----------------------------------------");
	}
	
	/*******************************************************************
	 * Used to start the code and retrieve the messages and alterations
	 ******************************************************************/
	public static void main (String[] args){
	
		/** the mixed message */
		Mix coded = new Mix();
		
		/** the user inputted text to be secured */
		String userText;
		
		Scanner message = new Scanner(System.in);
		System.out.println("Message to encrypt: ");
		userText = message.nextLine();
		coded.setInitialMessage(userText);
		
		//prints first list of commands and reads them in
		System.out.println("How would you like to alter your "
			+ "message!\nb c # 	means insert char 'c' before position "
			+ "#\nr # 	means remove a char at position #\nw & # 	"
			+ "means switch characters at position & with #\ns filename"
			+ " means, to save off the set of undo commands into text "
			+ "file named 'filename'\nx & # means cut to clipboard, "
			+ "starting at & to # (inclusive)\np # 	means paste from "
			+ "clipboard, start at #\nc & # means copy to clipboard, "
			+ "starting at & to # (inclusive)");
		Scanner command = new Scanner(System.in);
		
		//loop that breaks if Q is entered to continue with commmands
		while(command.hasNext()){
			String progress = command.nextLine();
			if(progress.equals("Q")){
				break;
			}
			System.out.println("How would you like to alter your "
					+ "message!\nb c # 	means insert char 'c' before "
					+ "position #\nr # 	means remove a char at position"
					+ " #\nw & # means switch characters at position & "
					+ "with #\ns filename means, to save off the set of"
					+ " undo commands into text file named 'filename'"
					+ "\nx & # means cut to clipboard, starting at & to"
					+ " # (inclusive)\np # 	means paste from clipboard,"
					+ " start at #\nc & # means copy to clipboard, "
					+ "starting at & to # (inclusive)");
			progress = coded.processCommand(progress);
			System.out.println(progress + "\n");
		}
		
	}
}