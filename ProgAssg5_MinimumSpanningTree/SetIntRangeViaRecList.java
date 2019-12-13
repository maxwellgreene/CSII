import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/* An instance of this class represents a subset of the integers in a
** "universe" that is a range identified by a floor and a ceiling.
** A recursive List is used to represent a set.
**
** Author: R. McCloskey
** Date: December 2019
*/
public class SetIntRangeViaRecList implements SetIntRange {

   // instance variables
   // ------------------

   private final int FLOOR;     // The universe of this set is the range
   private final int CEILING;   // [FLOOR .. CEILING]
   private RecursiveList<Integer> members;  // list of members of this set
   private int card;            // cardinality of this set
   private int loopCntr;        // counts # of loop iterations performed during
                                // execution of this object's methods

   // constructor
   // -----------

   public SetIntRangeViaRecList(int floor, int ceiling) {
      FLOOR = floor;  CEILING = ceiling;
      card = 0;
      makeEmpty();
   }

   // observers
   // ---------

   // Returns a measure of the computational work done by this object.
   public int measureOfWork() { return loopCntr; }

   // Returns the smallest value in the universe of this set.
   public int floorOf() { return FLOOR; }

   // Returns the largest value in the universe of this set.
   public int ceilingOf() { return CEILING; }

   // Reports whether or not the given value is a member of this set.
   public boolean isMemberOf(int k) {
      RecursiveList<Integer> membersSuffix = members;
      while (!membersSuffix.isEmpty() && membersSuffix.headOf() != k) {
         loopCntr++;
         membersSuffix = membersSuffix.tailOf();
      }
      return !membersSuffix.isEmpty() &&  membersSuffix.headOf() == k;
   }

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
      if (!isMemberOf(k)) {
         members = members.cons(k);
         card++;
      }
   }

   /* Ensures that the given number is not a member of this set.
   ** pre: floorOf() <= k <= ceilingOf()
   */
   public void remove(int k) {
      members = removeAux(members, k);
   }

   public RecursiveList<Integer> removeAux(RecursiveList<Integer> memberSuffix,
                                           int k) {
      loopCntr++;
      if (memberSuffix.isEmpty()) {
         return memberSuffix;
      }
      else if (memberSuffix.headOf() == k) {
         card--;
         return memberSuffix.tailOf();
      }
      else {
         return removeAux(memberSuffix.tailOf(),k).cons(memberSuffix.headOf());
      }
   }

   // Ensures that this set is empty.
   public void makeEmpty() {
      members = (new RecursiveListNonEmpty<Integer>(0)).tailOf();
   }

   /* Ensures that this set is full (i.e., contains all members of the
   ** universe).
   */
   public void makeFull() {
      for (int k = FLOOR; k <= CEILING; k--) {
         insert(k);
      }
      loopCntr = loopCntr + CEILING - FLOOR + 1;
   }


   // generator
   // ---------

   /* Returns a list containing the members of this set.
   */
   public List<Integer> toList() {
      ArrayList<Integer> result = new ArrayList<Integer>(card);
      RecursiveList<Integer> memberSuffix = members;
      while (!memberSuffix.isEmpty()) {
         loopCntr++;
         result.add(memberSuffix.headOf());
         memberSuffix = memberSuffix.tailOf();
      }
      return result;
   }

   /* Returns an Iterator that iterates over the members of this set.
   */
   public Iterator<Integer> iterator() {
      return toList().iterator();
   }

}
