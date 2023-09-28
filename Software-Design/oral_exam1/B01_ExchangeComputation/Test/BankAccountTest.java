
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void returnBalance()
    {
        BankAccount returnBalanceTest = new BankAccount(103,100); //initializes account to $103 and exchange rate to 100%
        BankAccount returnBalanceTestTwo = new BankAccount(103,100);
        assertEquals(103,returnBalanceTest.getBalance()); //makes sure balance it what it was initialized to
        assertEquals(1,BankAccount.exchangeRate);//makes sure exchange rate is what it was initialized to

        returnBalanceTest.depositMoney(1053); //increases amount in account
        assertEquals(1156,returnBalanceTest.getBalance()); //makes sure account is what it should be
        assertEquals(103,returnBalanceTestTwo.getBalance()); //makes sure it can return separate objects balances

        returnBalanceTest.withdrawMoney(999); //decreases amount
        assertEquals(157,returnBalanceTest.getBalance());//makes sure account is what it should be

    }
    @Test
    void depositMoney()
    {
        BankAccount depositMoneyTest = new BankAccount(100,100);//initializes account to $100 and exchange rate to 100%

        depositMoneyTest.depositMoney(-10); //tries to deposit negative amount
        assertEquals("That was not a valid number, deposit an amount above 0:", depositMoneyTest.errorString); //response should be a string telling user they cant

        depositMoneyTest.depositMoney(.00000000000000000000035f); //deposits really low fraction
        assertEquals(100f, depositMoneyTest.getBalance()); //shows that such a low number wouldn't be considered a value and amount wont change

        depositMoneyTest.depositMoney(.009f); //deposits amount that should round up
        assertEquals(100.01f, depositMoneyTest.getBalance()); //shows that it did round up
        depositMoneyTest.withdrawMoney(.01f); //withdraws amount to continue tests

        depositMoneyTest.depositMoney(.994f); //deposits amount that should round down
        assertEquals(100.99f, depositMoneyTest.getBalance()); //shows it did round dow to .99
        depositMoneyTest.withdrawMoney(.99f); //withdraws amount to continue tests

        depositMoneyTest.depositMoney(.999f);//deposits amount that should round to 1
        assertEquals(101f, depositMoneyTest.getBalance());//shows it did round to 1

    }

    @Test
    void withdrawMoney()
    {
        BankAccount withdrawMoneyTest = new BankAccount(1000,100);//initializes account to $1000 and exchange rate to 100%

        withdrawMoneyTest.withdrawMoney(-10); //tries to withdraw negative amount
        assertEquals("You can only withdraw amounts above 0 please retype:", withdrawMoneyTest.errorString); //shows it gives string telling user they cant do that

        withdrawMoneyTest.withdrawMoney(20f); //withdraws valid amount
        assertEquals(980, withdrawMoneyTest.getBalance()); //changes to proper amount

        withdrawMoneyTest.withdrawMoney(990f); //tries to withdraw more than is in account
        assertEquals("Deposit more money into your account, or withdraw a lesser amount", withdrawMoneyTest.errorString); //gives string telling user they do not have enough money

        withdrawMoneyTest.withdrawMoney(.994f); //withdraws amount that should round to .99
        assertEquals(979.01f, withdrawMoneyTest.getBalance()); //withdrew the proper amount
        withdrawMoneyTest.depositMoney(.99f); //deposits money back in to continue tests

        withdrawMoneyTest.withdrawMoney(.999f); //withdraws amount that should round to 1
        assertEquals(979f, withdrawMoneyTest.getBalance()); //withdrew proper amount

        withdrawMoneyTest.withdrawMoney(.0000000000000001f); //withdraws amount that is to low to count
        assertEquals(979f, withdrawMoneyTest.getBalance()); //balance stays the same
    }

    @Test
    void usdCashOut()
    {
        BankAccount usdCashOutTest = new BankAccount(100f,100); //initializes account to $100 and exchange rate to 100%
        usdCashOutTest.usdCashOut(); //uses cash out emptying account
        assertEquals(0,usdCashOutTest.getBalance()); //makes sure account is empty
    }
    @Test
    void getExchangeRate()
    {
        BankAccount getExchangeRateTest = new BankAccount(100f,100); //creates instance with exchange rate equal to 100
        assertEquals(100,getExchangeRateTest.getExchangeRate()); //check its equal to 100
        BankAccount getExchangeRateTestTwo = new BankAccount(100f,50); //creates instance with exchange rate equal to 50
        assertEquals(50,getExchangeRateTestTwo.getExchangeRate()); //check if its equal to 50
    }
    @Test
    void setBalance()
    {
        BankAccount setBalanceTest = new BankAccount(100f,100); //makes balance 100
        setBalanceTest.setBalance(50); //makes balance 50
        assertEquals(50,setBalanceTest.getBalance()); //checks balance is 50
        setBalanceTest.setBalance(-100); //makes balance -100
        assertEquals(50,setBalanceTest.getBalance()); //checks balance didnt change and should still be 50
    }

}
