/** An instance of this class represents a stack.
**
**  @author R. McCloskey
**  @version October 2008
*/

public class StackViaArray<T> implements Stack<T> {

   // class constant
   // --------------

   // Default initial length of contents[], as well as the minimum length 
   // to which contents[] will ever be "downsized"
   private static final int DEFAULT_INIT_LEN = 8; 


   // instance variables 
   // ------------------

   private int numItems;  // # of items on the stack
   private T contents[];  // holds (references to) the items on the stack, from
                          // bottom (at location 0) to top (at numItems-1)


   // constructors
   // ------------

   /* Initializes the underlying array to have the specified length.
   ** @param initLen The initial length of the underlying array
   */
   public StackViaArray(int initLen)  {
      numItems = 0; 
      contents = (T[]) new Object[initLen];
   }

   /* Initializes the underlying array to have the default length.
   */
   public StackViaArray()  { this( DEFAULT_INIT_LEN ); }


   // observers
   // ---------

   public boolean isEmpty() { return numItems == 0; }

   public int sizeOf() { return numItems; }

   public T topOf() { return contents[numItems-1]; }


   // mutators
   // --------

   public void push(T x) {
      if (numItems == contents.length) {
         // contents[] is full, so double its length
         T[] temp = (T[]) new Object[2 * contents.length];
         System.arraycopy(contents, 0, temp, 0, numItems);
         contents = temp; 
      } 
      contents[numItems] = x;
      numItems = numItems + 1;
   }


   public void pop() {
      contents[numItems-1] = null;  // to hasten garbage collection
      numItems = numItems - 1;

      if (contents.length >= 2 * DEFAULT_INIT_LEN) {
         if (numItems < contents.length / 4) {    // if < 1/4 of contents
            // contents is at most one-quarter full, so halve its length
            T[] temp = (T[]) new Object[contents.length / 2];
            System.arraycopy(contents, 0, temp, 0, numItems);
            contents = temp;
         }
      }
   }

}
