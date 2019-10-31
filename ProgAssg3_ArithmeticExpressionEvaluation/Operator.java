/* Operator.java
** An instance of an implementing class represents an operator that appears
** in an arithmetic expression.
*/

public interface Operator extends Token {

   /* Returns the precedence factor of this Operator.
   */
   int precedenceFactor();

   /* Returns the result of applying this operator to the given operands.
   */
   int apply(Operand x, Operand y);
}