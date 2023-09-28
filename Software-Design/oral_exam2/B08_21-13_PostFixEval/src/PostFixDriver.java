import java.util.Scanner;

/**
 * Main program that will run PostFixEval that calculates post fix expression equations
 */
public class PostFixDriver {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your equation");
        String equation = input.nextLine(); //gets equation as string
        StringBuilder buffer = new StringBuilder(equation); //turns string into buffer
        buffer.append(")"); //adds on the parenthesis to the end
        PostFixEval postFix = new PostFixEval(buffer); //creates new instance of postFixEval that will solve the equation and put the answer in the stack
        System.out.println(postFix.returnFinalValue()); //prints the highest value of the stack
    }
}

