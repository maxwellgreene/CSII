/* SubtractOp.java
** An instance of this class represents a subtraction operator appearing in
** an arithmetic expression.
*/
public class SubtractOp implements Operator {

   public int precedenceFactor() { return 1; }

   public int apply(Operand x, Operand y) {
      return x.valueOf() - y.valueOf();
   }

   public String toString() { return "-"; }

}