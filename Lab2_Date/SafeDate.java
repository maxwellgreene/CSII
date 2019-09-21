// SafeDate.java     by Dale/Joyce/Weems    Chapter 1
// Augmented by R. McCloskey
//
// CMPS 144L  Fall 2019  Lab #2
// Lab team members:  ...
// Known defects: ...
//
// An instance of this class, which extends IncDate, represents a calendar
// date.  There are no new methods here, nor are any inherited methods
// overridden.  What this class provides is a constructor that does not
// allow an object to be created unless it represents a valid calendar date.
// If an attempt is made to create an invalid date, a DateOutOfBoundsException
// is thrown.


public class SafeDate extends IncDate {

  /* Initializes the new object so that it represents the calendar date
  ** described by the three parameters.  However, if those parameters do 
  ** not describe a valid calendar date, a DateOutOfBoundsException is
  ** thrown.  The message associated with the thrown exception indicates
  ** whether a violation of validity was found with respect to the month,
  ** the year, or the day.
  */
  public SafeDate(int newMonth, int newDay, int newYear)
                       throws DateOutOfBoundsException
  {
    // Call the parent class's constructor
    super(newMonth, newDay, newYear);

    // Now verify that the state of this object is valid, i.e., that it
    // represents a valid calendar date.  If not, throw the 
    // DateOutOfBoundsException with an appropriate error message.

    // First check the month.
    if (month <= 0 || month > 12) {
      throw new DateOutOfBoundsException("month " + newMonth + " is illegal");
    }
    
    // Now check the year.
    if (year < MINYEAR) {
      throw new DateOutOfBoundsException("year " + newYear + " is too early");
    }

    // Finally, check the day.

    //  MISSING CODE (that should make use of the isValidDay() method)
  }

  /* Initializes the new object so that it represents the calendar date
  ** having the given Lilian Day Number.  If that number is not greater 
  ** than zero, however, a DateOutOfBoundsException is thrown.
  */
  public SafeDate(int lilianNumber) throws DateOutOfBoundsException
  {
    super(lilianNumber);
    if (lilianNumber < 1) {
      throw new DateOutOfBoundsException("Lilian Day numbers start at 1.");
    }
  }


  // private
  // -------

  /* Reports whether or not the value of the 'day' instance variable is
  ** valid, given the already-established values for instance variables 
  ** 'year' and 'month'.  In order for the 'day' value to be valid, it
  ** must be at least 1 and at most equal to the number of days in 
  ** the relevant month.  (Hence, the (inherited) DAYS_IN_MONTH[] array 
  ** should be useful.)  In the case of February (month #2), it has an 
  ** "extra" day in any leap year.  (Hence, the inherited isLeapYear() 
  ** method should be useful.)
  */
  private boolean isValidDay() {
    return false;  // STUB!!
  }

}