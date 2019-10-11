import java.util.Iterator;
import java.util.Arrays;

/* PathFinder.java
** An instance of this class is for the purpose of finding shortest paths
** in directed graphs.  The intent is that the client will invoke the
** findShortestPath() method (which finds --if it exists-- a shortest path 
** between two given vertices within a given directed graph) and then call 
** one or more of the observer methods in order to gather information about
** the path that was discovered (if one exists).
**
** Authors: R. McCloskey and ...
*/

public class PathFinder {

   // instance variables
   // ------------------

   // The values of the instance variables are all with respect to the path
   // discovered (if any) by the most recent call to the findShortestpath()
   // method, which finds a shortest path from a specified start to a 
   // specified target vertex (if such a path exists).

   protected int start, target;  // IDs of start and target vertices
   protected boolean pathFound;  // Does a path from start to target exist?

   protected int[] pred;  // pred[w] = -2 means w is the start vertex.
                          // pred[w] = -1 means that w does not lie on the
                          // path that was discovered (if any).
                          // pred[w] == v (v >= 0) means that, if w lies on 
                          // discovered path, (v,w) is an edge on that path.

   // observers
   // ---------

   // The values returned by the observer methods are all with respect
   // to the path discovered (if any) by the most recent call to the 
   // findShortestpath() method.

   /* Returns the ID of the start vertex.
   */
   public int startVertex() { return start; }

   /* Returns the ID of the target vertex.
   */
   public int targetVertex() { return target; }

   /* Reports whether or not a path was discovered.
   */
   public boolean pathExists() { return pathFound; }

   /* Returns the length of the discovered path (or -1 if no such path exists).
   */
   public int length() { // 
      int cntr = -1;
      if (pathFound) {
         cntr = 0;
         int v = target;
         while (v != start) {
            v = pred[v];
            cntr++;
         }
      }
      return cntr; 
   }

   /* Returns an array containing the IDs of vertices on the discovered path
   ** (in the order they would be visited along the path).
   ** If there is no such path, the returned array has length zero.
   */
   public int[] path() {   // STUB!
      int pathLen = length();
      int[] result = new int[pathLen+1];

      // MISSING CODE GOES HERE

      return result;
   }


   /* Prints the IDs of the vertices on the discovered path (in the order
   ** that they would be visited along the path).  If there is no such 
   ** path, nothing is printed.
   */
   public void printPath() { 
      System.out.print(Arrays.toString(path()));
   }


   // mutator
   // -------

   /* Computes some shortest path from u to v in the given digraph (if
   ** such a path exists) so that subsequent calls to the observers will 
   ** yield information about that path.
   */
   public void findShortestPath(Digraph graph, int u, int v) {
      // Establish values of the instance variables
      start = u;  target = v;
      final int N = graph.numVertices();
      pred = new int[N];
      fill(pred, -1);  // Place -1 in every element of pred[]
      pred[u] = -2;    // Mark u as being the start vertex

      if (u == v) { pathFound = true; }
      else { pathFound = false; }

      // Create the queue and prime it by inserting the start vertex.
      Queue<Integer> q = new QueueViaArray<Integer>();
      q.enqueue(u);

      // Loop needed here to "explore" from each vertex in the queue,
      // causing "discovered" vertices to be placed in the queue so that
      // exploration from them will occur later.
      // This breadth-first search should terminate when (and if) the
      // target vertex is discovered.  Along the way, relevant data 
      // is placed into the pred[] array to make it possible for the 
      // length() and path() methods to traverse the path easily.
      q.enqueue(start);
      int temp;
      for (int i=0;i<length();i++)
      {
         temp = q.dequeue();
         
      }

      //  MISSING CODE GOES HERE!
   }


   // utilities
   // ---------

   /* Places the specified value into every element of the specified array.
   */
   private void fill(int[] a, int val) {
      for (int i=0; i != a.length; i++) { a[i] = val; }
   }
}