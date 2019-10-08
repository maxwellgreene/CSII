/** Java interface that models the concept of a keypad lock that can be
*   opened by entering a sequence of digits, referred to as its code.
*
*  @author R. McCloskey
*  @version October 2019
*/

public interface KeyPadLock {
 
   // observers/queries/reporters/accessors
   // -------------------------------------

   /* Reports whether or not the lock is open.
   */
   public boolean isOpen(); 


   // mutators/transformers/modifiers
   // -------------------------------

   /* Records fact that the specified digit was entered on the keypad.
   ** pre:  0 <= digit < 10
   ** post: state of the lock has been updated, if warranted, to reflect
   **       the fact that the specified digit was entered via the keypad.
   ** throws: IllegalArgumentException if argument is not in range 0..9
   */
   public void enterDigit(int digit) throws IllegalArgumentException;


   /* Causes the lock to go into (or remain in) the open state, if warranted
   ** (depending upon the digits most recently entered via the keypad).
   ** pre:  none
   ** post: If lock was already open or if the most recently entered digits
   **       match the lock's code, the lock is in the open state.  Otherwise,
   **       it remains closed.
   */
   public void open(); 


   /* Causes to lock to go to (or remain in) the closed state.
   ** pre:  none
   ** post: !isOpen()
   */
   public void close();
  
}