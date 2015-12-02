/**
 * 
 */
package F15project4;

/**
 * @author Evan S
 *
 */
public class CodeList<E> {
	private Node<E> top; 
	//private Node<E> tail;

	public CodeList() {
		top = null;   // 0000
		//tail = null;
	}

	public void addfirst (E data) {

		//if (top == null) 
		//	tail = top = new Node<E>(data, top);
		if(top == null)
			top = new Node<E>(data, top);
		
		//top = new Node<E> (data, top);

		//		    Node temp = new Node();
		//		    temp.setData(data);
		//		    temp.setNext(top);
		//		    top = temp;
	}

	public void display() {
		//				System.out.println (top.getData());
		//				System.out.println (top.getNext().getData());
		//				System.out.println (top.getNext().getNext().getData());

		Node<E> temp = top;
		System.out.println ("-----------------");
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

		if (top == null) 
			tail = top = new Node<E> (data, top);

		else {
			
			tail.setNext(new Node<E>(data, null));
			tail = tail.getNext();
			
//			Node<E> temp = top;
//			while (temp.getNext() != null) {
//				temp = temp.getNext();
//			}
//
//			Node<E> temp2 = new Node<E>();
//			temp2.setData(data);
//			temp2.setNext(null);
//
//			temp.setNext(temp2);
		}

	} 

	public boolean delete (E data) {

		// check for empty list
		if (top == null) 
			return false;

		// check if top element is the target
		if (top.getData().equals(data)) {
			top = top.getNext();
			if (top.getNext() == null)
				tail = null;
			return true;
		}

		Node<E> temp = top;
		while (temp.getNext() != null) {
			if (temp.getNext().getData().equals(data)) {
				temp.setNext(temp.getNext().getNext());
				if (temp.getNext() == null)
					tail = temp;
				return true;
			}
			temp = temp.getNext();		
		}

		return false;
	}

	public void deleteHalfWay() {


	}


	public static void main (String[] args){
		LinkListFirst<String> list = new 
				LinkListFirst<String>();

		list.addAtEnd("pizza1");
		list.addfirst("pizza2");
		list.addfirst("pizza3");
		list.addfirst("pizza4");
		list.addAtEnd("pizza5");

		list.display();

		list.delete("pizza1");
		list.display();

		list.delete("pizza2");
		list.display();


		//		list.addAtEnd("pizza11");

		//		list.addfirst("pizza3");
		//		list.addfirst("pizza4");
		//		list.addfirst("pizza5");
		//		list.addfirst("pizza6");
		//		list.addfirst("pizza7");
		//		list.addfirst("pizza8");
		//		list.addAtEnd("pizza9");

		//list.display();

	}


}