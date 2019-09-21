/* ReversibleCounter.java
** An instance of this concrete class (which extends abstract class 
** BoundedCounter) is a bounded counter that can be in either of two modes:
** Standard or Reverse.  Except for the boundary cases described below, a 
** counter in Standard mode acts in the usual way, but a counter in Reverse
** mode increases its count value in response to decrement() being called 
** and decreases its count value in response to increment() being called.
** In other words, when in Reverse mode, increment() behaves like decrement()
** and vice versa.)
**
** The boundary cases occur when, in either mode, an attempt is made to 
** increase the count value beyond the maximum of its range or to decrease
** the count value beyond the minimum of its range.  In both of these cases,
** the count value should remain the same and the counter's mode should be
** toggled.
**
** Authors: R. McCloskey and Maxwell Greene
** Date: Sept. 20th 2019
*/

public class ReversibleCounter extends BoundedCounter {

   // class constants
   // ---------------
   // No additional constants to CoundedCounter
   
   
   // instance variables
   // ------------------
   private boolean mode; //true if in "Standard" mode

   // constructors
   // ------------

   /* Initializes this counter to have the specified initial value
   ** and the specified range of possible values.  Also initializes
   ** its mode to standard.
   */
   public ReversibleCounter(int init, int min, int max) {
      super(init, min, max);
      mode = true; //(Standard)
   }

   /* Initializes this counter to have the specified range of possible 
   ** values with the initial value being the minimum of its range.
   ** Also initializes its mode to STANDARD.
   */
   public ReversibleCounter(int min, int max) { this(min, min, max); }
   
   // observers
   // ---------

   /* Reports whether this counter is in Standard mode 
   ** (as opposed to Reverse mode).
   */
   
   public boolean inStandardMode() 
   { return mode; }

   /* Returns a string that describes the current state of this counter,
   ** including its count value, mode, and range of legal count values.
   */
   public String toString() {
      return "count val = " + countVal() + 
             "\nmode = " + (inStandardMode() ? "Standard" : "Reverse") +
             "\nmin = " + minimumVal() + "; max = " + maximumVal();
   }

   // mutators
   // --------

   /* Sets this counter's mode to Standard.
   */
   public void setToStandard() { mode = true; }

   /* Sets this counter's mode to Reverse.
   */
   public void setToReverse() { mode = false; }

   /* Toggles this counter's mode (i.e. flips it from 
   ** Standard to Reverse or vice versa).
   */
   public void toggleMode() { mode = !mode; }

   @Override
   public void increment() {
      if (mode)
      { super.increment(); }
      else
      { super.decrement(); }
   }

   @Override
   public void decrement() { 
      if (mode)
      { super.decrement(); }
      else
      { super.increment(); }
   }

   @Override
   protected void incrementFromMax() 
   { toggleMode(); }

   @Override
   protected void decrementFromMin() 
   { toggleMode(); }
}