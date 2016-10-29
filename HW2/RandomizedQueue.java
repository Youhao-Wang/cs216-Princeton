package alg_hw2;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int n;
	
	   public RandomizedQueue()                 // construct an empty randomized queue
	   {
		   a = (Item[]) new Object[2];
		   n = 0;
	   }
	   
	   public boolean isEmpty()                 // is the queue empty?
	   {
		   return n ==0;
	   }
	   
	   public int size()                        // return the number of items on the queue
	   {
		   return n;
	   }

	   private void resize(int capacity)
	   {
	   		Item[] copy = (Item[]) new Object [capacity];
	   		for (int i =0; i < n; i++)
	   		{
	   			copy[ i ] = a[ i ];
	   		}
	   		a = copy;
	   }
	   
	   public void enqueue(Item item)           // add the item
	   {
		   if( n == a.length)
		   	resize(2 * a.length);
		   a[n++] = item;
	   }
	   
	   public Item dequeue()                    // remove and return a random item
	   {
		   int index = StdRandom.uniform(n);
		   Item item = a[index];
		   for(int i = index; i < n-1; i ++)
		   {
		   	a[i] = a[ i +1];
		   }
		   a[n] = null;
		   n = n-1;

		   if(n > 0 && n == a.length/4)
		   	resize(a.length/2);
		   return item;
	   }
	   
	   public Item sample()                     // return (but do not remove) a random item
	   {
		   int index = StdRandom.uniform(n);
		   Item item = a[index];
		   return item;
	   }
	   
	   public Iterator<Item> iterator()         // return an independent iterator over items in random order
	   {
		   return new RandomizedQueueIterator();
	   }

	   private class RandomizedQueueIterator implements Iterator<Item> {
	   	private int i = n - 1;

	   	public boolean hasNext()
	   	{
	   		return i >=0;
	   	}

	   	public Item next(){
	   		return a[i--];
	   	}
	   }

	   private String tostring( )
    {

    	 Item item;
   		 String result = "[  ";

   		 for(int i = 0; i < n; i++){
   		 	item = a[i];
   		 	result = result + item.toString() + "  ";
   		 }

   		 result = result + "]";
    	return result;
    }
	   
	   public static void main(String[] args)   // unit testing
	   {
		   RandomizedQueue test1 = new RandomizedQueue();
		   System.out.println("is empty?: true  "+test1.isEmpty());
		    test1.enqueue(6);
			test1.enqueue(7);
			test1.enqueue(3);
			test1.enqueue(5);
			test1.enqueue(2);
			System.out.println("the  is [6,7,3,5,2]: "+test1.tostring() );
			
			//test1.dequeue( );
			System.out.println("test the dequeue(): "+test1.dequeue( )  );
			System.out.println("the  left: "+test1.tostring() );
			
			System.out.println("test the dequeue(): "+test1.dequeue( )  );
			System.out.println("the  left: "+test1.tostring() );
			
			System.out.println("test the dequeue(): "+test1.dequeue( )  );
			System.out.println("the  left: "+test1.tostring() );
			
			

	   }
	}