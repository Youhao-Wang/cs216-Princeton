package alg_hw2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
	private Node head, tail;
	private static int size;

	private class Node
	{
		Item  item;
		Node next;
		Node prev;
	}

	public Deque()                           // construct an empty deque
	{
		head = null;
		tail = null;
		size = 0;
	}
	   
	public boolean isEmpty()                 // is the deque empty?
	{
		if (size == 0)
			return true;
		else
			return false;

	}
	   
	 public int size()                        // return the number of items on the deque
	{
		return size;	   
	}
	   
	public void addFirst(Item item)          // add the item to the front
	{
		if(size ==0){
			Node temp = new Node();
			temp.item = item;
			temp.prev = null; 
			temp.next = null;
			head = temp;
			tail = temp;
			size ++;
		}
		else
		{
			Node temp = new Node();
			temp.item = item;
			temp.next = head;
			temp.prev = null;
			head = temp;
			size ++;
		}

	}
	   
	public void addLast(Item item)           // add the item to the end
	{
	    if(size == 0)
		{
		  	Node temp = new Node();
		  	temp.item = item;
		  	temp.prev = null; 
			temp.next = null;
			head = temp;
			tail = temp;
			size ++;
		}
		else {
			Node temp =new Node();
			temp.item = item;
			temp.prev = tail;
			tail.next = temp;
			temp.next = null;
			tail = temp;
			size ++;
		}
	}
	   
	public Item removeFirst()                // remove and return the item from the front
   {
		if (size ==1){
			Item result = head.item;
			head = null;
			tail = null;
			size --;
			return result;
		} 
		else{
			Item result = head.item;
			head = head.next;
			head.prev = null;
			size --;
			return result;
		} 
   }
	   
	public Item removeLast()                 // remove and return the item from the end
	{
		 if (size == 1){
			Item result = tail.item;
			tail = null;
			head = null;
			size --;
			return result;
		}
		else{
			Item result = tail.item;
			tail = tail.prev;
			tail.next = null;
			size --;
			return result;
		}
	}
	   
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
	   return new DequeIterator();
   }

    private class DequeIterator implements Iterator<	Item>
    {
    	private Node current = head;

    	public boolean hasNext()
    	{
    		return current != null;
    	}

    	public Item next()
    	{
    		Item item = current.item;
    		current = current.next;
    		return item;
    	}

    }


    private String tostring( )
    {
    	 int i;
    	 Item item;
   		 String result = "[  ";

    	Node cur = head;

   		 while (cur != null) {
    		 item = cur.item;
     		 result = result + item.toString() + "  ";
     		 cur = cur.next;
   		 }

   		 result = result + "]";
    	return result;
    }


	public static void main(String[] args)   // unit testing
	{
		Deque test1 = new Deque();
		System.out.println("is empty?: true  "+test1.isEmpty());
		test1.addFirst(6);
		test1.addFirst(7);
		test1.addFirst(3);
		System.out.println("the list after addFirst()  is [3,7,6]: "+test1.tostring() );
		System.out.println("is empty?: false  "+test1.isEmpty());
		
		test1.addLast(5);
		test1.addLast(9);
		test1.addLast(12);
		System.out.println("the list after addLast()  is [3,7,6,5,9,12]: "+test1.tostring() );
		System.out.println("the size is 6:  "+test1.size);
		System.out.println();
		
		test1.removeFirst();
		test1.removeFirst();
		System.out.println("the list after removeFirst()  is [6,5,9,12]: "+test1.tostring() );
		test1.removeLast();
		test1.removeLast();
		System.out.println("the list after removeFirst()  is [6,5]: "+test1.tostring() );
		
		test1.addLast(5);
		test1.addLast(9);
		test1.addLast(12);
		test1.addFirst(6);
		test1.addFirst(7); 
		test1.addFirst(3);
		System.out.println("the list is :   "+test1.tostring() );
		System.out.println(test1.iterator( ).toString() );
   }
}