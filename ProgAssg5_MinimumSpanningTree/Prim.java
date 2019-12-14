import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
/* An instance of this class computes a minimum spanning forest (MSF) of the
** graph provided to it upon construction, using an implementation of Prim's
** Algorithm.  Which is to say that the graph produced is a forest of trees,
** each tree in which is a minimum spanning tree of a connected component of
** the given graph.  (If the given graph is connected, that would be a single
** tree.)
**
** Author: R. McCloskey and Maxwell Greene
** CMPS 144, Prog. Assg. #5, December 2019
** Known defects: Sometimes adds duplicate node to new tree, not sure why
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
      fillAry(closest, Integer.MAX_VALUE);

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
      List<Integer> tree = new ArrayList<Integer>();
      tree.add(v);
      int from = 0;
      while(tree.size() < 4){
         for(int fromIndex=0;fromIndex<tree.size();fromIndex++){
            from = tree.get(fromIndex);
            for(int to=0;to<trees.numVertices();to++){
               if(graph.hasEdge(from,to) && tree.contains(from) && !tree.contains(to) && from != to)
               { closest[to] = graph.edgeCost(from,to); }
            }
         }
         int indOfMin = indexOfMin(closest);
         for(int fromIndex=0;fromIndex<tree.size();fromIndex++){
            from = tree.get(fromIndex);
            if(graph.edgeCost(from,indOfMin) == closest[indOfMin]){
               System.out.println("Inserting edge from: "+from+"  to: "+indOfMin+" at cost: "+graph.edgeCost(from,indOfMin));
               trees.insertEdge(from,indOfMin,graph.edgeCost(from,indOfMin));
               tree.add(indOfMin);
            }
         }
         fillAry(closest,Integer.MAX_VALUE);
      }
   }
   
   /* Returns the index of the minimum value in a array
   */
   private int indexOfMin (int [] arr1) {
       int index = 0; int min = arr1[index];
       for (int i=1; i<arr1.length; i++) {
           if (arr1[i] < min) {min = arr1[i];index = i;}
       }
       return index;
   }
   
   /* Returns true if all in a equal b
   */
   private boolean allEqual ( ArrayList<Integer> a, int b){
      for(int i=0;i<a.size();i++){
         if(a.get(i).equals(b)){
            return false;
         }
      }
      return true;
   }

   /* Fills all elements of the given array with the given value.
   */
   private void fillAry(int[] a, int value) {
      for (int i = 0; i != a.length; i++) { a[i] = value; }
   }
}