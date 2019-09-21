/* ReversibleTester.java
** Java application program that interacts with the user (at the keyboard) 
** for the purpose of testing the ReversibleCounter class, which is a child
** of BoundedCounter.
*/

import java.util.Scanner;

public class ReversibleTester {

   private static final char QUIT = 'q';
   private static final char INCREMENT = 'i';
   private static final char DECREMENT = 'd';
   private static final char SET_TO = 's';
   private static final char TOGGLE = 't';
   private static final char HELP = 'h';
   private static final String COMMANDS = "" + QUIT + INCREMENT + DECREMENT +
                                          SET_TO + TOGGLE + HELP;

   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) {

      System.out.println("Creating a new ReversibleCounter object...");
      int min = getIntFromUser("Enter minimum of range: ");
      int max = getIntFromUser("Enter maximum of range: ");
      int init = getIntFromUser("Enter initial count value: ");

      // Create a ReversibleCounter object with initial value zero.
      ReversibleCounter c = new ReversibleCounter(init, min, max);

      char command = ' ';
      println("Initially:\n" + c + "\n");
      printHelp();

      // Repeatedly apply operations to the counter in accord with
      // the commands entered by the user.
      while (command != QUIT) {
         try {
            print("\n>");
            command = keyboard.next().charAt(0);
            test(c, command);
         }
         catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println();
         }
      }
   }


   /* Applies to the given ReversibleCounter object the method indicated 
   ** by the given command code.
   */
   private static void test(ReversibleCounter cntr, char command) 
   { 
      command = Character.toLowerCase(command);

      if (COMMANDS.indexOf(command) == -1) {
         println("Unrecognized command; try again:");
         printHelp();
      }
      else if (command == QUIT) {
         // do nothing
      }
      else if (command == HELP) {
         printHelp();
      }
      else {  // a valid command to modify the counter has been entered
         if (command == INCREMENT) {
            println("After incrementing:");
            cntr.increment();
         }
         else if (command == DECREMENT) {
            println("After decrementing:");
            cntr.decrement();
         }
         else if (command == SET_TO) {
            println("After setting to:");
            int k = keyboard.nextInt(); 
            cntr.setTo(k);
         }
         else if (command == TOGGLE) {
            println("After toggling the mode:");
            cntr.toggleMode();
         }

         // Having applied an operation, display the counter's current state.
         System.out.println(cntr);
      }
   }


   /* Displays the given string and then reads the user's response, 
   ** interpreting it as an int and returning that value.
   */
   private static int getIntFromUser(String prompt) {
      System.out.print(prompt);
      return keyboard.nextInt();
   }

   /* Prints instructions for the user.
   */
   private static void printHelp() {
      System.out.printf("%c to quit, ", QUIT);
      System.out.printf("%c to increment, ", INCREMENT);
      System.out.printf("%c to decrement,\n", DECREMENT);
      System.out.printf("%c <num> to setTo, ", SET_TO);
      System.out.printf("%c to toggle mode, ", TOGGLE);
      System.out.printf("%c for this help\n", HELP);
   }


   /* Surrogate for the System.out.println() method.
   */
   private static void println(String s) { System.out.println(s); }

   /* Surrogate for the System.out.print() method.
   */
   private static void print(String s) { System.out.print(s); }

}