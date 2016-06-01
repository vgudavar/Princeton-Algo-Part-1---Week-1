import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int count;
	
	private class Node {
		private Item item;
		private Node next;
		private Node previous;
	}
	
	public Deque(){
		this.first = null;
		this.last  = null;
		this.count = 0;
	}
	
	public boolean isEmpty(){
		return count==0;
	}
	
	public int size(){
		return count;
		
	}
	
	public void addFirst(Item item){
		if(item==null) throw new java.lang.NullPointerException("You are adding null Item");
		else {
			if(this.size() == 0) {
				first = new Node();
				first.item = item;
				first.next = null;
				last = first;
				last.previous = null;
				count = count + 1;
			}
			else{
				Node oldfirst = first;
				first = new Node();
				first.item = item;
				first.next = oldfirst;
				oldfirst.previous = first;
				first.previous = null;
				count = count + 1;
			}
		}
		
	}
	
	public void addLast(Item item){
		if(item==null) throw new java.lang.NullPointerException("You are adding null Item");
		else if(last == null && first == null){
			Node newlast = new Node();
			newlast.item = item;
			newlast.previous = null;
			first = newlast;
			last = newlast;
			first.next = null;
			newlast = null;
			count = count + 1;
		}
		else{
			Node newlast = new Node();
			newlast.item = item;
			newlast.next = null;
			last.next = newlast;
			newlast.previous = last;
			last = newlast;
			newlast = null;
			count = count + 1;
		}
		
	}
	
	public Item removeFirst(){
		if(this.size() == 0) throw new java.util.NoSuchElementException("Hey No Elements");
		else{
			if(this.size() == 1){
				Item item = first.item; 
				first = null;
				last = first;
				count = count - 1;
				return item;
			}
			else {
				Item item = first.item; 
				first = first.next;
				first.previous = null;
				count = count - 1;
				return item;
			}
		}
	}
	
	public Item removeLast(){
		if(this.size() == 0) throw new java.util.NoSuchElementException("Hey No Elements");
		else if(this.size() == 1) {
			Item item = last.item;
			first = null;
			last = null;
			count = count - 1;
			return item;
		}
		else{
			Item item = last.item;
			last = last.previous;
			last.next = null;
			count = count - 1;
			return item;
			
		}
			
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current != null;
		}
		
		public void remove(){
			throw new UnsupportedOperationException("Invalid Operation");
		}
		
		public Item next(){
			if(current==null){
				throw new NoSuchElementException("No Elements Please");
			}
			else{
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
