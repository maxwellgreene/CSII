import java.util.List;

/* An instance of a class that implments this interface represents an
** undirected graph each of whose edges has an associated cost, of type int.
** (A valid cost is assumed to be at least zero.)  The nodes/vertices are
** identified by an initial segment of the natural numbers.
**
** Author: R. McCloskey
** Date: December 2019
*/

public interface Graph {

   // observers
   // ---------

   /* Returns a measure of the amount of work done by the object in performing
   ** its methods.
   */
   int measureOfWork();

   /* Returns the # of vertices/nodes in this graph.
   */
   int numVertices();


   /* Returns the # of edges in this graph.
   */
   int numEdges();

   /* Returns the degree of node v (i.e., the number of edges incident
   ** to it).
   */
   int degreeOf(int v);

   /* Reports whether or not this graph includes the edge {u,v}.
   */
   boolean hasEdge(int u, int v);

   /* Returns the cost of the edge {u,v}, or -1 if no such edge exists.
   */
   int edgeCost(int u, int v);


   /* Returns the sum of the costs of all edges in this graph.
   */
   int sumOfEdgeCosts();



   // mutators
   // --------

   /* Inserts the edge {u,v} and assigns to it the specified cost.
   ** pre: cost >= 0
   */
   void insertEdge(int u, int v, int cost);

   /* Removes the edge {u,v} from this graph.
   */
   void removeEdge(int u, int v);

   /* Sets the cost of the specified edge (i.e., {u,v}) to the value specified.
   ** pre: hasEdge(u,v) && cost >= 0
   */
   void setEdgeCost(int u, int v, int cost);


   // generators
   // ----------

   /* Returns a List containing the (ID's of) nodes that are
   ** adjacent to the given node (v) via an edge.
   */ 
   List<Integer> neighborList(int v);
}
