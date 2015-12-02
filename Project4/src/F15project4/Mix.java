/**
 * 
 */
package F15project4;

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
		
		if(command.length()<= 2){
			System.out.println("\nYou entered an incorrect command,"
					+ "please try again\n");
		}
			
		if(command.substring(0,1).equals("b")){
			if(!command.substring(1,2).equals(" ") || 
					!command.substring(3,4).equals(" ")){
				System.out.println("\nYou entered an incorrect command,"
						+ "please try again\n");
				System.out.println("-----------------------------------"
						+ "\nCurrent Message: ");
				return secretText.toString();
			}
			else{
				System.out.println("\n" + command.substring(2,3)+ "\n");
				return secretText.toString();
			}
		}
			
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
	
	
	
	
	/*
	public String processCommand(String command) {
		
		//String temp = command;
		if(command == "Q"){
			JOptionPane.showMessageDialog(null, "Your message is: " 
					+ secretText.toString());
			return command;
		}
		else{
			String temp = JOptionPane.showInputDialog(
				"How would you like to alter your message!\nx & # means"
				+ " cut to clipboard, starting at & to # (inclusive)" +
				"\np # 	means paste from clipboard, start at #\nc & #" +
				" means copy to clipboard, starting at & to # "
				+ "(inclusive)");
			
			JOptionPane.showMessageDialog(null, "Your message is: " 
					+ secretText.toString());
			secretText.display();
			System.out.println("---------");
			System.out.println(temp);
			System.out.println("---------");
			return temp;
		}
	}

	public void setInitialMessage(String message) {
		
		for(int i=0; i<message.length(); i++){
			secretText.addAtEnd(message.substring(i,i+1));
		}
		JOptionPane.showMessageDialog(null, "Your message is: " 
		+ secretText.toString());
		secretText.display();
		
	}
	public static void main (String[] args){
		Mix secretText = new Mix();
		String newCommand = "";
		
		String message = JOptionPane.showInputDialog(
				"Message to be encrypted: ");
		secretText.setInitialMessage(message);
		
		newCommand = JOptionPane.showInputDialog(
				"How would you like to alter your message!\nx & # means"
				+ " cut to clipboard, starting at & to # (inclusive)" +
				"\np # 	means paste from clipboard, start at #\nc & #" +
				" means copy to clipboard, starting at & to # "
				+ "(inclusive)");
		/*if(secretText.processCommand(newCommand) == "Q")
			JOptionPane.showMessageDialog(null, "Goodbye");
		else{
			newCommand = JOptionPane.showInputDialog(
					"How would you like to alter your message!\nx & # means"
					+ " cut to clipboard, starting at & to # (inclusive)" +
					"\np # 	means paste from clipboard, start at #\nc & #" +
					" means copy to clipboard, starting at & to # "
					+ "(inclusive)");
			
		}*/
		
	//}
}
