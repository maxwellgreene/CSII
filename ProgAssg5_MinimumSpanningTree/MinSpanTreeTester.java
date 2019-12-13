import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/* Java application whose purpose is to run tests upon the Prim class.
** It expects as input four numbers:
** (1) # of nodes/vertices in the "random" graph to be constructed (int)
** (2) The probability that two given nodes are connected by an edge
**     (a real number in the interval [0,1])
** (3) A lower bound on the cost assigned to any edge (int)
** (4) An upper bound on the cost assigned to any edge (int)
**
** If a command line argument is provided, it is taken to be the name of a
** file containing these inputs.  Otherwise, the user is expected to enter
** the inputs in response to displayed prompts.
**
** The program constructs a random graph (with costs associated to the edges) in
** accord with the inputs and then uses an instance of the Prim class to construct
** a minimum spanning forest (MSF) of that graph.
**
** Author: R. McCloskey
** Date: December 2019
*/

public class MinSpanTreeTester {

   private static Scanner input;

   public static void main(String[] args) throws FileNotFoundException {

      // Establish input as coming either from the file named by a command line
      // argument or, absent there being one, from the keyboard.
      if (args.length != 0) {
         input = new Scanner(new File(args[0]));
      }
      else {
         input = new Scanner(System.in);
      }

      // Read the inputs.
      int n = getInt(input, "Enter # of nodes in graph:", 1, 50000);
      double edgeProb = getDouble(input, "Enter edge probability:", 0.0, 1.0);
      int minCost = getInt(input, "Enter lower bound for edge costs:",
                           0, Integer.MAX_VALUE-1);
      int maxCost = getInt(input, "Enter upper bound for edge costs:",
                           minCost, Integer.MAX_VALUE-1);

      // Build a random graph in accord with the inputs and display it
      Random rand = new Random(5);
      Graph g = makeRandomGraph(n, edgeProb, minCost, maxCost, rand);
      System.out.println("Graph:\n" + g);

      // Create an instance of Prim and use it to compute a MSF of the graph.
      Prim prim = new Prim(g);
      Graph trees = prim.minSpanningForest();

      // Display the computed MSF.
      System.out.println("Min spanning forest:\n" + trees);
      System.out.println("Number of edges: " + trees.numEdges());
      System.out.println("Sum of edge costs: " + trees.sumOfEdgeCosts());

      // Report how much computation was performed.
      System.out.println("Computational work: " + prim.measureOfWork());
   }

   /* Returns a random graph having the specified # of vertices, with
   ** each pair of vertices being adjacent with the given probability,
   ** and with costs associated to the edges uniformally distributed
   ** between the given minimum and maximum costs.
   */
   public static Graph makeRandomGraph(int n, double edgeProb,
                                       int minCost, int maxCost,
                                       Random rand) {
      int maxMinDelta = maxCost - minCost + 1;
      Graph graph = new GraphViaMatrix(n);
      // Alternative to line above:
      // Graph graph = new GraphViaRecList(n);

      for (int i= n-1; i != 0; i--) {
         for (int j = 0; j != i; j++) {
            if (rand.nextDouble() < edgeProb) {
               graph.insertEdge(i, j, minCost + rand.nextInt(maxMinDelta));
            }
         }
      }
      return graph;
   }

   /* Prints the given prompt, then returns the (expected) int value
   ** read by the given Scanner.
   */
   private static int getInt(Scanner in, String prompt, int min, int max) {
      System.out.print(prompt);
      int result = in.nextInt();
      if (min <= result && result <= max) {
         return result;
      }
      else {
         throw new RuntimeException("Input out of range " + min + ".." + max);
      }
   }

   /* Prints the given prompt, then returns the (expected) double value
   ** read by the given Scanner.
   */
   private static double getDouble(Scanner in, String prompt,
                                   double min, double max) {
      System.out.print(prompt);
      double result = in.nextDouble();
      if (min <= result && result <= max) {
         return result;
      }
      else {
         throw new RuntimeException("Input out of range " + min + ".." + max);
      }
   }
}
