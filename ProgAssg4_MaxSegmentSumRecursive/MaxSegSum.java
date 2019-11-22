/* Java class that includes methods for determining the maximum segment sum
** of an array (or segment thereof) containing int values.
**
** Author: R. McCloskey and Maxwell Greene
** Date: November 2019
*/
public class MaxSegSum {

   /* Returns the largest sum among all segments of the given array.
   */
   public static int maxSegSumRec(int[] a) {
      return maxSegSumRec(a, 0, a.length);
   }

   /* Returns the largest sum among all segments of the given array segment
   ** (i.e., a[low..high)).  Uses a recursive approach.
   ** pre: 0 <= low <= high <= a.length
   */
   public static int maxSegSumRec(int[] a, int low, int high) {
     //#######################################################
     if (low == high) { return 0; }

     int mid = (low + high)/2;

      return Math.max(Math.max(maxSegSumRec(a, low, mid),
                    maxSegSumRec(a, mid+1, high)),
                    maxCrossSum(a, low, mid, high));
      //#######################################################
   }

   /* Returns the largest sum of any prefix of the given array segment.
   ** A prefix of the given segment would be b[bottom..k), for some k
   ** satisfying bottom <= k <= top.
   ** pre: 0 <= bottom <= top <= b.length
   */
   private static int maxPrefixSum(int[] b, int bottom, int top) {
     //#######################################################
     int max = Integer.MIN_VALUE;
     int[] bSum = prefixSums(b, bottom, top);
     for (int i=0; i < bSum.length;i++) //b.length
     {
       max = Math.max(bSum[i],max);
     }
      return max;
    //#######################################################
   }

   /* Returns the largest sum of any suffix of the given array segment.
   ** A suffix of the given segment would be b[k..top), for some k
   ** satisfying bottom <= k <= top.
   ** pre: 0 <= bottom <= top <= b.length
   */
   private static int maxSuffixSum(int[] b, int low, int high) {
     //#######################################################
     int max = Integer.MIN_VALUE;
     int[] bSum = suffixSums(b, low, high);
     for (int i=0; i < bSum.length;i++)
     {
       max = Math.max(bSum[i],high);
     }
      return max;
      //#######################################################
   }

   private static int maxCrossSum(int b[], int low, int mid, int high) {
     //#######################################################
     return (maxPrefixSum(b, low, mid) + maxSuffixSum(b, mid, high));
     //#######################################################
   }


   // -----------------------------------------------------------------
   // Remaining methods are for computing maximum segment sum via an
   // approach that makes use of the given array's "prefix sums".


   /* Returns the maximum segment sum of the given array, computed using
   ** the prefix sums approach.
   */
   public static int maxSegSumViaPrefixSums(int[] a) {
      return maxSegSumViaPrefixSums(a, 0, a.length);
   }

   /* Returns the maximum segment sum of the given array segment
   ** (i.e., a[low..high)), computed using the prefix sums approach.
   ** pre: 0 <= low <= high <= a.length
   */
   public static int maxSegSumViaPrefixSums(int[] a, int low, int high) {
      int[] prefixSums = prefixSums(a, low, high);
      int minPrefixSumSoFar = 0;
      int maxDiffSoFar = 0;
      for (int i=0; i != prefixSums.length; i++) {
         maxDiffSoFar = Math.max(maxDiffSoFar,
                                 prefixSums[i] - minPrefixSumSoFar);
         minPrefixSumSoFar = Math.min(minPrefixSumSoFar, prefixSums[i]);
      }
      return maxDiffSoFar;
   }

   /* Returns a new array containing the prefix sums of the given array
   ** segment (i.e., b[bottom..top)).  That is, the element at location k
   ** of the returned array will contain the sum of the first k+1 elements
   ** of the given array segment.
   ** pre: 0 <= bottom <= top <= b.length
   */
   private static int[] prefixSums(int[] b, int bottom, int top) {
      final int N = top - bottom;
      int[] result = new int[N];
      if (N != 0) {
         result[0] = b[bottom];
         for (int i=1; i != N; i++) {
            result[i] = result[i-1] + b[bottom+i];
         }
      }
      return result;
   }

   /* Returns a new array containing the suffix sums of the given array
   ** segment (i.e., b[bottom..top)).  That is, the element at location k
   ** of the returned array will contain the sum of the last top-k elements
   ** of the given array segment.
   ** pre: 0 <= bottom <= top <= b.length
   */
   private static int[] suffixSums(int[] b, int bottom, int top) {
      final int N = top - bottom;
      int[] result = new int[N];
      if (N != 0) {
         result[0] = b[bottom];
         for (int i=1; i != N; i++) {
            result[i] = result[i-1] + b[top-i];
         }
      }
      return result;
   }
}
