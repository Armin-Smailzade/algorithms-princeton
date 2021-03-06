package stackAndQueue;

import java.util.Iterator;

public class StackArrayIterable<Item> implements Iterable<Item>{

	private Item[] s;
	private int N = 0;
	
	@SuppressWarnings("unchecked")
	public StackArrayIterable(){
		s = (Item[]) new Object[2];
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public void push(Item item){
		if(N == s.length) 
			resize(2*s.length);
		s[N++] = item;
	}
	
	private void resize(int capacity){
		
		@SuppressWarnings("unchecked")
		Item[] copy = (Item[]) new Object[capacity];
		for(int i=0; i<N; i++)
			copy[i] = s[i];
		s = copy;
	}
	
	public Item pop(){
		//avoid Loitering
		Item item = s[--N];
		s[N] = null;
		if(N>0 && N == s.length/4) resize(s.length/2);
		return item;
	}
	
	/* Iterator ***/
	public Iterator<Item> iterator(){
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>{
		
		private int i = N;
		
		public boolean hasNext(){ return i>0; }
		public void remove(){  }
		public Item next(){ return s[--i];	}
	}
	
}
