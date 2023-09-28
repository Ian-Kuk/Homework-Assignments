public class CalculatorException extends Exception {
    /**
     Create a private instance variable:
        name: mesg
        type: String
     */
    private String mesg;
    /**
     Create a Constructor and a Default Constructor
     for an input of a String with the default value
     being an empty String.

     */
    CalculatorException(){
        mesg = "";

    }
    CalculatorException(String symbol){
        this.mesg = symbol;
    }

    /**
        Example:
            mesg: "Some Message"
            output: CalculatorException: Some Message
    */
    public String toString() {
        return ("CalculatorException: " + mesg);
    }
}
