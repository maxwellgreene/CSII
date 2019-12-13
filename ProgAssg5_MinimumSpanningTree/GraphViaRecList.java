import java.util.ArrayList;

/* An instance of a class that implments this interface represents an
** undirected graph each of whose edges has an associated cost, of type int.
** (A valid cost is assumed to be at least zero.)  The nodes/vertices are
** identified by an initial segment of the natural numbers.
**
** Author: R. McCloskey
** Date: December 2019
*/

public class GraphViaRecList implements Graph {

   // instance variables
   // ------------------

   protected final int N;     // number of vertices in this graph
   protected RecursiveList<Edge>[] edges;  // edges[k]
   protected int[] degree;    // degree[k] is node k's degree
   protected int numEdges;    // number of edges in this graph
   protected int loopCntr;    // measure of computational work

   // constructor
   // -----------

   /* Establishes this graph as having the given number of nodes/vertices
   ** and no edges.
   */
   public GraphViaRecList(int numVertices) {
      loopCntr = 0;
      N = numVertices;
      numEdges = 0;
      degree = new int[N];
      initializeEdges();
   }

   /* Initializes the edges[] array so that no edges exist.
   */
   protected void initializeEdges() {
      Edge dummyEdge = new Edge(0,0);
      RecursiveList emptyList =
         new RecursiveListNonEmpty<Edge>(dummyEdge).tailOf();
      //edges = (RecursiveList<Edge>[])(new Object[N]);
      edges = new RecursiveList[N];
      for (int i = N; i != 0; i--) {
         edges[i-1] = emptyList;
      }
      loopCntr = loopCntr + N;
   }



   // observers
   // ---------

   /* Returns a measure of the amount of work done by the object in performing
   ** its methods.
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
   public int degreeOf(int v) { return degree[v]; }

   /* Reports whether or not this graph includes the edge {u,v}.
   */
   public boolean hasEdge(int u, int v) {
      if (degreeOf(u) <= degreeOf(v)) {
         return findEdge(v, edges[u]) != null;
      }
      else {
         return findEdge(u, edges[v]) != null;
      }
   }


   /* Returns the cost of the edge {u,v}, or -1 if no such edge exists.
   */
   public int edgeCost(int u, int v) {
      Edge edge;
      if (degreeOf(u) <= degreeOf(v)) {
         edge = findEdge(v, edges[u]);
      }
      else {
         edge = findEdge(u, edges[v]);
      }
      return edge == null ? -1 : edge.cost;
   }


   /* Returns the sum of the costs of all edges in this graph.
   */
   public int sumOfEdgeCosts() { return 0; }  // STUB!

   /* Returns a string describing this graph.
   ** This can be done more efficiently by using lower level means.
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
   ** pre: cost >= 0
   */
   public void insertEdge(int u, int v, int cost) {
      edges[u] = edges[u].cons(new Edge(v, cost));
      edges[v] = edges[v].cons(new Edge(u, cost));
      degree[u]++;
      degree[v]++;
   }

   /* Removes the edge {u,v} from this graph.
   */
   public void removeEdge(int u, int v) {
      // STUB
   }

   /* Sets the cost of the specified edge (i.e., {u,v}) to the value specified.
   ** pre: hasEdge(u,v) && cost >= 0
   */
   public void setEdgeCost(int u, int v, int cost) {
      // STUB
   }


   // generators
   // ----------

   /* Returns an ArrayList containing the (ID's of) nodes that are
   ** adjacent to the given node (v) via an edge.
   */
   public ArrayList<Integer> neighborList(int v) {
      final int degOfV = degreeOf(v);
      ArrayList<Integer> result = new ArrayList<Integer>(degOfV);
      RecursiveList<Edge> edgeList = edges[v];
      while (!edgeList.isEmpty()) {
         int w = edgeList.headOf().targetNode;
         result.add(w);
         edgeList = edgeList.tailOf();
      }
      loopCntr = loopCntr + degOfV;
      return result;
   }

/*  The class Ege has been elevated to exist outside this one.
   An instance of this class represents an edge.
   /
   private class Edge {
      public int targetNode;
      public int cost;

      public Edge(int target, int cost) {
         targetNode = target;  this.cost = cost;
      }

      public String toString() {
         return "Target node: " + targetNode + "; cost: " + cost;
      }
   }
*/


   // Utility methods
   // ---------------

   /* Returns a reference to the edge appearing in the given list and
   ** having the specified target node, if it exists.  If it doesn't
   ** exist, returns null.
   */
   private Edge findEdge(int k, RecursiveList<Edge> list) {
      System.out.println("Searching for edge to node " + k);
      Edge result = null;
      while (!list.isEmpty()  &&  list.headOf().targetNode != k) {
         loopCntr++;
         System.out.println("  Going past edge " + list.headOf());
         list = list.tailOf();
      }
      if (!list.isEmpty()) {
         result = list.headOf();
         System.out.println("  Found it: " + list.headOf());
      }
      else {
         System.out.println("  Not found!");
      }
      return result;
   }

}
