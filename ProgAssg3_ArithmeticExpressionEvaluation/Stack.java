/** Interface for the well-known stack abstract data type.
**
**  @author R. McCloskey
**  @version October 2008
*/

public interface Stack<T> {

   /** Reports whether or not the stack is empty (i.e., contains no items).
   **  @return true if the stack is empty, false otherwise
   */
   boolean isEmpty();

   /** Reports how many items are on the stack.
   **  @return the # of items on the stack
   */
   int sizeOf();
   
   /** Returns the item at the top of the stack.<br />
   **  Precondition: !isEmpty()
   **  @return the item at the top of the stack
   */
   T topOf();

   /** Places the specified item at the top of the stack.
   **  @param x the item to be placed on the stack
   */
   void push(T x);

   /** Removes the item at the top of the stack.<br />
   **  Precondition: !isEmpty()
   */
   void pop();

}