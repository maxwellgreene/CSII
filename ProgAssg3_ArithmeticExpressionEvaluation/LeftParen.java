/* LeftParen.java
** An instance of this class represents a left parenthesis appearing in
** an arithmetic expression.
** It is somewhat awkward to classify a left parenthesis as an operator,
** but it is for the purpose of being able to place them onto the
** operator stack in the expression evaluation algorithm, as that algorithm
** treats left parentheses as a special kind of operator.
*/
public class LeftParen implements Operator {

   public int precedenceFactor() { return 0; }

   /* This method should never be applied, as a left paren is not
   ** a binary operator.
   */
   public int apply(Operand x, Operand y) {
      return 0;
   }

   public String toString() { return "("; }

}
