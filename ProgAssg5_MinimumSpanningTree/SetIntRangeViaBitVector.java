import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/* An instance of an this class represents a subset of the integers
** in a "universe" that is a range identified by a floor and a ceiling.
** A boolean array (i.e., "bit vector") is used to represent the set.
**
** Author: R. McCloskey
** Date: December 2019
*/
public class SetIntRangeViaBitVector implements SetIntRange {

   // instance variables
   // ------------------

   private final int FLOOR;     // The universe of this set is the range
   private final int CEILING;   // [FLOOR .. CEILING]
   private boolean[] isMember;  // isMember[k] iff k is a member of this set
   private int card;            // cardinality of this set
   private int loopCntr;

   // constructor
   // -----------

   public SetIntRangeViaBitVector(int floor, int ceiling) {
      FLOOR = floor;  CEILING = ceiling;
      card = 0;
      isMember = new boolean[CEILING - FLOOR + 1];
      makeEmpty();
      loopCntr = 0;
   }

   // observers
   // ---------

   public int measureOfWork() { return loopCntr; }

   // Returns the smallest value in the universe of this set.
   public int floorOf() { return FLOOR; }

   // Returns the largest value in the universe of this set.
   public int ceilingOf() { return CEILING; }

   // Reports whether or not the given value is a member of this set.
   public boolean isMemberOf(int k) { return isMember[k - FLOOR]; }

   // Returns the # of elements in this set.
   public int cardinalityOf() { return card; }

   // Reports whether or not this set is empty.
   public boolean isEmpty() { return cardinalityOf() == 0; }

   // mutators
   // --------

   /* Ensures that the given number is a member of this set.
   ** pre: floorOf() <= k <= ceilingOf()
   */
   public void insert(int k) {
      if (!isMember[k - FLOOR]) {
         isMember[k - FLOOR] = true;
         card++;
      }
   }

   /* Ensures that the given number is not a member of this set.
   ** pre: floorOf() <= k <= ceilingOf()
   */
   public void remove(int k) {
      if (isMember[k - FLOOR]) {
         isMember[k - FLOOR] = false;
         card--;
      }
   }

   // Ensures that this set is empty.
   public void makeEmpty() { fill(false); }

   // Ensures that this set is full (i.e., contains all members of the universe).
   public void makeFull() { fill(true); }


   // generator
   // ---------

   /* Returns a list containing the members of this set.
   */
   public List<Integer> toList() {
      ArrayList<Integer> result = new ArrayList<Integer>(card);
      for (int i = 0; i != isMember.length; i++) {
         if (isMember[i]) { result.add(FLOOR + i); }
      }
      loopCntr = loopCntr + isMember.length;
      return result;
   }

   /* Returns an Iterator that iterates over the members of this set.
   */
   public Iterator<Integer> iterator() {
      return toList().iterator();
   }


   // private methods
   // ---------------

   /* Places the given value into each element of isMember[].
   */
   private void fill(boolean value) {
      for (int i=0; i != isMember.length; i++) { isMember[i] = value; }
      loopCntr = loopCntr + isMember.length;
   }
}
