import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.NumberFormat;

public class ExchangeCompMain{
    private static Float balance;
    private static Float exchangeRate;
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    @FXML
    private Button submitButton;
    @FXML
    private Button initialButton;
    @FXML
    private ToggleGroup buttonGroup;
    @FXML
    private ToggleButton withdrawButton;
    @FXML
    private ToggleButton depositButton;
    @FXML
    private ToggleButton closeButton;
    @FXML
    private TextField accountChange;
    @FXML
    private TextField accountBalance;
    @FXML
    private TextField accountBalanceSWD;
    @FXML
    private Label balanceLabelSWD;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private TextArea cashOut;
    @FXML
    private TextArea exchangeRateArea;


    /**
     * Method to initialize the GUI. It sets everything invisible and disable except for 2 labels and text areas. It changes the GUI so the user has to initialize the exchange rate and the account balance
     */
    public void initialize()
    {
        //sets everything invisible and disable but two text areas and labels. It changes the text of the two text labels
        //Basically looks like a different page to collect accounts initial amount and exchange rate
        moneyLabel.setText("Exchange Rate");
        balanceLabel.setText("Initial Amount(USD)");
        exchangeRateArea.setVisible(false);
        cashOut.setVisible(false);
        submitButton.setVisible(false);
        submitButton.setDisable(true);
        withdrawButton.setVisible(false);
        withdrawButton.setDisable(true);
        depositButton.setVisible(false);
        depositButton.setDisable(true);
        closeButton.setVisible(false);
        closeButton.setDisable(true);
        balanceLabelSWD.setVisible(false);
        accountBalanceSWD.setVisible(false);
        accountBalanceSWD.setDisable(true);
    }

    /**
     * Method that once the first button is pressed on the screen with the limited features it either shows everything and changes the label, or informs the user that the information is invalid and to type valid information.
     * @param actionEvent the event of the button being pressed
     */
    public void initialButtonPress(javafx.event.ActionEvent actionEvent) {
        try
        {
            exchangeRate = Float.parseFloat(accountChange.getText()); //gets the exchange rates
            balance = Float.parseFloat(accountBalance.getText()); //gets account balance
            if(balance >= 0 && exchangeRate > 0)  //if the information is valid show the full screen with all the features
            {
                setExchangeRate(exchangeRate);
                setBalance(balance);

                moneyLabel.setText("Amount to Deposit/Withdraw(SWD)");
                balanceLabel.setText("Account Balance(USD)");
                exchangeRateArea.setVisible(true);
                cashOut.setVisible(true);
                submitButton.setVisible(true);
                submitButton.setDisable(false);
                withdrawButton.setVisible(true);
                withdrawButton.setDisable(false);
                depositButton.setVisible(true);
                depositButton.setDisable(false);
                closeButton.setVisible(true);
                closeButton.setDisable(false);
                initialButton.setVisible(false);
                initialButton.setDisable(true);
                accountBalanceSWD.setVisible(true);
                balanceLabelSWD.setVisible(true);
                accountBalance.setDisable(true);
                exchangeRateArea.setText("The current exchange rate is: " + percent.format(exchangeRate)); //formats percent
            }
            else //tells user the entered information isnt valid
            {
                cashOut.setVisible(true);
                cashOut.setText("The exchange has to be above 0\n The account balance has to be 0 or greater");
            }
        }
        catch (NumberFormatException numberFormatException) //if the floats cant parse properly the user typed invalid information
        {
            accountChange.setText("Enter exchange rate");
            accountBalance.setText("Enter account balance");
            accountChange.requestFocus();
            cashOut.setVisible(true);
            cashOut.setText("The exchange has to be above 0\n The account balance has to be 0 or greater");
        }

    }

    /**
     * Method that is triggered if the button on the page with all the features is pressed. Depending on which toggle button is selected it will call a method to deposit, withdraw, or close the account.
     * @param actionEvent the even of the button being pressed
     */
    public void submitButtonPress(ActionEvent actionEvent) {

        if(depositButton.isSelected()) //if deposit toggle button is selected
        {
            try
            {
                depositMoney(Float.parseFloat(accountChange.getText())); //calls method to deposit money
            }
            catch (NumberFormatException numberFormatException) //tells user they typed an invalid amount
            {
                accountChange.setText("Enter amount");
                accountChange.requestFocus();
                cashOut.setVisible(true);
                cashOut.setText("The exchange has to be above 0\n The account balance has to be 0 or greater");
            }

        }
        if(withdrawButton.isSelected()) //if withdraw toggle button is selected
        {
            try
            {
                withdrawMoney(Float.parseFloat(accountChange.getText())); //calls method to withdraw money
            }
            catch (NumberFormatException numberFormatException) //informs user if they typed an invalid amount
            {
                accountChange.setText("Enter amount");
                accountChange.requestFocus();
            }

        }
        if(closeButton.isSelected()) //if cash out button is selected
        {
            usdCashOut(); //calls method to cash out

        }
    }

