/**
 * 
 */
package F15project4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * @author xxevanxx007
 *
 */
public class List<E> {
	private Node<E> top; 

	public List() {
		top = null;   // 0000
	}

	public void addfirst (E data) {

			top = new Node<E>(data, top);
		

		//		    Node temp = new Node();
		//		    temp.setData(data);
		//		    temp.setNext(top);
		//		    top = temp;
	}

	public int size(){
		int counter = 1;
		Node<E> temp = top;
		while (temp.getNext() != null) {
			temp = temp.getNext();
			counter++;		
		}
		return counter;
	}
	
	public void display() {
		//				System.out.println (top.getData());
		//				System.out.println (top.getNext().getData());
		//				System.out.println (top.getNext().getNext().getData());

		Node<E> temp = top;
		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
	}

	public int count() {
		int count = 0;

		Node<E> temp = top;
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}


		return count;
	}

	public void addAtEnd (E data) {

		if (top == null) {
			top = new Node<E> (data, top);
		}

		else {
			Node<E> temp = top;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}

			Node<E> temp2 = new Node<E>();
			temp2.setData(data);
			temp2.setNext(null);

			temp.setNext(temp2);
		}
	} 

	public void addAt(E added, int spot){
		if (top == null) {
			top = new Node<E> (added, top);
		}

		else {
			Node<E> temp = top;
			for(int i=0; i<spot-1;i++){
				temp = temp.getNext();
			}

			Node<E> temp2 = new Node<E>();
			temp2.setData(added);
			temp2.setNext(temp.getNext());

			temp.setNext(temp2);
		}
	}

	public boolean delete (E data) {
		
		// check for empty list
		if (top == null) 
			return false;
		
		// check if top element is the target
		if (top.getData().equals(data)) {
			top = top.getNext();
			return true;
		}
		
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
	
	public boolean delete (int spot, int size) {
		
		// check for empty list
		if (top == null) 
			return false;
		
		// check if top element is the target
		if (spot == 0) {
			top = top.getNext();
			return true;
		}
		if(spot == size()){
			Node<E> temp = top;
			for(int i=0; i<spot;i++){
				temp = temp.getNext();
			}
			temp.setNext(null);
		}
		
		
		Node<E> temp = top;
		for(int i=0; i<spot;i++){
			temp = temp.getNext();
		}
		temp.setNext(temp.getNext().getNext());
		
		return true;
	}
	
	public void saveList(String fileName){
		String file_name = fileName;
        try {

            FileWriter fstream = new FileWriter(file_name);
            BufferedWriter out = new BufferedWriter(fstream);

            
            
            

            ListIterator itr = account.listIterator();
            while (itr.hasNext()) {
                Account element = (Account) itr.next();
                out.write(element + "\n");
            }

            out.close();
            System.out.println("File created successfully.");

        } catch (Exception e) {
        }
	}
	
	public String toString(){
		String listAsString = "";
		
		if (top == null) 
			return "";
		
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
	public static void main (String[] args){
		
		
		List<String> list = new List<String>();
		list.addfirst("pizza1");
		list.addAtEnd("pizza2");
		list.addAtEnd("pizza3");
		list.display();
		System.out.println("--------------");
		list.delete(1,list.size());
		list.display();
		System.out.println("--------------");
		list.addAt("hey", 2);
		list.display();
		System.out.println("--------------");
	}


}