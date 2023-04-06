package edu.du.werthan;
import java.util.NoSuchElementException;

import edu.du.dudraw.DUDraw;
/***
 * Class that holds nodes in the form of a doubly linked list data structure
 * @author tobyw
 *
 * @param 
 */
public class DLL {
	private Node myHead;
	private Node myTail;
	private int mySize;
	public DLL() {
		this.myHead = new Node(null, null, null);
		this.myTail = new Node(null, null, null);
		this.myHead.setNext(this.myTail);
		this.myTail.setPrev(this.myHead);
		this.mySize = 0;
	}
	public int size() {
		return this.mySize;
	}
	public void setHead(Node head) {
		this.myHead = head;
	}
	public void setTail(Node tail) {
		this.myTail = tail;
	}
	public String toString() {
		String result = "[ ";
		Node current = this.myHead.getNext();
		while(current != this.myTail) {
			result = result + current.getVal() + " ";
			current = current.getNext();
		}
		result = result + "]";
		return result;
	}
	public boolean isEmpty() {
		return this.mySize == 0;
	}
	public void addFirst(int[] val) {
		Node newNode = new Node(this.myHead, val, this.myHead.getNext());
		this.myHead.getNext().setPrev(newNode);
		this.myHead.setNext(newNode);
		this.mySize++;
	}
	public void addFirst(Node node) {
		node.setNext(this.myHead.getNext());
		node.setPrev(this.myHead);
		this.myHead.getNext().setPrev(node);
		this.myHead.setNext(node);
		this.mySize++;
	}
	public int[] removeFirst() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		int[] result = this.myHead.getNext().getVal();
		this.myHead.setNext(this.myHead.getNext().getNext());
		this.myHead.getNext().setPrev(this.myHead);
		this.mySize--;
		return result;
	}	
	public void addLast(int[] val) {
		Node addedNode = new Node(this.myTail.getPrev(), val, this.myTail);
		this.myTail.getPrev().setNext(addedNode);
		this.myTail.setPrev(addedNode);
		this.mySize++;
	}
	public int[] removeLast() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		int[] result = this.myTail.getPrev().getVal();
		this.myTail.setPrev(this.myTail.getPrev().getPrev());
		this.myTail.getPrev().setNext(this.myTail);
		this.mySize--;
		return result;
	}
	public int[] getFromIndex(int index) {
		if(index >= this.mySize) {
			throw new IndexOutOfBoundsException("Index is too large!");
		}
		int temp = 0;
		Node current = this.myHead.getNext();
		while(current != this.myTail && temp != index) {
			current = current.getNext();
			temp++;
		}
		return current.getVal();
	}
	public int[] removeAtIndex(int index){
		if(index >= this.mySize) {
			throw new IndexOutOfBoundsException();
		}
		int temp = 0;
		Node prev = this.myHead;
		Node current = this.myHead.getNext();
		while(current != this.myTail && temp != index) {
			prev = current;
			current = current.getNext();
			temp++;
		}
		prev.setNext(current.getNext());
		current.getNext().setPrev(prev);
		this.mySize--;
		return current.getVal();
	}
	public int[] removeBetween(Node node1, Node node2){
		//check if either is null
        if (node1 ==  null || node2 == null)
        {
         throw new IllegalArgumentException("Must have valid parameters");
        }
        //Check for an empty list
		if (this.myHead.getNext() ==  this.myTail)
		{
			throw new NoSuchElementException("Cannot delete from an empty list");
		}
        //check that given nodes are 1 apart
        if(node1.getNext().getNext() != node2)
        {
                throw new IllegalArgumentException("The nodes must have a single node betwen them");
        }
		int[] valueToReturn = null;
	    Node current = this.myHead.getNext();
	    while(current.getNext() != node2){
	    	current = current.getNext();
	    }
	    valueToReturn = current.getVal();
	    current.getNext().setPrev(node1);
	    current.getPrev().setNext(node2);
		return valueToReturn;
	}
	public int search(int[] val) {
		int index = 0;
		Node current = this.myHead.getNext();
		while(val != current.getVal()) {
			current = current.getNext();
			index++;
		}
		return index;
	}
	public Node getHead() {
		return this.myHead;
	}
	public Node getTail() {
		return this.myTail;
	}
}