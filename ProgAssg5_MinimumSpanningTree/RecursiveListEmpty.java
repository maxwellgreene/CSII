import java.util.NoSuchElementException;

/* An instance of this class represents an empty recursive list.
**
** Author: R. McCloskey, November 2019
*/

public class RecursiveListEmpty<T> implements RecursiveList<T> {

   // static variable (to implement singleton pattern)
   // ---------------

   private static RecursiveListEmpty loner;

   // constructor
   // -----------

   private RecursiveListEmpty() { }


   // observers
   // ---------

   public boolean isEmpty() { return true; }

   public T headOf() { 
      throw new NoSuchElementException("Empty list has no head");
   }

   public RecursiveList<T> tailOf() {
      throw new NoSuchElementException("Empty list has no tail");
   }

   public String toString() { return "[]"; }


   // generators
   // ----------

   public RecursiveList<T> cons(T item) {
      return new RecursiveListNonEmpty(item, this);
   }

   public RecursiveList<T> emptyList() { return this; }

   public static <T> RecursiveList<T> emptyListFactory() {
      if (loner == null) { 
         loner = new RecursiveListEmpty<T>();
      }
      return loner;
   }

}