import java.util.Random;

/* An instance of this class is for the purpose of generating
** arrays of type int[] containing pseudorandomly-gentered numbers.
*/

public class RandomIntAryGenerator {

   // instance variable
   // -----------------
   private Random rand;  // for generating pseudorandom numbers


   // constructors
   // ------------

   /* Establishes this array generator as having the specified
   ** Random object as its "resident" pseudorandom number generator.
   */
   public RandomIntAryGenerator(Random random) { rand = random; }

   /* Establishes this array generator as having a newly-created
   ** Random object as its "resident" pseudorandom number generator.
   */
   public RandomIntAryGenerator() { this(new Random()); }


   // method of interest
   // ------------------

   /* Returns a new array of the specified length containing pseudorandom
   ** numbers in the specified range (i.e., [bottom..top]).
   */
   public int[] randomArray(int length, int bottom, int top) {
      final int N = top - bottom + 1;
      int[] result = new int[length];
      for (int i=0; i != length; i++) {
         result[i] = rand.nextInt(N) + bottom;
      }
      return result;
   }

}
