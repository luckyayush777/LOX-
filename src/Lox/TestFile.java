package Lox;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;
import java.util.Queue;

public class TestFile {

    private void processTestString()
{
    String testString = "1 + 2 * 4 - 3";
    String[] tokens = testString.split(" ");
     StringBuilder postFixResult = new StringBuilder();
     Stack<String> operatorStack = new Stack<>();
     for(String token : tokens)
     {
         if(isOperand(token))
         {
             postFixResult.append(token);
         }

         else if(isOperator(token))
         {
             while(!operatorStack.isEmpty() && precedence(operatorStack.peek()) >=
                     precedence(token))
             {
                 postFixResult.append(operatorStack.pop()).append(" ");
             }
             operatorStack.push(token);
         }

         else if(token.equals("("))
             operatorStack.push("(");
         else if(token.equals(")"))
         {
             while(!Objects.equals(operatorStack.peek(), "(")){
                 postFixResult.append(operatorStack.pop()).append(" ");}
             operatorStack.pop();
         }
     }

     while(!operatorStack.isEmpty())
     {
         postFixResult.append(operatorStack.pop()).append(" ");
     }
    System.out.println("PostFixExpression : " + postFixResult.toString().trim());


}



private int precedence(String operator)
{
    return switch (operator) {
        case "+", "-" -> 1;
        case "/", "*" -> 2;
        default -> 0;
    };
}

private boolean isOperand(String token)
{
    return token.matches("\\d+");
}

private boolean isOperator(String token)
{
    return token.equals("+") || token.equals("-") ||
           token.equals("/") || token.equals("*");
}



public static void main(String[] args)
{
    new TestFile().processTestString();
}

}
