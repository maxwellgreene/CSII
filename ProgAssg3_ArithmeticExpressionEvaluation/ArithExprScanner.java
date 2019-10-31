import java.util.Iterator;
import java.util.Scanner;

/* An instance of this class interprets a given String as an arithmetic
** expression, returning the tokens contained in that expression one after
** the other (via the next() method).
**
** This is a very unsophisticated scanner in that it assumes that 
** occurrences of tokens are separated by whitespace.
*/

public class ArithExprScanner implements Iterator<Token> {

   // class constants
   // ---------------
   private static final LeftParen LEFT_PAREN = new LeftParen();
   private static final RightParen RIGHT_PAREN = new RightParen();
   private static final AddOp ADD = new AddOp();
   private static final SubtractOp SUBTRACT = new SubtractOp();
   private static final MultiplyOp MULTIPLY = new MultiplyOp();
   private static final DivideOp DIVIDE = new DivideOp();
   private static final PowerOp POWER = new PowerOp();


   // instance variable
   // -----------------
   private Scanner scanner;   


   // constructor
   // -----------

   /* Establishes the String that is to be scanned.
   */
   public ArithExprScanner(String expr) {
      scanner = new Scanner(expr);
   }


   // observer
   // --------

   /* Reports whether any more tokens are yet to be returned.
   */
   public boolean hasNext() { return scanner.hasNext(); }

 
   // observer/mutator
   // ----------------

   /* Returns the next token (i.e., that following the token returned
   ** by the most recent call of this method, or, in case this method
   ** has not been called before, the first token).
   */
   public Token next() {

      Token result;
      String elem = scanner.next();

      if (elem.equals("("))      { result = LEFT_PAREN; }
      else if (elem.equals(")")) { result = RIGHT_PAREN; }
      else if (elem.equals("+")) { result = ADD; }
      else if (elem.equals("-")) { result = SUBTRACT; }
      else if (elem.equals("*")) { result = MULTIPLY; }
      else if (elem.equals("/")) { result = DIVIDE; }
      else if (elem.equals("^")) { result = POWER; }
      else if (isIntLiteral(elem)) { 
         result = new IntLiteral(Integer.parseInt(elem));
      }
      else { result = new UnRecognizableToken(elem); }
      return result;
   }

   /* Reports whether the given string is an integer literal, which is to
   ** say that it is composed entirely of char values in the range '0'..'9'.
   ** pre: s.length() != 0
   */
   private boolean isIntLiteral(String s) {
      boolean allDigits = true;
      int i = 0;
      // loop invariant: All characters in s[0..i) are digit chars.
      while (allDigits && i != s.length()) {
         char ch = s.charAt(i);
         allDigits = '0' <= ch  &&  ch <= '9';
         i = i+1;
      }
      return allDigits; 
   }
   
}