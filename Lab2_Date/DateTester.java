// DateTester.java    
// A modification of SafeDateTester, its purpose is to test the 2nd
// constructor of the Date class.
//
// This application prompts the user to enter a calendar date, as described
// by its Lilian Day Number.  It passes that number to the 2nd constructor
// of the Date class to create a Date object.  Then the lilian() method is
// invoked upon that object.  If the result is not equal to the number
// entered by the user, the 2nd constructor must not have worked correctly.
//----------------------------------------------------------------------


import java.util.Scanner;

public class DateTester {

  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a Lilian Day Number: ");
    int lilianNum = input.nextInt();

    Date date = new Date(lilianNum);
    System.out.println("Date is " + date);

    int actualLilianNum = date.lilian();
    if (actualLilianNum != lilianNum) {
       System.out.println("ERROR: That date has Lilian Day Number " +
                          actualLilianNum);
    }
    else {
       System.out.println("This is correct!");
    }
  }
}