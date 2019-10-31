/* AddOp.java
** An instance of this class represents an addition operator appearing in
** an arithmetic expression.
*/
public class AddOp implements Operator {

   public int precedenceFactor() { return 1; }

   public int apply(Operand x, Operand y) {
      return x.valueOf() + y.valueOf();
   }

   public String toString() { return "+"; }

}