/* ExprEvaluator.java
** An instance of this class evaluates arithmetic expressions.
**
** Author: R. McCloskey and ...
** Date: October 2019
** CMPS 144
*/
import java.util.*;

public class ArithExprEvaluator {


   /* Evaluates the given arithmetic expression and returns the result.
   ** pre: The expression is syntactically valid.
   */
   public int evaluate(String expr) {
      
      StackViaArray operatorStack = new StackViaArray();
      StackViaArray operandStack = new StackViaArray();
      char token; int tokenInt=0;
      
      while(expr.length() > 0)
      {
         token = expr.charAt(0);
         expr = expr.substring(1,expr.length());
         
         if(Character.isDigit(token)){
            int tokenINT = Integer.parseInt(String.valueOf(token));  
            while(Character.isDigit(expr.charAt(0))) //While loop used to combine characters corrospondign to the same number
            {
               tokenINT = 10*tokenINT + Integer.parseInt(String.valueOf(expr.charAt(0)));
               expr = expr.substring(1,expr.length());
            }
            System.out.println("Pushing to operand stack: "+tokenINT);
            IntLiteral lit = new IntLiteral(tokenINT);
            operandStack.push(lit);
         } else if(token == '('){
            System.out.println("Processing left paren");
            LeftParen lpar = new LeftParen();
            operatorStack.push(lpar);
         } else if(token == '+' || token == '-' || token == '*' || token == '/' || token == '^'){
            int pres = 0;
            if(token == '+'){AddOp op1 = new AddOp();             pres += op1.precedenceFactor();}//   operatorStack.push(op1);}
            if(token == '-'){SubtractOp op2 = new SubtractOp();   pres += op2.precedenceFactor();}//   operatorStack.push(op2);}
            if(token == '*'){MultiplyOp op3 = new MultiplyOp();   pres += op3.precedenceFactor();}//   operatorStack.push(op3);}
            if(token == '/'){DivideOp op4 = new DivideOp();       pres += op4.precedenceFactor();}//   operatorStack.push(op4);}
            if(token == '^'){PowerOp op5 = new PowerOp();         pres += op5.precedenceFactor();}//   operatorStack.push(op5);}
            
            
            System.out.println("Processing operator with presedence factor: " + pres);
            System.out.println("operator.topOf(): "+((Operator) operatorStack.topOf()).precedenceFactor());
            
            while(pres <= ((Operator) operatorStack.topOf()).precedenceFactor())
            {
               pres = ((Operator) operatorStack.topOf()).precedenceFactor();
               System.out.println("did it");
               IntLiteral literal2 = (IntLiteral) operandStack.topOf();
               operandStack.pop();
               
               IntLiteral literal1 = (IntLiteral) operandStack.topOf();
               operandStack.pop();
               
               int result = 0;
               
               if(operatorStack.topOf().toString().equals("+"))
                  {AddOp op1 = new AddOp();             result = op1.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("-"))
                  {SubtractOp op2 = new SubtractOp();   result = op2.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("*"))
                  {MultiplyOp op3 = new MultiplyOp();   result = op3.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("/"))
                  {DivideOp op4 = new DivideOp();       result = op4.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("^"))
                  {PowerOp op5 = new PowerOp();         result = op5.apply(literal1,literal2);}
                  
               operatorStack.pop();
               
               System.out.println("Pushing RESULT to operand stack: "+result);
               IntLiteral resultLit = new IntLiteral(result);
               operandStack.push(resultLit);
               
            }
            if(token == '+'){AddOp op1 = new AddOp();             pres += op1.precedenceFactor();   operatorStack.push(op1);}
            if(token == '-'){SubtractOp op2 = new SubtractOp();   pres += op2.precedenceFactor();   operatorStack.push(op2);}
            if(token == '*'){MultiplyOp op3 = new MultiplyOp();   pres += op3.precedenceFactor();   operatorStack.push(op3);}
            if(token == '/'){DivideOp op4 = new DivideOp();       pres += op4.precedenceFactor();   operatorStack.push(op4);}
            if(token == '^'){PowerOp op5 = new PowerOp();         pres += op5.precedenceFactor();   operatorStack.push(op5);}
            
            
         } else if(token == ')'){
            System.out.println("Processing right paren.");
            while(!operatorStack.topOf().toString().equals("("))
            {               
               IntLiteral literal2 = (IntLiteral) operandStack.topOf();
               operandStack.pop();
               
               IntLiteral literal1 = (IntLiteral) operandStack.topOf();
               operandStack.pop();
               
               int result = 0;
               
               if(operatorStack.topOf().toString().equals("+"))
                  {AddOp op1 = new AddOp();             result = op1.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("-"))
                  {SubtractOp op2 = new SubtractOp();   result = op2.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("*"))
                  {MultiplyOp op3 = new MultiplyOp();   result = op3.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("/"))
                  {DivideOp op4 = new DivideOp();       result = op4.apply(literal1,literal2);}
               if(operatorStack.topOf().toString().equals("^"))
                  {PowerOp op5 = new PowerOp();         result = op5.apply(literal1,literal2);}
                  
               operatorStack.pop();
               
               System.out.println("Pushing RESULT to operand stack: "+result);
               IntLiteral resultLit = new IntLiteral(result);
               operandStack.push(resultLit);
               
            }
           //if(operatorStack.topOf().equals("(")){operatorStack.pop();}
           operatorStack.pop();
         }
      }
      IntLiteral finalIntLiteral = (IntLiteral) operandStack.topOf();
      int finalVal = finalIntLiteral.valueOf();
      return finalVal;
   }
}

