
public class IntLiteral implements Operand {

   // instance variable
   // -----------------

   private int value;


   // constructor
   // -----------

   public IntLiteral(int val) { value = val; }


   // observer
   // --------

   public int valueOf() { return value; }

   public String toString() { return value + ""; }
}