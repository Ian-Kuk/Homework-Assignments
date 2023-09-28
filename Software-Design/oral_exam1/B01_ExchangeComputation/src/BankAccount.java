/**
 * The Class BankAccount preforms basic mathematical computation to change the balance in a users account.
 * Depending on the users choice to withdraw, deposit, or cash out the class with add to their account, take away from their account tell the users the bills they will be receiving for their withdrawal, or fully empty their account and tell the user the bills that they will recieve.
 * The class constructor takes in the amount of the account and the exchange rate. The exchange rate will be divided by 100 to turn the percentage into decimal format.
 */
public class BankAccount {
    /**
     * float used for hold the current accounts balance
     */
    private float accountBalance;
    /**
     * String used to hold the error a method may produce. Primarily used to check methods during junit testong
     */
    public String errorString; //class variable error string made to make unit testing easier
    /**
     * float used for the exchange rate between currency's
     */
    public static float exchangeRate;
    public void setBalance(float balance)
    {
        if (balance >= 0) {
            this.accountBalance = Math.round(balance * 100.0f) / 100.0f; //sets the balance of the object and uses Math.round to round it to 2 decimal places
        }
    }
    public float getBalance(){ //returns objects account balance
        return this.accountBalance;
    }
    public double getExchangeRate(){ //returns exchange rate
        return exchangeRate * 100f;
    }

    /**
     * Class constructor that uses sets to set the class variables accountBalance, and accountBalance
     * @param accountBalance a float for the balance of the account
     * @param exchangeRateGiven a float for the exchange rate to switch between currencies
     */
    public BankAccount(float accountBalance, float exchangeRateGiven) //constructor for the class
    {
        if(accountBalance < 0){
            throw new IllegalArgumentException("User can only have a positive balance");
        }
        if(exchangeRateGiven < 0){
            throw new IllegalArgumentException("Exchange rate has to be above 0");
        }
        exchangeRate = exchangeRateGiven/100f; //turns the exchange rate percentage into a decimal for calculations
        if (accountBalance >= 0) //makes sure the user didn't enter a negative number.
        {
            setBalance(accountBalance) ; //uses set balance to set the objects balance amount
            System.out.println("Account balanced initialised to $" + getBalance() + " USD");
            System.out.println();
        }
        else //tells user they typed a negative number and the account was initialized to 0
        {
            this.accountBalance = 0f;
            System.out.println("You cannot enter negative amounts, the account was initialised to $0 USD");
            System.out.println();
        }
    }

    /**
     * Method that converts depositAmount to USD from SWD and adds that amount to the accounts full balance
     * @param depositAmount user entered float that determines how much money in SWD the user wants to deposit
     */
    public void depositMoney(float depositAmount) //method to deposit money
    {
            if (depositAmount > 0) //makes sure user is depositing money, it cant be negative or 0
            {
                depositAmount = ((depositAmount * exchangeRate ) + getBalance()); //user is depositing in SWD so it converts it to usd and adds the amount that was already in the account
                setBalance(depositAmount); //sets new value to the account equal to what they had plus the amount they deposited
                System.out.println("You now have $" + getBalance() + " USD in your account"); //tells them how much is in there account
                System.out.println();
            }
            else //tells user they were trying to deposit an amount that isn't possible
            {
                errorString = "That was not a valid number, deposit an amount above 0:"; //sets errorString to this string so it can be junit tested
                System.out.println(errorString);
                System.out.println();
            }
    }

