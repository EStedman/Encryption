package F15project4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/***********************************************************************
 * @author Evan Stedman
 * @version Fall 2015
 * This is where all of the methods to alter the linked list is located
 **********************************************************************/
public class List<E> {
	
	/** this is the initial node for the linked list to build off of */
	private Node<E> top; 

	/*******************************************************************
	 * This is the cunstructor that sets the inital node of top to null
	 * since there is no data yet for the list
	 ******************************************************************/
	public List() {
		top = null;   // 0000
	}

	/*******************************************************************
	 * Used to add a new node to the front/top of the linked list
	 * @param data any object to be stored in the list
	 ******************************************************************/
	public void addfirst (E data) {

		
		top = new Node<E>(data, top);
	}

	/*******************************************************************
	 * Used to find the size of the linked list
	 * @return the number the iterator returns is the size
	 ******************************************************************/
	public int size(){
		
		//starts at 1 since counter is based on getNext
		int counter = 1;
		Node<E> temp = top;
		while (temp.getNext() != null) {
			temp = temp.getNext();
			counter++;		
		}
		return counter;
	}
	
	/*******************************************************************
	 * Used to print the list line by line in the console
	 ******************************************************************/
	public void display() {
		
		//iterates through list and prints
		Node<E> temp = top;
		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
	}

