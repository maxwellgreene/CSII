import java.util.Iterator;
import java.util.List;

/* The purpose of this class is to demonstrate how to make use of
** (1) an Iterator object (i.e., an instance of a class that implements the
**     java.util.Iterator interface), and
** (2) a for-each loop to iterate over the elements of an object that is an
**     instance of a class that implements the java.util.Iterable interface.
**
** For the purpose of demonstration, objects that are instances of classes
** implementing the SetIntRange interface will be used.
*/
public class IterListDemo {

   public static void main(String[] args) {

      // Create a new (empty) set.
      SetIntRange set = new SetIntRangeViaBitVector(0,40);

      // Insert several integers into the set.
      set.insert(15); set.insert(3); set.insert(28); set.insert(9);
      set.insert(35); set.insert(19);

      // Create an Iterator for the set.
      Iterator<Integer> iter = set.iterator();

      System.out.println("Members of set (using Iterator): ");
      // Now iterate over the elements of the set using the next() and
      // hasNext() methods, and print each of its members.IterListDemo 
      while (iter.hasNext()) {      // while there are more elements
         int k = iter.next();       // fetch the next one
         System.out.print(k + " "); // print it
      }
      System.out.println();

      // Now create a list of the elements of the set and use a for-each
      // loop to access each one.
      List<Integer> list = set.toList();
      System.out.println("Members of set (using for-each loop): ");
      for (int k : list) {
         System.out.print(k + " ");
      }
      System.out.println();
   }

}
