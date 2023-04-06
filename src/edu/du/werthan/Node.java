package edu.du.werthan;

public class Node {
	private int[] myPos = new int[2];
	private Node myNext;
	private Node myPrev;
	public Node(Node prev, int[] pos, Node next) {
		this.myPos = pos;
		this.myNext = next;
		this.myPrev = prev;
	}
	public int[] getVal() {
		return this.myPos;
	}
	public void setVal(int[] val) {
		this.myPos = val;
	}
	public Node getNext(){
		return this.myNext;
	}
	public Node getPrev(){
		return this.myPrev;
	}
	public void setPos(int x, int y) {
		this.myPos[0] = x;
		this.myPos[1] = y;
	}
	public void setNext(Node next) {
		this.myNext = next;
	}
	public void setPrev(Node prev) {
		this.myPrev = prev;
	}
	public String toString() {
		return "" + "( " + this.myPos[0] + ", " + this.myPos[1] + " )";
	}
}
