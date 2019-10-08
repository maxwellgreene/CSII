/* KeyPadLock1.java
** Author: R. McCloskey  and  < your name >
** Date: October 2019
**
** An instance of this class models a locking device that can be either 
** open or closed; to open it requires that a prescribed sequence of digits
** be entered on its keypad.
*/
import java.util.Arrays;

public class KeyPadLock1 implements KeyPadLock {

   // class constant
   // --------------
   protected final int MIN_CODE_LENGTH = 4;

   // instance variables
   // ------------------
   protected int[] secretCode;    // holds the code needed to open the lock
   protected int[] digitsEntered; // holds the most recently entered digits

   // **********************************************************
   // Declaring one or more other instance variables is advised.
   // **********************************************************
   protected boolean open;

 
   // constructors
   // ------------

   /* Initializes this KeyPadLock so that its "secret code" (i.e., the
   ** sequence of digits needed to open it) corresponds to the contents
   ** of the given array (which is assumed to hold one digit per element).
   ** If the length of the given array is less than four, an
   ** IllegalArgumentException is thrown.
   */
   public KeyPadLock1(int[] code) { 
      if(code.length < MIN_CODE_LENGTH)
      { throw new IllegalArgumentException("code must be longer than the minimum: " + MIN_CODE_LENGTH); }
      System.out.println("doing");
      secretCode = code;
      digitsEntered = new int[code.length];  this.reset();
      open = false;
   }


   // observers
   // ---------

   /* Reports whether or not the lock is currently open.
   */
   public boolean isOpen() { return open; }


   // mutators
   // --------

   /* Records that the given digit has been entered on the keypad.  An
   ** IllegalArgumentException is thrown if digit is not in the range 0..9.
   */
   
   public void enterDigit(int digit) { 
      if (digit < 0  ||  digit > 9) { throw new IllegalArgumentException("digit must be between 0 and 9"); }
      
      append(digit);
   }


   /* If the lock is already open, it remains so.  Otherwise, if the digits 
   ** most recently entered on the keypad match the secret code, the lock 
   ** becomes open.  Otherwise, the lock remains closed.
   */
   public void open() {  
       if(Arrays.equals(digitsEntered,secretCode) && !open)
       { 
         open = true; this.reset();
       }
   /*
        if(open){
            System.out.println("LOCK IS ALREADY OPEN, MY FRIEND.");
        } else if(Arrays.equals(digitsEntered,secretCode) && !open){
            open = true;
            System.out.println("ACCESSS GRANTED, THE LOCK HAS BEEN OPEN");
        } else if(open){
            System.out.println("WRONG CODE, BUT LOCK IS STILL OPEN");
        } else {
             System.out.println("ACCESSS DENIED. NICE TRY, SUCKER");
        }
    */
   }


   /* Closes the lock.
   */
   public void close() { 
      open = false;
   }
   
   
   /*Additional methods to keep the above code pretty
   */
   
   private void append(int digit)
   {
      for (int i=0 ; i < digitsEntered.length - 1; i++)
      { digitsEntered[i] = digitsEntered[i+1]; }
      digitsEntered[digitsEntered.length - 1] = digit;
   }
   
   private void reset()
   {
      for (int i=0 ; i<digitsEntered.length; i++)
      { digitsEntered[i] = -1; }
   }

}
