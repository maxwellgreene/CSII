/ * An instance of this class represents a non-empty list of items.
**
** Author: R. McCloskey, November 2019
* /

public class RecursiveListNonEmpty <T> implements RecursiveList <T> {

   // instance variables
   // ------------------
   private T head;
   private RecursiveList <T> tail;

   // constructors
   // ------------

   public RecursiveListNonEmpty (T item) {
      this (item, RecursiveListEmpty.emptyListFactory ()); }
      // this (item, new RecursiveListEmpty ()); }

   public RecursiveListNonEmpty (T item, RecursiveList <T> list) {
      head = item;
      tail = list;
   }

   public boolean isEmpty () {return false; }

   public T headOf () {return head; }

   public RecursiveList <T> tailOf () {return tail; }

   public String toString () {
      return "[" + headOf () + "" + tailOf (). toString () + "]";
   }

   public RecursiveList <T> cons (T item) {
      return new RecursiveListNonEmpty (item, this);
   }

   public RecursiveList <T> emptyList () {
      return RecursiveListEmpty.emptyListFactory ();
      // return new RecursiveListEmpty <T> ();
   }
}
