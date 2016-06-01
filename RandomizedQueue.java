import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] s;
	private int N = 0;
	
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue(){
		s = (Item[]) new Object[1];
	}
	
	public boolean isEmpty(){
		return N == 0 ;
	}
	
	public int size(){
		return N;
	}
	
	public void enqueue(Item item){
		if (N == s.length) {
			resize ( 2 * s.length);
		}
		if(item == null) throw new java.lang.NullPointerException("You are adding Null Item");
		else s[N++] = item;
	}
	
	private void resize(int capacity) {
		Item [] copy = (Item[]) new Object[capacity];
		for(int i = 0; i < N; i++){
			copy[i] = s[i];
		}
		s = copy;
	}
	
	public Item dequeue(){
		if(N == 0) throw new NoSuchElementException("Empty Queue");
		else {
			int x = StdRandom.uniform(N);
			while(s[x]==null){
				x = StdRandom.uniform(N);
			}
			Item item = s[x];
			s[x] = s[N-1];
			s[N-1] = null;
			if(N > 0 && N == s.length/4) {
				resize(s.length/2);
			}
			N = N - 1;
			return item;
		}
	}
	
	public Item sample(){
		if(N == 0) throw new NoSuchElementException("Empty Queue");
		else {
			int x = StdRandom.uniform(s.length);
			while(s[x]==null){
				x = StdRandom.uniform(s.length);
			}
			Item item = s[x];
			return item;
		}
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item>{
		private Item[] inside = s.clone();
		private int count = N;
		public boolean hasNext(){
			return count != 0;
		}
		
		public void remove(){
			throw new UnsupportedOperationException("Invalid Operation");
		}
		
		public Item next(){
			if(count==0){
				throw new NoSuchElementException("No Elements Please");
			}
			else{
				int x = StdRandom.uniform(count);
				Item item = inside[x];
				inside[x] = inside[count-1];
				inside[count-1] = null;
				count = count-1;
				return item;
			}
		}
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		

	}

}
