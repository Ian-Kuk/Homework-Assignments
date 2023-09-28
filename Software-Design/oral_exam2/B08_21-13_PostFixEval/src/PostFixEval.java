import java.util.Stack;

/**
 * Main program that will run a post fix expression calculator. It will use a stack to add the numbers and take them out when an operator it encountered.
 * It will calculate the expression and then add its result to the stack until it ends.
 */
public class PostFixEval {
    private final Stack<Long> stack = new Stack<Long>();

    /**
     * Constructor that will run a loop solving the equation
     * It will take in the string of the equation and go through it adding the numbers into the stack until an operand is encountered
     * When the operand is encountered it will take the numbers off the stack and send them to a method to calculate its value
     * once the value has been calculated it will be added to the stack, and it will continue until the equation is solved
     * @param equation string from user that is the post fix expression
     */
    public PostFixEval(StringBuilder equation){
        long x,y;
        String number ="";
        for(int i = 0; equation.charAt(i) != ')'; i++) { //for loop that goes until the end parenthesis of the equation is encountered
            boolean digit = Character.isDigit(equation.charAt(i)); //checks to see if character is a digit
            if(digit) {
                number += equation.charAt(i); //adds the character to a string if the next character isn't a space the next number will be added
                //this is done to get more than one digit numbers

                if(equation.charAt(i+1) == ' ') //if the next character is a space then its the last number
                {
                    if(number.length() == 1)
                    { //if its only one character find its value using the unicode method
                        stack.push((long)equation.charAt(i) - '0'); //puts it to stack
                    }
                    else { //if its more than one character parse to find number
                        stack.push(Long.parseLong(number)); //puts it to stack
                    }
                    number = ""; //resets the string
                }

            }
            else if(equation.charAt(i) != ' ') { //if the character isn't a digit or space calculate
                x = stack.pop(); //gets numbers off stack
                y = stack.pop();
                stack.push(calculate(y,x,equation.charAt(i))); //sends the result of operation to stack
                number =""; //resets the string
            }
        }
    }

    /**
     * method that does basic mathematical operations to 2 numbers depending on the operand given,
     * @param y a long that was second in the stack and is the first number of the equation
     * @param x a long that was first in the stack and is the second number of the equation
     * @param operator a character that is the identifying operand for what equation to preform
     * @return the result of the operation or 0 if the operation could not be done
     */
    private static long calculate(long y, long x, char operator){
        if(operator == '+') //addition
        {
            return y + x;

        }
        if(operator == '-') //subtraction
        {
            return y -x;
        }
        if(operator == '*') //multiplication
        {
            return y * x;

        }
        if(operator == '/') //division
        {
            return y/x;
        }
        if(operator == '^')//exponential
        {
            return (int) Math.pow(y,x);

        }
        if(operator == '%')//modulus
        {
            return y % x;
        }
        else return 0;
    }

    /**
     * method to give final value of the stack
     * @return the private stacks highest number
     */
    public long returnFinalValue(){
        return stack.peek();
    }
}

