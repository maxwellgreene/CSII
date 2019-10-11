/** An instance of this class represents a queue capable of holding items
**  of the specified type T (the generic type parameter).
**  An array (used in a "wraparound" fashion) is the basis for the
**  underlying implementation.
**
**  Author: R. McCloskey
**  Date: March 2012
*/

public class QueueViaArray<T> implements Queue<T> {


   /*  i n s t a n c e    v a r i a b l e s  */

   private int numItems;  // # items occupying the queue
   private T[] contents;  // holds (references to) the items on the queue
   private int frontLoc;  // location in array at which the front item occurs
                          // The kth item on the queue is stored in 
                          // contents[(startLoc+k) % contents.length]

   /*  s y m b o l i c   c o n s t a n t  */

   private static final int DEFAULT_INIT_CAPACITY = 8;


   /*  c o n s t r u c t o r s  */

   public QueueViaArray(int initCapacity)
   {
      numItems = 0;
      contents = (T[])(new Object[initCapacity]);
      frontLoc = 0;
   }

   public QueueViaArray() { this( DEFAULT_INIT_CAPACITY); }


   /*  o b s e r v e r s  */

   public boolean isEmpty() { return sizeOf() == 0; }

   public int sizeOf() { return numItems; }

   public T frontOf() { return item(0); }

   public T item(int k) { return contents[(frontLoc+k) % contents.length]; }

   public String toString()
   {
      StringBuilder s = new StringBuilder();
      for (int i=0; i != sizeOf(); i++)
      {
         s.append(item(i).toString() + ",");
      }
      return s.substring(0,Math.max(0, s.length()-1));
   }


   /*  m u t a t o r s  */

   public void enqueue( T elem)
   {
      if (numItems == contents.length)
      {
         // contents[] is full, so create a new array having twice the length,
         // copy the values from contents[] into the new array, and then 
         // make contents[] refer to the new array.
         T[] temp = (T[])(new Object[2 * contents.length]);
         arrayCopy(contents, frontLoc, temp, numItems);
         contents = temp; 
         frontLoc = 0;
      } 
      contents[(frontLoc + numItems) % contents.length] = elem;
      numItems = numItems + 1;
   }


   public void dequeue()
   {
      final int N = contents.length;
      contents[frontLoc] = null;                   // to aid garbage collection
      frontLoc = (frontLoc + 1) % N;
      numItems = numItems - 1;

      if (N > DEFAULT_INIT_CAPACITY  && N > 4 * numItems)
      { 
         // The length of contents[] is greater than the default initial 
         // capacity and more than four times the queue's size, so cut the 
         // length of contents[] in half.
         T[] temp = (T[])(new Object[N / 2]);
         arrayCopy(contents, frontLoc, temp, numItems);
         contents = temp;
         frontLoc = 0;
      }
   }

   /*  u t i l i t y  */

   /** Copies values in source[startLoc..((startLoc+length-1)%source.length)]
   **  (in wraparound fashion) into dest[0..length-1]
   */
   private void arrayCopy(T[] source, int startLoc, T[] dest, int length)
   {
      for (int i=0; i != length; i++)
         { dest[i] = source[(startLoc+i) % source.length]; }
   }
}