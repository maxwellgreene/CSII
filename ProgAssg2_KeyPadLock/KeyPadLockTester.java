/* Java application whose purpose is to test instances of the KeyPadLock1
*  and KeyPadLock2 classes.
*  @version  October 2019.
*  @author R. McCloskey
*
*  If there is a command line argument, it provides the name of the file
*  containing the input data.  Otherwise, input (for which prompts are
*  displayed) comes from standard input (i.e., the keyboard).  The input
*  data is to include the lock version number (either 1 or 2), the lock's
*  secret code (a sequence of decimal digits), and a sequence of one-character 
*  command codes, each of which (except for the quit command) corresponds 
*  to a key press on the lock's keypad.
*
*  For each command specified, the program reports that it is being 
*  performed, the relevant method is applied to the lock and, afterwards, 
*  the state (OPEN or CLOSED) of the KeyPadLock object is reported.
*
*  The commands are as follows:
*
*     q : quit (i.e., terminate program)
*     o : press OPEN key on the lock's keypad
*     c : press CLOSE key on the lock's keypad
*     r : replace 
*     0 : enter 0 on the lock's keypad
*     1 : enter 1 on the lock's keypad
*     .
*     .
*     9 : enter 9 on the lock's keypad
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class KeyPadLockTester {

   private static final char SPACE = ' ';
   private static final char QUIT = 'q';
   private static final char OPEN = 'o';
   private static final char CLOSE = 'c';
   private static final char REPLACE = 'r';
   private static final String ALL_COMMANDS = "0123456789" + QUIT + OPEN +
                                              CLOSE + REPLACE;

   private static Scanner input;

   public static void main(String[] args) throws FileNotFoundException {

      // Establish the Scanner as reading either from the file whose name
      // was provided as the command line argument or, if there is no such
      // argument, from the keyboard.
      if (args.length != 0) { input = new Scanner(new File(args[0])); }
      else { input = new Scanner(System.in); }

      System.out.print("Enter version of lock (1 or 2): ");
      int version = input.nextInt();
      if (args.length != 0) { System.out.println(version); }
      if (version != 1  &&  version != 2) { 
         version = 1;
         System.out.println("Invalid version number; assuming 1");
      }

      System.out.print("Enter secret code: ");
      String codeStr = input.next();  input.nextLine();
      if (args.length != 0) { System.out.println(codeStr); }
      int[] code = digitStrToIntAry(codeStr);


      KeyPadLock kpl;
      if (version == 1) { kpl = new KeyPadLock1( code ); }
      else { kpl = new KeyPadLock2( code ); }

      System.out.println("A version " + version + " keypad lock with code " + 
                         codeStr + " has been created.");

      processCommands(kpl);

      System.out.println("Goodbye.");
   }

   private static void processCommands(KeyPadLock kpl) {

      boolean keepGoing = true;
      
      while ( keepGoing ) {
         System.out.print("Enter one or more commands: ");
         String commandStr = input.nextLine();
         System.out.println(commandStr);
         int i = 0;
         while (keepGoing && i != commandStr.length()) {
            char command = commandStr.charAt(i); 
            if (command == SPACE) { i = i+1; }  // ignore spaces
            else if (ALL_COMMANDS.indexOf(command) == -1) {
               System.out.print("  Command " + command + " not recognized.");
               i = i+1;
            }
            else {
               System.out.print(" Performing command " + command);
               if (command == QUIT)
                  { keepGoing = false; }
               else if (command == REPLACE) {
                  i = i+1;
                  String newCode = "";
                  while (i != commandStr.length() && 
                         Character.isDigit(commandStr.charAt(i))) {
                     newCode = newCode + commandStr.charAt(i);
                     i = i+1;
                  }
                  System.out.println("Attempting to reset secret code to " + 
                                     newCode);
                  ((KeyPadLock2)(kpl)).replaceCode(digitStrToIntAry(newCode));
               }
               else {
                  try {
                     performCommand(kpl, command);
                     i = i+1;
                  }
                  catch (Exception e) {
                     System.out.println();
                     e.printStackTrace(System.out);
                     System.out.println();
                  }
               }
               String state = kpl.isOpen() ? "OPEN" : "CLOSED";
               System.out.print("; afterwards, lock is " + state);
            }
            System.out.println();
         }
      }
   }


   /* Performs the specified command upon the specified KeyPadLock.
   ** pre: command is either OPEN, CLOSE, or a digit.
   */
   private static void performCommand(KeyPadLock kpLock, char command) {

      if (command == OPEN) { kpLock.open(); }
      else if (command == CLOSE) { kpLock.close(); }
      else if (Character.isDigit(command)) { 
         kpLock.enterDigit( charToDig(command) );
      } 
      else {  // command is not recognized 
         System.out.println("This should never be printed!");
      }
   }

   /* Given a String composed of digit characters (in the range '0'..'9'),
   ** returns an array of integers containing the digits.
   ** Example: Given "56023", the returned array will be [5, 6, 0, 2, 3].
   ** If any character in the given String is not a digit character, an
   ** IllegalArgumentException is thrown.
   */
   private static int[] digitStrToIntAry(String codeStr) {
      int[] result = new int[codeStr.length()];
      for (int i=0;  i != result.length;  i = i+1)  {
         char ch = codeStr.charAt(i);
         if (Character.isDigit(ch)) {
            result[i] = charToDig(ch);
         }
         else {
            throw new IllegalArgumentException(ch + " is not a digit char.");
         }
      }
      return result;
   }


   /* pre:  c is in the range '0'..'9'
   ** post: value returned is the integer value corresponding to c.
   **      E.g., if c is (the char) '4', value returned will be (the int) 4.
   */
   private static int charToDig(char c) { return c - '0'; }
}