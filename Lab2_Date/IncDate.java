
// IncDate.java              by Dale/Joyce/Weems                  Chapter 1
// Augmented by R. McCloskey
//
// CMPS 144L  Fall 2019  Lab #2
// Team members: ...
// Known defects: ...
//
// Extends the Date class, instances of which represent calendar dates.
// This class introduces the increment() method, which is a mutator/transformer 
// not found in the parent class (which produces immutable objects).


public class IncDate extends Date {

  // class constant
  // --------------

  // Array holding # days in each month, so that the element at location k 
  // contains the number of days in month k (1 is Jan., 2 is Feb., etc.).
  // February is designated to have 28 days, ignoring the leap year issue.
  protected static final int[] DAYS_IN_MONTH = 
     { -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


  // constructors
  // ------------

  public IncDate(int newMonth, int newDay, int newYear)
  {
    super(newMonth, newDay, newYear);  // Call the parent class's
  }                                    // analogous constructor.

  public IncDate(int lilianNumber)
  { 
     super(lilianNumber);    // Call the parent class's analogous constructor
  }


  // transformer/mutator
  // -------------------
  /* Advances this IncDate object by one day into the future.
  ** E.g., May 14, 1947 becomes May 15, 1947, 
  **       December 31, 1977 becomes January 1, 1978.
  */
  public void increment() {
     if(isLastDayOfMonth())
     {
         
     }else{
     
     }
  }


  // Utilities
  // ---------

  /* Reports whether or not this object describes a calendar date that
  ** falls on the last day of a month.  
  */
  protected boolean isLastDayOfMonth() {
     if (isLeapYear() && getMonth()==2 && getDate()==29)
     {
         return true;
     }
     if (getDate()==DAYS_IN_MONTH[getMonth()])
     {
      return true;
     }
     // Hint: Use the DAYS_IN_MONTH[] array declared above and the
     //       isLeapYear() method below. 
     return false;
  }

  /* Reports whether or not this object describes a calendar date
  ** that falls during a leap year.
  ** A leap year is one that is either divisible by 400 or else
  ** divisible by 4 but not by 100.
  */
  protected boolean isLeapYear() {
     return (year % 400 == 0) ||
            (year % 4 == 0  &&  year % 100 != 0);
  }

}
 