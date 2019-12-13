import java.util.ArrayList;

/* An instance of this class represents an undirected graph each of whose
** edges has an associated cost, of type int.  (A valid cost is assumed
** to be at least zero.)  The nodes/vertices are identified by an initial
** segment of the natural numbers.
**
** Author: R. McCloskey
** Date: December 2019
*/

public class GraphViaMatrix implements Graph {

   // instance variables
   // ------------------
   protected final int N;   // number of vertices in the graph
   protected int[][] edges; // edges[u][v] is cost of edge {u,v}, or -1
                            // if there is no such edge
   protected int[] degree;  // degree[k] = # edges incident to vertex k
   protected int numEdges;  // # of edges in the graph
   protected int loopCntr;


   /* Establishes this graph as having the given number of nodes/vertices
   ** and no edges.
   */
   public GraphViaMatrix(int numVertices) {
      loopCntr = 0;
      N = numVertices;
      numEdges = 0;
      degree = new int[N];
      initializeEdges();
   }

   /* Initializes the edges[][] array so that no edges exist.
   */
   protected void initializeEdges() {
      edges = new int[N][];
      for (int i = N; i != 0; i--) {
         edges[i-1] = new int[i];
         fillAry(edges[i-1], -1);
      }
      loopCntr = loopCntr + N;
   }

   // observers
   // ---------

   /* Returns a measure of the computational work performed by this object
   ** during its lifetime.
   */
   public int measureOfWork() { return loopCntr; }

   /* Returns the # of vertices/nodes in this graph.
   */
   public int numVertices() { return N; }


   /* Returns the # of edges in this graph.
   */
   public int numEdges() { return numEdges; }

   /* Returns the degree of node v (i.e., the number of edges incident
   ** to it).
   */
   public int degreeOf(int v) {
      return degree[v];
      // before degree[] was introduced:
      //int cntr = 0;
      //for (int w = 0; w != N; w++) {
      //   if (hasEdge(v,w)) { cntr++; }
      //}
      //return cntr;
   }

   /* Reports whether or not this graph includes the edge {u,v}.
   */
   public boolean hasEdge(int u, int v) {
      return edgeCost(u,v) != -1;
   }

   /* Returns the cost of the edge {u,v}, or -1 if no such edge exists.
   */
   public int edgeCost(int u, int v) {
      if (u >= v) { return edges[u][v]; }
      else { return edges[v][u]; }
   }


   /* Returns the sum of the costs of all edges in this graph.
   */
   public int sumOfEdgeCosts() {
      int sumSoFar = 0;
      for (int i = N-1; i != -1; i--) {
         for (int j = 0; j < i; j++) {
            if (hasEdge(i,j)) { sumSoFar = sumSoFar + edgeCost(i,j); }
         }
         loopCntr = loopCntr + i;
      }
      return sumSoFar;
   }

   /* Returns a string describing this graph.
   */
   public String toString() {
      String result = "";
      for (int i = N-1; i != -1; i--) {
         //result = i + ":" + neighborList(i) + '\n' + result;
         result = '\n' + result;
         for (int j = N-1; j != i; j--) {
            int cost = edgeCost(j,i);
            if (cost != -1) {
               result = "(" + j + "," + cost + ") " + result;
            }
         }
         result = i + " (" + degreeOf(i) + "): " + result;
      }
      return result;
   }


   // mutators
   // --------

   /* Inserts the edge {u,v} and assigns to it the specified cost.
   ** pre: !hasEdge(u,v) && cost >= 0
   */
   public void insertEdge(int u, int v, int cost) {
      if (cost < 0) {
         throw new IllegalArgumentException("Attempt to insert an edge " +
                                            "with negative cost " + cost);
      }
      if (!hasEdge(u,v)) {
         degree[u]++; degree[v]++;
         numEdges++;
      }
      setEdgeCost(u, v, cost);
   }

   /* Removes the edge {u,v} from this graph.
   */
   public void removeEdge(int u, int v) {
      if (hasEdge(u,v)) {
         numEdges--;
         degree[u]--;
         degree[v]--;
         setEdgeCostPrivate(u,v,-1);
      }
   }

   /* Sets the cost of the specified edge (i.e., {u,v}) to the specified value.
   ** pre: hasEdge(u,v) && cost >= 0
   */
   public void setEdgeCost(int u, int v, int cost) {
      setEdgeCostPrivate(u, v, cost);
   }


   // generators
   // ----------

   /* Returns an ArrayList containing the (ID's of) nodes that are
   ** adjacent to the given node (v) via an edge.
   */
   public ArrayList<Integer> neighborList(int v) {
      ArrayList<Integer> neighborList = new ArrayList<Integer>();
      for (int w = 0; w != N; w++) {
         if (edgeCost(v,w) != -1) {
            neighborList.add(w);
         }
      }
      loopCntr = loopCntr + N;
      return neighborList;
   }


   // private methods
   // ---------------

   public void setEdgeCostPrivate(int u, int v, int cost) {
      if (u >= v)
         { edges[u][v] = cost; }
      else
         { edges[v][u] = cost; }
   }

   /* Fills the elements of the given array with the given value.
   */
   private void fillAry(int[] ary, int val) {
      for (int j=0; j != ary.length; j++) {
         ary[j] = val;
      }
      loopCntr = loopCntr + ary.length;
   }

}