    /**
     * Method that sets the balance of the account, but will also update the textFields displaying the accounts balance in USD and SWD
     * @param balance
     */
    public void setBalance(float balance)
    {
        if (balance >= 0) {
            this.balance = Math.round(balance * 100.0f) / 100.0f; //sets the balance of the object and uses Math.round to round it to 2 decimal places
            accountBalanceSWD.setText("$" + Math.round((this.balance / this.exchangeRate)*100.0f) / 100.0f); //displays balance SWD
            accountBalance.setText("$" + this.balance); //displays balance usd
            accountChange.setText("");
        }

    }
    public float getBalance(){ //returns objects account balance
        return this.balance;
    }
    public double getExchangeRate(){ //returns exchange rate
        return exchangeRate * 100f;
    }
    public void setExchangeRate(float exchange)
    {
        this.exchangeRate = exchange / 100.0f;
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
            cashOut.clear();
        }
        else //tells user they were trying to deposit an amount that isn't possible
        {
            cashOut.clear(); //clears text area
            cashOut.setText("You tried to deposit money below 0");
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
                swdCashOut(Math.round(withdrawAmount * 100.0f) / 100.0f ); //rounds the withdrawal amount incase it has more than 2 decimal places, sends it to swdCahOut to figure out what bills to give
            }
            else
            {
                cashOut.clear();
                cashOut.setText("Your account currently sits at $" + Math.round((getBalance() / exchangeRate)*100.0f)/100.0f + " SWD you do not have enough funds to withdraw $" + withdrawAmount);
            }
        }
        else {
            cashOut.clear();
            cashOut.setText("You can only withdraw amounts above 0 please retype:");
        }
    }
    /**
     * Method that figures out how much of each currency type in SWD to give to the user that equals the amount they withdrew
     * Prints out the type of currency and the amount of each type that equal the amount withdrawn
     * @param withdrawAmount Gives method the amount of money being withdrawn
     */
    public void swdCashOut(float withdrawAmount){ //method to figure out what bills to give
        float[] swdCurrency = new float[]{25f,10f,5f,1f,.2f,.08f,.05f,.01f}; //float array of the possible change amounts
        String[] currencyName = new String[]{" 25 dollar bills"," 10 dollar bills", " 5 dollar bills", " 1 dollar bills", " 20 cent coins", " 8 cent coins", " 5 cent coins", " 1 cent coins"}; //name of all the currency's
        int[] currencyAmounts = new int[8]; //array the holds the amount of each currency that will be distributed
        String temp = "";
        for(int i = 0; i < 8; i++) //figures out how much of each currency to use
        {
            currencyAmounts[i] =(int) Math.floor(withdrawAmount / swdCurrency[i]); //sees how many times a currency can evenly go into the withdrawal amount

            withdrawAmount = (withdrawAmount) % swdCurrency[i]; //changes withdraw amount to what is left after using a certain currency
        }
        for(int j = 0; j<8; j++){ //prints out the amount of each currency and the name of the currency
            if (currencyAmounts[j] != 0) //if the amount of currency you are giving isn't 0 print the amount and the name of the currency
            {
                temp += (currencyAmounts[j] + currencyName[j] + "\n");
            }
        }
        cashOut.clear();
        cashOut.setText(temp); //sets text area to the string of the currency given
    }

    /**
     * Method to print out the remaining money in the bank account. It finds the amount of each currency that make up the amount of money left in the account.
     * It prints the name of the currency and the amount of each type of currency to return.
     */
    public void usdCashOut(){ //method to figure out what bills to give in USD after the account closes
        float[] usdCurrency = new float[]{20f,10f,5f,1f,.25f,.1f,.05f,.01f}; //float array of possible currency amounts
        String[] currencyName = new String[]{" 20 dollar bills"," 10 dollar bills", " 5 dollar bills", " 1 dollar bills", " Quarters", " Dimes", " Nickles", " Pennies"}; //name of currencys
        int[] currencyAmounts = new int[8];//array that  holds the amount of each currency to return
        String temp = "";
        for(int i = 0; i < 8; i++)
        {
            currencyAmounts[i] =(int) Math.floor(getBalance() / usdCurrency[i]); //finds how many times a currency can evenly go into the amount left in the account
            setBalance(getBalance() % usdCurrency[i]); //changes the balance to the amount left after using a certain currency, till eventually 0
        }
        for(int j = 0; j<8; j++){
            if (currencyAmounts[j] != 0) //if the currency amount isnt 0 print its amount and the name of the currency
            {
                temp += currencyAmounts[j] + currencyName[j] + "\n";
            }
        }
        cashOut.clear();
        cashOut.setText(temp); //sets text area to the string of the currency given
    }


}