	/*******************************************************************
	 * Used to place a new data object at the end/bottom of the list
	 * @param data is the new object to be added to the list
	 ******************************************************************/
	public void addAtEnd (E data) {

		//essentially cuts the top off of a list
		if (top == null) {
			top = new Node<E> (data, top);
		}

		//if not at top then finds the bottom
		else {
			Node<E> temp = top;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}

			//creates new node then adds it
			Node<E> temp2 = new Node<E>();
			temp2.setData(data);
			temp2.setNext(null);
			temp.setNext(temp2);
		}
	} 

	/*******************************************************************
	 * Used to add a data element into the list at a given position
	 * @param added is the new object to be added to the list
	 * @param spot is the spot/position of where the new node with 
	 * data will be added
	 ******************************************************************/
	public void addAt(E added, int spot){
		
		//if top is target then cuts it off
		if (top == null) {
			top = new Node<E> (added, top);
		}

		//goes tot eh specified position in the list
		else {
			Node<E> temp = top;
			for(int i=0; i<spot-1;i++){
				temp = temp.getNext();
			}

			//creates a new node to be added in a particualr spot
			Node<E> temp2 = new Node<E>();
			temp2.setData(added);
			temp2.setNext(temp.getNext());
			temp.setNext(temp2);
		}
	}

	/*******************************************************************
	 * This deletes a node based on a given object input rather than
	 * the objects location in the list
	 * @param data is the actual object to be compared
	 * @return true if the list can have the element and false if the 
	 * list is empty
	 ******************************************************************/
	public boolean delete (E data) {
		
		// check for empty list
		if (top == null) 
			return false;
		
		// check if top element is the target
		if (top.getData().equals(data)) {
			top = top.getNext();
			return true;
		}
		
		//if top isn't the target then parses through till finds a 
		//matching element to delete
		Node<E> temp = top;
		while (temp.getNext() != null) {
			if (temp.getNext().getData().equals(data)) {
				temp.setNext(temp.getNext().getNext());
				return true;
			}
			temp = temp.getNext();		
		}
		return true;
	}
	
	/*******************************************************************
	 * This deletes a node based on its location in the list
	 * @param spot is the location of the node wanting to be erased
	 * @param size is used to help determine how to handle deleting
	 * @return true if the list can have the element and false if the 
	 * list is empty
	 ******************************************************************/
	public boolean delete (int spot, int size) {
		
		// check for empty list
		if (top == null) 
			return false;
		
		// check if top element is the target
		if (spot == 0) {
			top = top.getNext();
			return true;
		}
		
		//used if deleting the last element of the list
		if(spot == size()){
			Node<E> temp = top;
			for(int i=0; i<spot;i++){
				temp = temp.getNext();
			}
			temp.setNext(null);
		}
		
		//used to traverse link to specific spot then delete next link
		Node<E> temp = top;
		for(int i=0; i<spot;i++){
			temp = temp.getNext();
		}
		temp.setNext(temp.getNext().getNext());
		
		return true;
	}
	
	/*******************************************************************
	 * Used to delete all links from one location to another
	 * @param start is the first location to be deleted
	 * @param end is the node to be deleted
	 ******************************************************************/
	public void deleteFrom(int start, int end){
		
		//traverses to first node to be deleted
		Node<E> temp = top;
		for(int i=0; i<start-1;i++){
			temp = temp.getNext();
		}
		
		//traverses to last node to be deleted then deletes inbetween 
		Node<E> temp2 = top;
		for (int x=0;x<end;x++){
			temp2 = temp2.getNext();
			if(temp2.getNext() == null){
				temp.setNext(null);
			}
			else{
				temp.setNext(temp2.getNext());
			}
		}
		
		//used to erase first link is position 0 is given
		if(start == 0)
			top = top.getNext();
	}
	
	/*******************************************************************
	 * Used to save the linked list off into a text file line by line
	 * @param the name of the file where the list will be saved
	 ******************************************************************/
	public void saveList(String fileName){
		
		/** the name of the file to be saved */
		String file_name = fileName;
        try {

            FileWriter fstream = new FileWriter(file_name);
            BufferedWriter out = new BufferedWriter(fstream);
            
            Node<E> temp = top;
    		if(temp != null) {
				while (temp.getNext() != null) {
					out.write((String)temp.getData());
					out.newLine();
					temp = temp.getNext();		
				}
    		}
    		out.write((String) temp.getData());
            out.close();
            System.out.println("File created successfully.");

        	} catch (Exception e) {
        		System.out.println("File not able to be created");
        }
	}
	
	/*******************************************************************
	 * used to convert the entire list into one string
	 * @return the final one line string of the list
	 ******************************************************************/
	public String toString(){
		
		/** holds the final result of single line list */
		String listAsString = "";
		
		//if list is empty returns empty string
		if (top == null) 
			return "";
		
		//copies each individual line/item as one line
		else {
			Node<E> temp = top;
			listAsString += temp.getData();
			while (temp.getNext() != null) {
				listAsString += temp.getNext().getData(); 
				temp = temp.getNext();		
			}
		}
		return listAsString;
	}

	/*******************************************************************
	 * Used to swap out two of the nodes in different locations
	 * @param first is the location of the first node
	 * @param second is the location of the second node to be swapped
	 ******************************************************************/
	public void swap(int first, int second){
		
		// used to hold the first string to be swapped 
		String spot1;
		
		// holds the second string to be swapped 
		String spot2;
		
		// used to traverse through list for first element position
		Node<E> temp = top;
		for(int i=0; i<first;i++){
			temp = temp.getNext();
		}
		spot1 = (String) temp.getData();
		
		// used to traverse through list for second item position
		Node<E> temp2 = top;
		for(int i=0; i<second;i++){
			temp2 = temp2.getNext();
		}
		
		//Does the swapping
		spot2 = (String) temp2.getData();
		temp2.setData((E)spot1);
		temp.setData((E)spot2);
	}
	
	/*******************************************************************
	 * Used to help test some of the methods and create a list.
	 ******************************************************************/
	public static void main (String[] args){
		
		List<String> list = new List<String>();
		
		//testing add methods
		list.addfirst("pizza1");
		list.addAtEnd("pizza2");
		list.addAtEnd("pizza3");
		list.display();
		System.out.println("--------------");
		
		//testing delete
		list.delete(1,list.size());
		list.display();
		System.out.println("--------------");
		
		//testing specific location modifiers
		list.addAt("hey", 2);
		list.display();
		System.out.println("--------------");
		
		//tests saving the document
		list.saveList("SaveTest.txt");
		list.display();
		System.out.println("--------------");
		list.swap(0, 2);
		list.display();
		System.out.println("--------------");
		
		//wanted more elements in list to test
		list.addAtEnd("pizza4");
		list.addAtEnd("pizza5");
		list.addAtEnd("pizza6");
		list.addAtEnd("pizza7");
		list.display();
		System.out.println("--------------");
		list.deleteFrom(1, 6);
		list.display();
		System.out.println("--------------");
	}
}