/* An instance of a concrete class that implements this interface represents 
** a list of items.  The list concept adopted here is based upon that of 
** John McCarthy's LISP programming language.  
** Thinking recursively, a list either
** --is empty, or 
** --has a head (an element) and a tail (that is itself a list)
**
** Author: R. McCloskey, November 2019
*/

public interface RecursiveList<T> {

   /* Reports whether or not this list is empty (i.e., has no items in it)
   */
   boolean isEmpty();

   /* Returns the head of this list.
   ** Throws NoSuchElementException if this list is empty.
   */
   T headOf();

   /* Returns the tail of this list.
   ** Throws NoSuchElementException if this list is empty.
   */
   RecursiveList<T> tailOf();

   /* Returns the list whose head is the specified item and whose tail
   ** is this list.
   */
   RecursiveList<T> cons(T item);


   /* Returns a new empty list
   */
   RecursiveList<T> emptyList();
}