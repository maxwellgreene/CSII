import java.util.Scanner;
import java.util.InputMismatchException;

/* IncDateTester.java
** Java application having as its purpose to test the methods of the IncDate
** class, which is a subclass of Date.
**
** Author: R. McCloskey
** Date: January 2019
*/

public class IncDateTester {

   private static Scanner input;

   public static void main(String[] args) {
      input = new Scanner(System.in);

      // Allow user to specify a starting date.
      System.out.print("Enter an initial calendar date:");
      int m = getIntFromUser("Month (in range 1..12): ", 1, 12);
      int d = getIntFromUser("Day (in range 1..31): ", 1, 31);
      int y = getIntFromUser("Year (in range 1583..): ", 1583, Integer.MAX_VALUE);

      IncDate date = new IncDate(m, d, y);
      System.out.println("\nInitial value of date is " + date);

      // Now have user specify how many times to "increment" the date.
      System.out.println();
      int n = getIntFromUser("Enter how many times to apply increment(): ", 1, 367);

      System.out.println();

      // Apply the increment() method the specified number of times, and each
      // time print the current state of the IncDate object.
      for (int i=1; i <= n; i++) {
         date.increment();
         System.out.printf("After incrementing %3d times, date is %s\n", i, date);
      }

      System.out.println("\nGoodbye.");
   }

   /* Prints the specified prompt and returns the user's response, interpreted
   ** as an int.  If the response is outside the range of values specified by
   ** the last two parameters, or the user enters a sequence of characters that
   ** cannot be interpreted to be a decimal integer numeral, the user is asked 
   ** to enter a new value.  This repeats until a valid value is entered.
   */
   private static int getIntFromUser(String prompt, int min, int max) {
      int result = 0;  
      boolean keepGoing = true;
      while (keepGoing) {
         System.out.print(prompt);
         try {
            result = input.nextInt();
            if (result >= min  &&  result <= max) {
               keepGoing = false;
            }
            else {
               System.out.printf("Error; response must be in the range %d..%d\n\n", 
                                 min, max);
            }
         }
         catch (InputMismatchException e) {
            e.printStackTrace(System.out);
            System.out.println("\nError; you must enter an integer numeral.\n");
            input.nextLine();
         }
      }
      return result;
   }

}