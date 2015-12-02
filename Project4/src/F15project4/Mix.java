/**
 * 
 */
package F15project4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * @author xxevanxx007
 *
 */
public class Mix implements IMix{

	List<String> secretText = new List<String>();
	List<String> validCommands = new List<String>();

	public String processCommand(String command) {
		
		if(command.equals("Q")){
			System.out.println(secretText.toString());
			return "Q";
		}	
		
		if(command.length() <= 2 || command.length() > 5){
			System.out.println("\nYou entered an incorrect command,"
					+ "please try again");
		}
		
		//Start of swap command /////////////////
		if(command.startsWith("w")){
			if(!command.substring(1,2).equals(" ")||
					!command.substring(3,4).equals(" ")||
					command.length() != 5){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			try{
				int position = Integer.valueOf(command.substring(2,3));
				int position2 = Integer.valueOf(command.substring(4,5));
				if(secretText.size()<position || 
						secretText.size()<position2){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				else{
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
		
		//Start of the save command/////////////
		if(command.startsWith("s")){
			if(!command.substring(1,2).equals(" ")){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			else{
				validCommands.saveList(command.substring(2));
				return secretText.toString();
			}
		}
		
		//Start of the remove command///////////////////////
		if(command.startsWith("r")){
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
				if(position == 0){
					secretText.delete(0,secretText.size());
					validCommands.addAtEnd(command);
				}
				else{
					secretText.delete(position-1, secretText.size());
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
		}
		
		//Start of the place character before command//////////
		if(command.startsWith("b")){
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
				int position = Integer.valueOf(command.substring(4,5));
				if(secretText.size()<position){
					System.out.println("\nYou entered an incorrect "
							+ "command,please try again\n");
					System.out.println("-------------------------------"
							+ "\nCurrent Message: ");
					return secretText.toString();
				}
				if(position == 0){
					secretText.addfirst(command.substring(2,3));
					validCommands.addAtEnd(command);
				}
				else{
					secretText.addAt(command.substring(2,3), position);
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
		
		//fallback in case nothing is entered
		else{
			System.out.println("-------------------------------------"
					+ "\nCurrent Message: ");
			return secretText.toString();
		}
	}
	
	public void setInitialMessage(String message) {
		
		for(int i=0; i<message.length(); i++){
			secretText.addAtEnd(message.substring(i,i+1));
		}
		System.out.println("-----------------------------------------");
		System.out.println("Your message is: " + secretText.toString());
		System.out.println("-----------------------------------------");
	}
	
	public static void main (String[] args){
		Mix coded = new Mix();
		String userText;
		
		Scanner message = new Scanner(System.in);
		System.out.println("Message to encrypt: ");
		userText = message.nextLine();
		coded.setInitialMessage(userText);
		
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