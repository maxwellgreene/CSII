import java.util.List;
import java.util.Iterator;

/* An instance of an implementing class represents a subset of the integers
** in a "universe" that is a range identified by a floor and a ceiling.
**
** Author: R. McCloskey
** Date: December 2019
*/
public interface SetIntRange {


   // observers
   // ---------

   // Returns a measure of the computational work carried out by this object.
   int measureOfWork();

   // Returns the smallest value in the universe of this set.
   int floorOf();

   // Returns the largest value in the universe of this set.
   int ceilingOf();

   // Reports whether or not the given value is a member of this set.
   boolean isMemberOf(int k);

   // Returns the # of elements in this set.
   int cardinalityOf();

   // Reports whether or not this set has cardinality zero.
   boolean isEmpty();

   // mutators
   // --------

   /* Ensures that the given number is a member of this set.
   ** pre: floorOf() <= k <= ceilingOf()
   */
   void insert(int k);

   /* Ensures that the given number is not a member of this set.
   ** pre: floorOf() <= k <= ceilingOf()
   */
   void remove(int k);

   // Ensures that this set has no members.
   void makeEmpty();

   /* Ensurses that this set includes as members all elements of the
   ** universe [floorOf() .. ceilingOf()].
   */
   void makeFull();


   // generator
   // ---------

   /* Returns a list containing the members of this set.
   */
   List<Integer> toList();

   /* Returns an Iterator that iterates over the members of this set.
   */
   Iterator<Integer> iterator();
}
