import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;


public class Deque<Item> implements Iterable<Item> {

	private DequeueNode start;
	private DequeueNode end;
	private int size;
	
   public Deque() {
   }
   
   public boolean isEmpty() {
	   return (size == 0);
   }
   
   public int size() {
	   return 0;
   }
   
   public void addFirst(Item item) {
	   // add the item to the front
	   if (start == null) {
		   DequeueNode current = new DequeueNode();
		   current.item = item;
		   start = current;
		   end = current;
	   } else {
		   DequeueNode current = start;
		   start = new DequeueNode();
		   start.item = item;
		   start.next = current;
	   }
	   size++;
   }
   
   public void addLast(Item item) {
	   // add the item to the end
	   if (end == null) {
		   DequeueNode current = new DequeueNode();
		   current.item = item;
		   start = current;
		   end = current;
	   } else {
		   DequeueNode current = end;
		   end = new DequeueNode();
		   end.item = item;
		   current.next = end;
	   }
	   size++;
   }
   
	public Item removeFirst() {
		// remove and return the item from the front
		if (start == null) {
			throw new NoSuchElementException();
		}
		DequeueNode current = start;
		start = current.next;
		size--;
		return current.item;
	}

	public Item removeLast() {
		// remove and return the item from the end
		if (end == null) {
			throw new NoSuchElementException();
		}
		DequeueNode current = end;
		start = current.next;
		size--;		
		return current.item;
	}

   public Iterator<Item> iterator() {
	   // return an iterator over items in order from front to end
	   return null;
   }
   
   public static void main(String[] args) {
	   // unit testing (optional)
   }
   
   private class DequeueNode {
		DequeueNode next;
		Item item;
	}
}


