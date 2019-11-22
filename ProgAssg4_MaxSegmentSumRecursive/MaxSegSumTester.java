import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/* Java application having as its purpose to test the methods of the
** MaxSegSum class.
*/

public class MaxSegSumTester {

   public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);
      int aryLen = getInt(keyboard, "Enter desired array length:");
      int lowerBound = getInt(keyboard, "Enter element lower bound:");
      int upperBound = getInt(keyboard, "Enter element upper bound:");
      int randSeed = getInt(keyboard, "Enter seed for random # generation:");

      // Create an array containing pseudorandom values in the range
      // specified by the user.
      RandomIntAryGenerator riag = new RandomIntAryGenerator(new Random(randSeed));
      int[] a = riag.randomArray(aryLen, lowerBound, upperBound);

      System.out.println("Array generated:");
      System.out.println(Arrays.toString(a));

      // Now compute the maximum segment sum of that array, using
      // two different methods in the MaxSegSum class.  Verify that
      // they yield the same result.
      int result1 = MaxSegSum.maxSegSumRec(a);
      int result2 = MaxSegSum.maxSegSumViaPrefixSums(a);
      System.out.println("Maximum segment sum " +
                         "(as computed by maxSegSumRec()) is " + result1);
      if (result1 != result2) {
         System.out.println("**ERROR; correct result is " + result2);
      }

   }


   private static int getInt(Scanner scanner, String prompt) {
      System.out.print(prompt);
      return scanner.nextInt();
   }

}
