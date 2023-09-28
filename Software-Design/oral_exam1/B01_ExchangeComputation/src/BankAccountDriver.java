
import java.util.Scanner;

/**
 * Main class that holds the driver and that will run the program
 */
public class BankAccountDriver {
    /**
     * float that holds the exchange rate between USD and SWD
     */
    public final static float exchangeRate = setExchangeRate();//setExchangeRate(); //uses method to set exchange rate to final

    /**
     * Main driver class that starts by asking to initialize the customers account balance and the exchange rate.
     * After initializing the exchange rate and account balance it goes through a continuous while loop asking the user what action they would like to preform
     * Depending on the users choice it will send them to a specific method in BankAccount.
     * The loop will exit and the program will end when the user decides to cash out.
     */
    public static void main (String[] args){
        Scanner input = new Scanner(System.in); //creates scanner to take input from user
        System.out.println("Initialize customer balance in USD: ");
        float balanceAmount = 0;
        while(input.hasNext()){ //while loop to get balance amount and only use it if its a float and not a negative number
            if(input.hasNextFloat()){
                balanceAmount = input.nextFloat();
                if (balanceAmount >= 0){
                    break;
                }
                else{
                    System.out.println("Balance has to be a positive");
                }
            }
            else{
                System.out.println("Balance has to be a number");
                input.next();
            }
        }

        BankAccount accountOne = new BankAccount(balanceAmount, exchangeRate); //creates object sends with parameters account balance and the exchange rate

        boolean exitLoop = true; //boolean used to keep going, will be set to false if user wants to exit
        while (exitLoop){
            boolean checker = true; //boolean to make the else if not print if the user input is the response to one of the categories

            System.out.println("To deposit SWD currency type one");
            System.out.println("To withdraw SWD currency type two: "); //categories for user
            System.out.println("To close account press three: ");

            String choice = input.next();
            if (choice.equals("one")) //checks if they want to deposit
            {
                checker = false; //makes it so else if knows next value is for the category
                System.out.println("Please enter amount in SWD you would like to deposit: ");
                if(input.hasNextFloat()) { //makes sure input is a number
                    float amount = input.nextFloat();
                    accountOne.depositMoney(amount); //calls bank account to deposit money
                }
                else{
                    System.out.println("You can only enter numbers for deposit amounts."); //tells user that there value was not a number
                    System.out.println();
                    input.next(); //makes input go to an empty value so it does mistake it as user trying to select a category
                }
            }

            if (choice.equals("two")) //checks if they want to withdraw
            {
                checker = false; //makes sure input knows next value is for category
                System.out.println("How much would you like to withdraw in SWD: ");
                if(input.hasNextFloat()) { //makes sure value user types is a number
                    float amount = input.nextFloat();
                    accountOne.withdrawMoney(amount); //uses bank account to withdraw money
                }
                else{
                    System.out.println("You can only enter numbers for withdraw amounts."); //tells user that there value was not a number
                    System.out.println();
                    input.next(); //makes input go to an empty value so it does mistake it as user trying to select a category
                }
            }

            if (choice.equals("three")) //checks if they want to exit
            {
                accountOne.usdCashOut(); //calls bank account to cash out the account
                exitLoop = false; //exits main while loop closing program
            }
            else if(checker) //sends response if they didnt enter a proper choice. Checker implemented so the scanner doesnt mistake input as a response to a category as an answer to what category needed.
            {
                System.out.println("That is not a valid choice, please type a valid selection");
                System.out.println();
            }
        }
    }

    /**
     * A private method that sets the final variable exchangeRate to the value the user decides.
     * @return The exchange rate the user wants.
     */
    private static float setExchangeRate(){ //private method to set the exchange rate
        Scanner rate = new Scanner(System.in);
        System.out.println("Enter the exchange rate:");
        boolean exitLoop = true;
        float exchangeRate = 0;
        while (exitLoop){ //checks to see if there is an input
            if(rate.hasNextFloat()){ //if the next input is a number
                exchangeRate = rate.nextFloat();
                if (exchangeRate > 0){
                    exitLoop = false; //makes sure the inputed exchange rate is above 0
                }
                else{
                    System.out.println("The exchange rate has to be grater than 0");
                }
                // keeps looping if its not a float or not above 0;
            }
            else{
                System.out.println("The exchange rate has to be a number");
                rate.next();
            }
        }
        return exchangeRate;
    }
}
