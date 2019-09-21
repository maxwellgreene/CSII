import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Java application whose purpose is to test the methods of the Temperature
** class.  (CMPS 144L, Lab #1)
**
** Author: R. McCloskey
** Date: Auguest 2019
*/
public class TemperatureTester {

   private static char CEL_SCALE_CODE = 'C';
   private static char FAH_SCALE_CODE = 'F';
   private static char SET_TO_CODE = 'S';
   private static char CHANGE_BY_CODE = 'C';
   private static char DIFF_CODE = 'D';
   private static char HELP_CODE = 'H';
   private static char QUIT_CODE = 'Q';

   private static Scanner input;
   private static boolean echo;

   public static void main(String[] args) throws FileNotFoundException {
      // Establish the Scanner so that it reads either from the file whose name
      // was provided as a command-line argument or, absent any such argument,
      // from the keyboard.
      if (args.length != 0) {
         input = new Scanner(new File(args[0]));
         echo = true;
      }   
      else {  // no command line arg to specify input file name
         input = new Scanner(System.in);
         echo = false;
      }   

      Temperature temp = getNewTemperature();

      performCommands(temp);
      System.out.println("\nGoodbye.");
   }

   /* Prompts the user to enter an initial reading for a new Temperature object,
   ** and returns that new object.  User input is expected to be a number 
   ** followed by an optional C or F (for Celsius and Fahrenheit, respectively).
   ** For example, "-35.6F" indicates a temperature of -35.6 degrees Fahrenheit.
   */
   private static Temperature getNewTemperature() {
      System.out.println("Enter an initial temperature reading, which");
      System.out.print("should be a number optionally followed by C or F: "); 
      String response = input.nextLine().trim(); 
      if (echo) { System.out.println(response); }
      double initVal = parseOutNum(response);
      char scale = Character.toUpperCase(parseOutScale(response));

      if (scale == CEL_SCALE_CODE) { 
         System.out.printf("Calling Temperature(%f,true)\n", initVal);
         return new Temperature(initVal, true);
      }
      else if (scale == FAH_SCALE_CODE) {
         System.out.printf("Calling Temperature(%f,false)\n", initVal);
         return new Temperature(initVal, false);
      }
      else { // no scale indicated
         System.out.printf("Calling Temperature(%f)\n", initVal);
         return new Temperature(initVal);
      }
   }

   /* Given a string that is a real numeral optionally followed by a 
   ** non-digit character, returns the number (as a value of type
   ** double) corresponding to that numeral.
   ** Examples: "-13.5F" maps to -13.5, "14" maps to 14.0
   */
   private static double parseOutNum(String s)
   {
      final int N = s.length();
      // If the last character of s is a digit, the whole of s is assumed to
      // be a numeral.  Otherwise, s up to, but not including, its last 
      // character, is assumed to be a numeral.
      if (Character.isDigit(s.charAt(N-1)))
         { return Double.parseDouble(s); }
      else
         { return Double.parseDouble(s.substring(0,N-1)); }
   }

   /* Given a string that is a real numeral optionally followed by a 
   ** non-digit character, returns that non-digit character (if it is
   ** present) or a space (if it is not present).
   ** Examples: "-13.5F" maps to 'F', "14" maps to ' '
   */
   private static char parseOutScale(String s)
   {
      final int N = s.length();
      // If the last character of s is a digit, the whole of s is assumed to
      // be a numeral and a space is returned.  Otherwise, the last character
      // of s is returned.
      char lastChar = s.charAt(N-1);
      if (Character.isDigit(lastChar)) 
         { return ' '; }
      else
         { return lastChar; }
   }

   /* Processes commands provided as input, applying them to the given
   ** Temperature object.  Aside from the Quit ('Q') and Help ('H') commands,
   ** there are only 'S' (to call setTo()) and 'C' (to call changeBy()).
   ** Examples:
   **   "S 36.5F" : sets the temperature to 36.5F
   **   "C -16C"  : changes the temperature by -16C
   **   "D 13.4F" : computes the difference between the temperature and 13.4F
   */
   private static void performCommands(Temperature temp) {

      boolean keepGoing = true;
      while (keepGoing) {
         try {
            // Call each of the three toString() methods upon temp and
            // display the results.
            System.out.printf("\ntoString() yields      %s", temp.toString());
            System.out.printf("\ntoString(true) yields  %s", temp.toString(true));
            System.out.printf("\ntoString(false) yields %s\n", temp.toString(false));

            // Prompt user to enter a command.
            System.out.print("\nEnter command (H for help): ");
            String command = input.nextLine().trim();
            if (echo) { System.out.println(command); }

            // Parse the entered command.
            char operation = Character.toUpperCase(command.charAt(0));
            String rest = command.substring(1).trim();
            double number;
            char scale;
            if (rest.length() != 0) { 
               number = parseOutNum(rest);
               scale = parseOutScale(rest);
            }
            else { 
               number = 0;
               scale = ' ';
            }

            // Perform the command.
            if (operation == SET_TO_CODE) {
               performSetTo(temp, number, scale);
            }
            else if (operation == CHANGE_BY_CODE) {
               performChangeBy(temp, number, scale);
            }
            else if (operation == DIFF_CODE) {
               performDiff(temp, number, scale);
            }
            else if (operation == HELP_CODE) {
               printHelp();
            }
            else if (operation == QUIT_CODE) {
               keepGoing = false;
            }
            else {
               System.out.println(operation + " is not a known operation code");
            }
         }
         catch (Exception e) {
            e.printStackTrace(System.out);
         }
      }
   }

   /* Invokes one of the setTo() methods upon the given Temperature object,
   ** depending upon the parameter values.
   */
   private static void performSetTo(Temperature t, double reading, char scale) {
      if (scale == CEL_SCALE_CODE) { 
         System.out.printf("Calling setTo(%f,true)\n", reading);
         t.setTo(reading, true); 
      }
      else if (scale == FAH_SCALE_CODE) { 
         System.out.printf("Calling setTo(%f,false)\n", reading);
         t.setTo(reading, false);
      }
      else {
         System.out.printf("Calling setTo(%f)\n", reading);
         t.setTo(reading);
      }
   }

   /* Invokes one of the changeBy() methods upon the given Temperature object,
   ** depending upon the parameter values.
   */
   private static void performChangeBy(Temperature t, double delta, char scale) {

      if (scale == CEL_SCALE_CODE) { 
         System.out.printf("Calling changeBy(%f,true)\n", delta);
         t.changeBy(delta, true); 
      }
      else if (scale == FAH_SCALE_CODE) { 
         System.out.printf("Calling changeBy(%f,false)\n", delta);
         t.changeBy(delta, false);
      }
      else {
         System.out.printf("Calling changeBy(%f)\n", delta);
         t.changeBy(delta);
      }
   }

   /* Invokes the difference() methods upon the given Temperature object,
   ** so as to compute the difference between it and the temperature
   ** described by the given reading and scale code.
   */
   private static void performDiff(Temperature t, double tempReading, char scale)
   {
      Temperature temp;   // the temperature to be compared with
      
      if (scale == CEL_SCALE_CODE) { 
         temp = new Temperature(tempReading, true);
      }
      else if (scale == FAH_SCALE_CODE) { 
         temp = new Temperature(tempReading, false);
      }
      else {
         temp = new Temperature(tempReading);
      }

      System.out.printf("Computing difference between %s (%s) and %s (%s)\n", 
                        t.toString(), t.toString(false), 
                        temp.toString(), temp.toString(false));
      double diffCel1 = t.difference(temp, true);
      System.out.printf("  difference(%s, true) yields %f\n", temp, diffCel1);
      double diffFah = t.difference(temp, false);
      System.out.printf("  difference(%s, false) yields %f\n", temp, diffFah);
      double diffCel2 = t.difference(temp);
      System.out.printf("  difference(%s) yields %f\n", temp, diffCel2);
   }


   private static void printHelp() {
      System.out.println("Examples of commands:");
      System.out.println("  S -14.5F (to set temperature to -14.5 degrees Fahrenheit)");
      System.out.println("  C 93C (to change temperature by 93 degrees Celsius)");
      System.out.println("  D 20F (to compute difference with a temperature" +
                         " of 20 degrees Fahrenheit)");
      System.out.println("  Q (to quit)");
   }

}