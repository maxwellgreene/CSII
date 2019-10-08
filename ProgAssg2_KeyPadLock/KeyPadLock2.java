/* KeyPadLock2.java (extends KeyPadLock1)
** CMPS 144 Fall 2019 Prog. Assg. #2
** Author: R. McCloskey and < student's name >
** Date: October 2019
** Collaborated with: ...
** Known defects: ...
**
** An instance of this class, like its parent, models a locking device that
** can be either open or closed; to open it requires that a prescribed 
** sequence of digits be entered on its keypad.  An instance of this class
** differs from an instance of its parent in that
** (1) its secret code can be replaced via a call to replaceCode()
** (2) digits entered on the keypad while the lock is open are ignored.
*/
import java.util.Arrays;

public class KeyPadLock2 extends KeyPadLock1 {

   // constructors
   // ------------

   /* Initializes this KeyPadLock2 so that its "secret code" (i.e., the
   ** sequence of digits needed to open it) corresponds to the contents
   ** of the given array (which is assumed to hold one digit per element).
   */
   public KeyPadLock2(int[] code) { 
      super(code); 
      if(code.length < MIN_CODE_LENGTH)
      { throw new IllegalArgumentException("code must be longer than the minimum: " + MIN_CODE_LENGTH); }
      System.out.println("I am picking up what you're putting down."); 

   }


   // mutators
   // --------

   /* Provided that the lock is not open, the given digit is recorded as
   ** having been entered on the keypad.  Otherwise, the digit is ignored.
   ** An IllegalArgumentException is thrown if digit is not in the range 0..9.
   */
   @Override
   public void enterDigit(int digit) { 
      if(!super.open)
      { super.enterDigit(digit); }
   }

   
   /* Provided that the lock is open, its secret code is replaced by the 
   ** one specified.  Otherwise, nothing changes.
   */
   public void replaceCode(int[] newCode) {
      System.out.println(Arrays.toString(super.secretCode));
      if(super.open)
      { super.secretCode = newCode; }
      System.out.println(Arrays.toString(super.secretCode));
   }
}
