/* An instance of this class represents a token that could not be
** recognized as being of any known kind.
*/

public class UnRecognizableToken implements Token {

   String image;

   public UnRecognizableToken(String s) { image = s; }

   public String toString() { return image; }
}