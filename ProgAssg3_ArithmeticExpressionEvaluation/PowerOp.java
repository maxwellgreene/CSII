/* PowerOp.java
** An instance of this class represents a power operator appearing in
** an arithmetic expression.
*/
public class PowerOp implements Operator {

   public int precedenceFactor() { return 3; }

   public int apply(Operand x, Operand y) {
      return x.valueOf() ^ y.valueOf();
   }

   public String toString() { return "^"; }

}
