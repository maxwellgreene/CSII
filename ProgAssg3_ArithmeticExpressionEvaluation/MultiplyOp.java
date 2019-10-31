/* MultiplyOp.java
** An instance of this class represents a multiplication operator appearing in
** an arithmetic expression.
*/
public class MultiplyOp implements Operator {

   public int precedenceFactor() { return 2; }

   public int apply(Operand x, Operand y) {
      return x.valueOf() * y.valueOf();
   }

   public String toString() { return "*"; }

}