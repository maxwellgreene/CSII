/* ArithExprEvalApp.java
** Java application that evaluates arithmetic expressions entered by the user.
**
** Author: R. McCloskey
** Date: September 2019
*/

import java.util.Scanner;
import java.io.IOException;

public class ArithExprEvalApp {

   /* Repeatedly evaluates arithmetic expressions entered at the prompt.
   ** Terminates when the empty string is given in response.
   */
   public static void main(String[] args) throws Exception {

      Scanner s = new Scanner(System.in);
      ArithExprEvaluator evaluator = new ArithExprEvaluator();
      boolean keepGoing = true; 

      while (keepGoing) {
         try {
            System.out.print("\nEnter expression:");
            String expr = s.nextLine();
            if (expr.length() == 0)     // empty string means user is quitting
               { keepGoing = false; }
            else 
               { System.out.println(evaluator.evaluate(expr)); }
         }
         catch (Exception e) {
            e.printStackTrace(System.out);
         }
      }
      System.out.println("Goodbye.");
   }

}