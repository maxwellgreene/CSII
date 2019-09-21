/* BoundedCounter.java
** Abstract Java class that extends ResetableCounter.  An instance of this
** class is such that its count value is restricted to lie within a range
** of integers whose lower and upper bounds are specified upon construction.
** The effect of incrementing (respectively, decrementing) a BoundedCounter
** is the same as with a ResetableCounter, except when its count value is
** equal to its upper bound (resp., lower bound).  In that case, the effect
** is that caused by the abstract method incrementFromMax() (resp.,
** decrementFromMin()) and thus is left to be specified by each subclass.
*/

public abstract class BoundedCounter extends ResetableCounter {

   // instance variables
   // ------------------
   private int minVal, maxVal;  // min and max allowed count values

   // constructors
   // ------------

   /* Initializes this BoundedCounter so that its range of possible
   ** count values is min..max and its initial value is init.
   **
   ** pre:  min <= init <= max
   ** post: this.countVal() == init &&
   **       this.minimumVal() == min  &&
   **       this.maximumVal() == max
   */
   public BoundedCounter(int init, int min, int max) {
      super(init);
      minVal = min;
      maxVal = max;
      if (minVal > maxVal) {
         throw new IllegalArgumentException("range " + minVal  + ".." + 
                                            maxVal + " is empty");
      }
      else if (init < minVal || init > maxVal) {
         throw new IllegalArgumentException("initial value " + init + 
                                            " is outside the range " + 
                                            minVal + ".." + maxVal);
      }
   }

   /* Initializes this BoundedCounter so that its range of possible
   ** count values is min..max and its initial value is min.
   ** pre:  min <= max
   ** post: this.countVal() == min &&
   **       this.minimumVal() == min  &&
   **       this.maximumVal() == max
   */
   public BoundedCounter(int min, int max) { this(min, min, max); }

   
   // observers
   // ---------

   public int minimumVal() { return minVal; }
   public int maximumVal() { return maxVal; }


   // mutators
   // --------

   // --------------------------------------------------------------------
   // NOTE: The following method is not in the version of this class initially
   //   shown to students.  Inserting it into that class solves the 2nd 
   //   exercise posed in the web page that presents the Counter class 
   //   hierarchy.
   // -----------------------------------------------------------------

   @Override
   public void setTo(int val) {
      if (minimumVal() <= val  &&  val <= maximumVal()) {
         super.setTo(val);
      }
      else {
         throw new IllegalArgumentException(val + " is out of range");
      }
   }

   /* Increments this BoundedCounter's count value, which means applying the
   ** inherited version of the method except when the count value equals 
   ** this.maximumVal(), in which case the incrementFromMax() method is applied.
   */
   @Override
   public void increment() {
      if (countVal() == maxVal)
         { incrementFromMax(); }
      else
         { super.increment(); }
   }

   /* Decrements this BoundedCounter's count value, which means applying the
   ** inherited version of the method except when the count value equals 
   ** this.minimumVal(), in which case the decrementFromMin() method is applied.
   */
   @Override
   public void decrement() {
      if (countVal() == minVal)
         { decrementFromMin(); }
      else
         { super.decrement(); }
   }


   // mutators  (abstract, and thus left for subclasses to implement)
   // --------

   /* Takes appropriate action to increment this BoundedCounter's count value
   ** in the case that it currently equals maximumVal();
   */
   protected abstract void incrementFromMax();

   /* Takes appropriate action to decrement this BoundedCounter's count value
   ** in the case that it currently equals minimumVal();
   */
   protected abstract void decrementFromMin();

}