    /**
     * method that takes a user entered float and converts in to usd and subtracts that amount from the main balance
     * @param withdrawAmount user entered float that decides how much the user wants to withdraw in SWD
     */
    public void withdrawMoney(float withdrawAmount) //method to withdraw money
    {
            if (withdrawAmount > 0)  //makes sure they are withdrawing a valid amount
            {
                if ( withdrawAmount <= getBalance() / exchangeRate) //takes there balance in usd and converts it so SWD and checks if they have enough in their account to withdraw the amount hey want
                {
                    setBalance((getBalance() - (withdrawAmount * exchangeRate))); //sets balance to the new amount after withdrawal
                    System.out.println("The current exchange rate is " + exchangeRate + "%"); //gives exchange rate, their current balance, and what bills the withdrawal will be paid in
                    System.out.println("Your current balance in USD is $" + getBalance());
                    System.out.println("Your withdraw will be paid out in");
                    swdCashOut(Math.round(withdrawAmount * 100.0f) / 100.0f ); //rounds the withdrawal amount incase it has more than 2 decimal places, sends it to swdCahOut to figure out what bills to give
                    System.out.println();
                }
                else
                {
                    System.out.println("Your account currently sits at $" + Math.round((getBalance() / exchangeRate)*100.0f)/100.0f + " SWD you do not have enough funds to withdraw $" + withdrawAmount);
                    errorString ="Deposit more money into your account, or withdraw a lesser amount";
                    System.out.println(errorString);
                    System.out.println();
                }
            }
            else {
                errorString = "You can only withdraw amounts above 0 please retype:";
                System.out.println(errorString);
                System.out.println();
            }
    }

    /**
     * Method that figures out how much of each currency type in SWD to give to the user that equals the amount they withdrew
     * Prints out the type of currency and the amount of each type that equal the amount withdrawn
     * @param withdrawAmount Gives method the amount of money being withdrawn
     */
    public static void swdCashOut(float withdrawAmount){ //method to figure out what bills to give
        float[] swdCurrency = new float[]{25f,10f,5f,1f,.2f,.08f,.05f,.01f}; //float array of the possible change amounts
        String[] currencyName = new String[]{" 25 dollar bills"," 10 dollar bills", " 5 dollar bills", " 1 dollar bills", " 20 cent coins", " 8 cent coins", " 5 cent coins", " 1 cent coins"}; //name of all the currency's
        int[] currencyAmounts = new int[8]; //array the holds the amount of each currency that will be distributed
        for(int i = 0; i < 8; i++) //figures out how much of each currency to use
        {
            currencyAmounts[i] =(int) Math.floor(withdrawAmount / swdCurrency[i]); //sees how many times a currency can evenly go into the withdrawal amount

            withdrawAmount = (withdrawAmount) % swdCurrency[i]; //changes withdraw amount to what is left after using a certain currency
        }
        for(int j = 0; j<8; j++){ //prints out the amount of each currency and the name of the currency
            if (currencyAmounts[j] != 0) //if the amount of currency you are giving isn't 0 print the amount and the name of the currency
            {
                System.out.println(currencyAmounts[j] + currencyName[j]);
            }
        }
    }

    /**
     * Method to print out the remaining money in the bank account. It finds the amount of each currency that make up the amount of money left in the account.
     * It prints the name of the currency and the amount of each type of currency to return.
     */
    public void usdCashOut(){ //method to figure out what bills to give in USD after the account closes
        float[] usdCurrency = new float[]{20f,10f,5f,1f,.25f,.1f,.05f,.01f}; //float array of possible currency amounts
        String[] currencyName = new String[]{" 20 dollar bills"," 10 dollar bills", " 5 dollar bills", " 1 dollar bills", " Quarters", " Dimes", " Nickles", " Pennies"}; //name of currencys
        int[] currencyAmounts = new int[8];//array that  holds the amount of each currency to return

        for(int i = 0; i < 8; i++)
        {
            currencyAmounts[i] =(int) Math.floor(getBalance() / usdCurrency[i]); //finds how many times a currency can evenly go into the amount left in the account
            setBalance(getBalance() % usdCurrency[i]); //changes the balance to the amount left after using a certain currency, till eventually 0
        }
        for(int j = 0; j<8; j++){
            if (currencyAmounts[j] != 0) //if the currency amount isnt 0 print its amount and the name of the currency
            {
                System.out.println(currencyAmounts[j] + currencyName[j]);
            }
        }
    }


}
