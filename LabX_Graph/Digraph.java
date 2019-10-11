import java.util.Arrays;
import java.util.Iterator;

/* An instance of this graph represents a directed graph whose vertices
** are identified by the natural numbers 0..N-1 (for some N).
*/
public class Digraph {

   // instance variables
   // ------------------
   protected ArrayList<Integer>[] edges;  // edges[i] is an ArrayList of the
                                          // vertices to which i has an edge

   // constructor
   // -----------

   /* Initializes this digraph to be one having the specified number of 
   ** vertices (numbered starting at zero) and no edges.
   */
   public Digraph(int numVertices) {
      //Note: The Java compiler does not allow the following line:
      //edges = new ArrayList<Integer>[numVertices];
      edges = new ArrayList[numVertices];
      for (int i=0; i != numVertices; i++) {
         edges[i] = new ArrayList<Integer>();
      }
   }


   // observers
   // ---------

   /* Prints a description of the graph.
   */
   public void printDescription() {
      final int N = numVertices();
      System.out.println("Graph has " + N + " vertices.");
      for (int i=0; i != N; i++) {
         System.out.println("Edges from vertex " + i + " go to " + edges[i]);
      }
   }

   /* Returns the number of vertices in this digraph.
   */
   public int numVertices() { return edges.length; }

   /* Reports whether or not there is an edge from u to v.
   */
   public boolean hasEdge(int u, int v) {
      return edges[u].indexOf(v) != -1;
   }

   /* Returns an array containing the IDs of the vertices to which
   ** the specified vertex (u) has an edge.  That is, the array
   ** that is returned contains v iff (u,v) is an edge in this digraph.
   */ 
   public Integer[] neighbors(int u) {
      return (Integer[])(edges[u].toArray());
   }

   /* Returns an Iterator that can iterate through the ID's of the vertices
   ** to which the specified vertex has an edge.  That is, the returned
   ** iterator will contain v among the elements yielded by its next()
   ** method iff (u,v) is an edge in this digraph. 
   */
   public Iterator<Integer> neighborIterator(int u) {
      return edges[u].iterator();
   }

   // mutators
   // --------

   /* Inserts the edge (u,v) into this graph, unless it is already there.
   */
   public void addEdge(int u, int v) {
      ArrayList<Integer> neighbors = edges[u];
      final int NUM_NEIGHBORS = neighbors.size();
      int i = 0;
      while (i != NUM_NEIGHBORS  &&  neighbors.get(i) < v) { i = i+1; }
      if (i == NUM_NEIGHBORS  ||  neighbors.get(i) != v) {
         neighbors.add(i, v);
      }
   }

   /* Removes the edge (u,v) from this graph, unless it is not already there.
   */
   public void removeEdge(int u, int v) {
      ArrayList<Integer> neighbors = edges[u];
      int k = neighbors.indexOf(v);
      if (k != -1) {
         neighbors.remove(k);
      }
   }

}