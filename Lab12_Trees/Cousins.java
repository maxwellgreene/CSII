import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Cousins.java
**
** Author: R. McCloskey,  Maxwell Greene, Lasha Basadzishvili
** Date: November 2019
** Known Defects: ...
**
** Java application that builds a FamilyForest according to the data in 
** the file whose name is provided via a command-line argument (or run
** argument, as jGrasp calls it) and then, repeatedly, in response to the 
** user identifying two nodes, describes the relationship between them.
** There are four categories of relationships, as exemplified by the
** following:
**   (1) A and B are unrelated.
**   (2) A is the k-ancestor of B.
**   (3) A is a k-descendant of B.
**   (4) A and B are k-th cousins r times removed.
**
** (1) applies when A and B have no common ancestor.
** (2) applies when A is an ancestor of B.  For example, if A is the
**     grandparent of B, then A is the 2-ancestor of B.  If A and B are
**     the same, A is the 0-ancestor of B.
** (3) applies when A is a proper descendant of B.  For example if B
**     is the great-grandparent of A, then A is a 3-descendant of B.
** (4) applies when A and B have a common ancestor, but neither one is an
**     ancestor of the other.  Let C be the nearest common ancestor of A 
**     and B and let dist(A,C) and dist(B,C) be the distances from A and B
**     to C, respectively.  (The distance from a node to its grandparent
**     is two, for example.)  Then A and B are k-th cousins r times removed,
**     where k = min(dist(A,C), dist(B,C)) - 1 and r = |dist(A,C) - dist(A,B)|.
*/

public class Cousins {

   public static void main(String[] args) throws FileNotFoundException { 

      String fileName = args[0];
      Scanner fileInput = new Scanner(new File(fileName));

      FamilyForest famFor = makeFamilyForest(fileInput);
      userInteraction(famFor);
   }

   /* Prompts the user to enter a pair of node ID's and responds
   ** by reporting the relationship between the nodes (with respect
   ** to the familyForest object).  Repeats this until the user enters -1
   ** for either of the two inputs.
   */
   private static void userInteraction(FamilyForest ff) {
      Scanner keyboard = new Scanner(System.in);
      boolean keepGoing = true;
      while (keepGoing) {
         System.out.print("\nEnter pair of node IDs: ");
         int id1 = keyboard.nextInt();
         int id2 = keyboard.nextInt();
         if (id1 == -1  || id2 == -1) {
            keepGoing = false;
         }
         else {
            try {
               reportRelationship(ff, id1, id2);
            }
            catch (Exception e) {
               e.printStackTrace(System.out);
            }
         }
      } 
      System.out.println("Goodbye.");
   }

   /* Prints a message indicating, with respect to the specified FamilyForest,
   ** the relationship between the two specified nodes (joe and carol).
   ** The message should be in one of the four forms exemplified here:
   **
   ** "Unrelated", "4-ancestor", "2-descendant", "2-cousins 1 times removed"
   */
   private static void reportRelationship(FamilyForest famForest, 
                                          int joe, int carol)        {
                                          
      if(famForest.nearestCommonAncestor(joe,carol) == -1)           {
         System.out.println("Unrelated, Duh!");                      }
      else if(famForest.isAncestorOf(joe,carol))                     {
         int dist = famForest.distance(carol,joe);
         System.out.println(dist+"-ancestor, Duh!");                 }
      else if(famForest.isAncestorOf(carol,joe))                     {
         int dist = famForest.distance(joe,carol);
         System.out.println(dist+"-descendant, Duh!");               }
      else                                                           {
         int C = famForest.nearestCommonAncestor(joe,carol); 
         int A = joe; int B = carol; 
         int AC = famForest.distance(A,C);
         int BC = famForest.distance(B,C);
         int k = Math.min(AC,BC)-1;
         int r = Math.abs(AC - BC);
         System.out.println(k+"-cousins "+r+" times removed, Duh!"); }
                                                                     }


   /* Returns a new FamilyForest object whose state is described by
   ** the data accessible to the given Scanner.  It is assumed that the
   ** first line of input data contains a positive integer N placing an
   ** upper bound on the number of distinct nodes involved in the forest.
   ** (Entities are identified by the natural numbers 0..N-1.)
   ** Following that, one per line, is a sequence of child/parent pairs
   ** of node ID's.  (That is, a pair (p,q) indicates that q is the 
   ** parent of p.)  Any text on a line past the pair is ignored.
   */
   private static FamilyForest makeFamilyForest(Scanner input) {
      final int N = input.nextInt();  // read upper bound on # nodes
      input.nextLine();

      FamilyForest ff = new FamilyForest(N);
      while (input.hasNext()) {
         int id = input.nextInt();       // read pair of node IDs and 
         int parentId = input.nextInt(); // make the 2nd the parent of 1st
         ff.setParent(id, parentId);
         input.nextLine();               // skip data on rest of line
      }
      return ff;
   }

}