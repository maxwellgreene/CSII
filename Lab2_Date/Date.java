//----------------------------------------------------------------------
// Date.java
// Defines calendar date objects having year, month, and day attributes.
//
// Originally by Dale/Joyce/Weems (Chapter 1 of textbook)
// Modified to include a second constructor that initializes a newly-created
// object to represent the calendear date having the given lilian day number.
//
// CMPS 144L  Fall 2019
// Team members: ...
// Known defects: ...
//
//----------------------------------------------------------------------

public class Date
{
  // class constant
  // --------------
  public static final int MINYEAR = 1583;  // Calendar dates before this year
                                           // are not supported.

  // instance variables
  // ------------------
  protected int year, month, day;


  // Constructors
  // ------------

  // Initializes this Date to be that described by the incoming parameter
  // values.
  public Date(int newMonth, int newDay, int newYear)
  {
    month = newMonth;  day = newDay;  year = newYear;
  }

  // Initializes this Date to be that having the given Lilian Day Number.
  public Date(int lilianNumber) { 
     month = 2; day = 8; year = 2019;   // NOT CORRECT!!
  }


  // Observers
  // ---------

  public int getYear() { return year;  }
  public int getMonth(){ return month; }
  public int getDay()  { return day;   }
  

  // Returns the Lilian Day Number of this date.  Under this counting system,
  // day #1 was October 15, 1582.
  // Precondition: This Date object describes a calendar date that comes
  // after 10/14/1582.
  public int lilian() { return lilian(month, day, year); }

  // Returns the Lilian Day Number of the calendar date described by the
  // incoming parameter values.
  protected int lilian(int month, int day, int year) {
  
    // Computes the number of days between 1/1/0 and this date as if no calendar
    // reforms took place, then subtracts 578,100 so that October 15, 1582 is 
    // day 1.  
    final int subDays = 578100;  // number of calculated days 
                                 // from 1/1/0 to 10/14/1582
    int numDays = 0;

    // Add days in years.
    numDays = year * 365;

    // Add days in the months.
    if (month <= 2) 
      { numDays = numDays + (month - 1) * 31; }
    else 
      { numDays = numDays + ((month - 1) * 31) - ((4 * (month-1) + 27) / 10); }

    // Add days in the days.
    numDays = numDays + day;

    // Take care of leap years.
    numDays = numDays + (year / 4) - (year / 100) + (year / 400);

    // Handle special case of leap year but not yet leap day.
    if (month < 3) 
    {
       if ((year % 4) == 0)   { numDays = numDays - 1; }
       if ((year % 100) == 0) { numDays = numDays + 1; }
       if ((year % 400) == 0) { numDays = numDays - 1; }
    }

    // Subtract extra days up to 10/14/1582.
    numDays = numDays - subDays;
    return numDays;
  }
  
  // Returns this date as a String of the form M/D/Y (e.g., "10/5/1977").
  @Override
  public String toString() {
     return(month + "/" + day + "/" + year);
  }

  /* Reports whether or not this Date represents the same calendar date as
  ** the given Date object.
  */
  public boolean equals(Date date) {
     return this.year == date.year &&
            this.month == date.month &&
            this.day == date.day;
  }

}
 