/* PowerOp.java
** An instance of this class represents a power operator appearing in
** an arithmetic expression.
*/
public class PowerOp implements Operator {

   public int precedenceFactor() { return 3; }

   public int apply(Operand x, Operand y) {
      return (int) Math.pow((double) x.valueOf(), (double) y.valueOf());
   }

   public String toString() { return "^"; }

}
