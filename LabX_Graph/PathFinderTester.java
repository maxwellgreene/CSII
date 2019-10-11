import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/* Java application having as its purpose to test the PathFinder class.
** It creates a digraph (i.e., an instance of the Digraph class) using
** the data in a file whose name is provided either as a command line
** argument or by the user in response to a prompt.  Then it has the
** user enter pairs of vertex IDs and, for each pair, prints info about
** a shortest path connecting them (or reporting that no such path exists).
*/

public class PathFinderTester {

   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args)  throws IOException {
      Scanner graphInput;
      String fileName;
      if (args.length != 0) {
         fileName = args[0]; 
      }
      else { 
         System.out.print("Enter name of input file: ");
         fileName = keyboard.nextLine();
      }
      graphInput = new Scanner(new File(fileName));
      Digraph g = buildGraph(graphInput);
      g.printDescription();


      PathFinder pf = new PathFinder();
      final int N = g.numVertices();
      
      // Now have user repeatedly request info about shortest paths.
      boolean keepGoing = true;
      while (keepGoing) {
         System.out.print("\nEnter IDs of start and target vertices: ");
         int start = keyboard.nextInt();
         int target = keyboard.nextInt();

         // The user is prompted to enter two integers.
         // If both are negative, the program halts.  If exactly one is 
         // negative, a description of the digraph is printed.  If both 
         // are nonnegative (say u and v), a shortest path from u to v 
         // is described (if one exists).
         if (start < 0  &&  target < 0) {
            keepGoing = false;
         }
         else if (start < 0  ||  target < 0) {
            g.printDescription();
         }
         else {
            pf.findShortestPath(g, start, target);
            printReport(pf);
         }
      }
      System.out.println("Goodbye.");
   }

   private static void printReport(PathFinder pf) {
      System.out.printf("Start: %d; Target: %d\n", 
                        pf.startVertex(), pf.targetVertex());
      if (!pf.pathExists()) {
         System.out.println("There is no path.");
      }
      else {
         System.out.println("Shortest paths have length " + pf.length());
         System.out.println("One shortest path: " + Arrays.toString(pf.path()));
         System.out.print("One shortest path: ");
         pf.printPath();  System.out.println();
      }
   }


   /* Returns a new Digraph object as described by the source of data to 
   ** which the given Scanner is attached.  The expected format of the
   ** graph's description is that the first line contains an integer N
   ** providing the number of vertices in the graph.  The remaining input
   ** is on the next N lines, the k-th of which contains a list of IDs 
   ** of the vertices to which vertex k-1 has an edge.  Any non-integer
   ** data on a line is taken to indicate the end of the meaningful data
   ** on that line.
   ** Example:
   ** +------------------------------------------------------
   ** |4       // number of vertices
   ** |2 3     // vertex 0 has outgoing edges to vertices 2 and 3
   ** |0       // vertex 1 has an outgoing edge to vertex 0
   ** |        // vertex 2 has no outgoing edges
   ** |2 0 1   // vertex 3 has outgoing edges to vertices 2, 0, and 1
   ** +------------------------------------------------------
   */
   private static Digraph buildGraph(Scanner input) {
      int numVertices = input.nextInt();  input.nextLine();
      Digraph graph = new Digraph(numVertices);
      for (int i = 0; i != numVertices; i++) {
         Scanner edgeScanner = new Scanner(input.nextLine());
         while (edgeScanner.hasNextInt()) {
            int j = edgeScanner.nextInt();
            graph.addEdge(i,j);
         }
      }
      return graph; 
   }

}