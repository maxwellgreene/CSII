import java.util.List;
import java.util.Iterator;

/* An instance of this class computes a minimum spanning forest (MSF) of the
** graph provided to it upon construction, using an implementation of Prim's
** Algorithm.  Which is to say that the graph produced is a forest of trees,
** each tree in which is a minimum spanning tree of a connected component of
** the given graph.  (If the given graph is connected, that would be a single
** tree.)
**
** Author: R. McCloskey and <your name>
** CMPS 144, Prog. Assg. #5, December 2019
** Collaborated with: ...
** Known defects: ...
*/

public class Prim {

   // instance variables
   // ------------------

   private final Graph graph;     // input graph
   private final Graph trees;     // MSF of 'graph' (output)
   private final int N;           // # of vertices in the graph
   private final int[] closest;   // see below
   private SetIntRange Z;         // set of vertices that are candidates to
                                  // have an incident edge placed into 'trees'
   private int loopCntr;          // to count loop iterations, as a measure
                                  // of how much work was performed in
                                  // computing the MSF
   // constructor
   // -----------

   /* Computes a MSF of the given graph, which can then be obtained via
   ** an observer method.
   */
   public Prim(Graph g) {
      graph = g;
      N = graph.numVertices();

      // Establish 'trees' as containing all the vertices of 'graph'
      // but no edges.
      trees = new GraphViaMatrix(N);  // or trees = new GraphViaRecList(N);

      // Establish closest[] as indicating that no vertex "knows" which
      // vertex already in a tree is closest to it.  As the algorithm
      // proceeds, closest[y] = x (0 <= x < N) means that, among the
      // vertices already absorbed into a tree, x is the one that is
      // "closest" to vertex y (i.e., edge {x,y} has minimum cost among
      // edges connecting y to a vertex in a tree).
      closest = new int[N];
      fillAry(closest, -1);

      // Establish Z (the set of vertices not yet included in a tree but
      // connected by an edge to a vertex that has been) as being an
      // empty set whose universe is the set of all vertex IDs.
      Z = new SetIntRangeViaBitVector(0, N-1);
      // alternative to statement above:
      // Z = new SetIntRangeViaRecList(0, N-1);

      // Insert edges into 'trees' that make it into a MSF of 'graph'
      computeMinSpanningForest();
   }


   // observers
   // ---------

   /* Returns a minimum spanning forest (MSF) of the graph that was
   ** provided to the constructor.  (A MSF of a graph G = (V,E) is a
   ** spanning subgraph G' = (V,E') (E' subset of E) that is a forest
   ** of trees, each of which is a minimum spanning tree (MST) of a
   ** connected component of G.
   */
   public Graph minSpanningForest() { return trees; }

   /* Returns a measure of how much work was performed in computing
   ** the MSF.
   */
   public int measureOfWork() {
      return loopCntr + Z.measureOfWork() +
             graph.measureOfWork() + trees.measureOfWork();
   }


   // private methods
   // ---------------

   /* Computes a MSF (minimal spanning forest) of the graph that was
   ** provided to the constructor, leaving the result in instance variable
   ** trees.
   */
   private void computeMinSpanningForest() {

      for (int v = 0; v != N; v++) {
         if (trees.degreeOf(v) == 0) {   // v is not yet in any MST, so use
            addTreeToForest(v);          // it as the root of a new tree
         }
      }
      loopCntr = loopCntr + N;
   }


   /* Inserts into 'trees' a set of edges that form a minimum spanning tree
   ** (MST) of the connected component of 'graph' containing vertex v.
   */
   private void addTreeToForest(int v) {
      // STUB!
   }


   /* Fills all elements of the given array with the given value.
   */
   private void fillAry(int[] a, int value) {
      for (int i = 0; i != a.length; i++) { a[i] = value; }
   }
}
