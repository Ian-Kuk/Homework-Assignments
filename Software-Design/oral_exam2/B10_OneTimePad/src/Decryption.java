import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Decryption extends RepeatedMethods
{

    private static int startingPosition;
    private static int messageLength;
    private static String[] wordsInMessage;
    private static String[] key;

    /**
     *
     * @param keyFile the file location of the key
     * Method that takes the keyFile gets the keys starting position and turns the files key into a usable array
     * @throws IOException
     */
    public Decryption(String keyFile) throws IOException {
        BufferedReader readKeyFile = new BufferedReader(new java.io.FileReader(keyFile)); //creates buffer reader to read through the file
        String currentLine = readKeyFile.readLine(); //sets string current line to the first line of the page
        try {
            startingPosition = Integer.parseInt(currentLine); //gets the inter value of the starting position
            key = readKeyFile.readLine().replace("[", "").replace("]", "").split(",");//gets rid of the pages formatting and turns key into array
        }
        catch (NumberFormatException numberFormatException) //if errors happen from the replacements they create a proper key
        {
            System.out.println("The key file does not follow proper formatting");
        }
    }

    /**
     *  Method that gets the file with the encrypted message
     *  It makes sure that the file is a file, and is properly formatted
     *  It reads in the encrypted message, and splits it into an array and gets the length of the message
     * @throws IOException
     */
    public void getMessage() throws IOException
    {
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter the name of the file with the encrypted message(include the .txt"); //has user give the file with the encrypted message
            String messageFile = input.next();

            if (new File(messageFile).isFile()) //makes sure file exists
            {
                BufferedReader readKeyFile = new BufferedReader(new FileReader(messageFile)); //creates buffer reader to read through the file
                try
                {
                    String currentLine = readKeyFile.readLine().toLowerCase(); //gets the first line of the file which is the message
                    if (checkLetters(currentLine.replaceAll(" ","")) == -1) //will only produce -1 if the key isn't formatted properly
                    {
                        System.out.println("Your encrypted message contained an illegal character, fix your message before retrying program");
                        System.exit(0);
                    }
                    else
                    {
                        wordsInMessage = currentLine.split(" "); //if its formatted properly it turns the message into an array
                        messageLength = currentLine.replaceAll(" ","").length(); //gets length of message
                        break;
                    }
                }
                catch (NullPointerException nullPointerException) //error with the file
                {
                    System.out.println("The file you entered is empty decryption is impossible");
                }
            }
            else //file doesnt exist
            {
                System.out.println("That file does not exist try again");
            }

        }
    }

    /**
     * Method that takes the key and using the message length and the keys ending position starts the decryption process.
     * It will go through every letter of every word in the message and gets its key counterpart and decrypts it.
     * @return decryptedMessage the now decrypted message
     */
    public String decryptMessage()
    {
        int counter = startingPosition - messageLength; //counter for placement in key array
        String decryptedMessage = "";
        for (String words : wordsInMessage) { // loops to get every word in message
            String decryptedLetters = "";
            for (int j = 0; j < words.length() ; j++) { //loop for the length of each word in message
                try {
                    int indexInAlphabet = getIndexOfLetter(words.charAt(j )) - Integer.parseInt(key[counter].replace(" ","")); //local variable to hold the value of the index of the changed letter minus how much it was changed by
                    while (indexInAlphabet < 0) //checks to see if index is lower than the array and will loop through until it has usable number
                    {
                        indexInAlphabet += 26;
                    }
                    decryptedLetters = decryptedLetters + alphabetArray[indexInAlphabet]; //adds the letters from each letter of each word together
                    counter += 1; // moves up in key
                }
                catch (NumberFormatException numberFormatException) //if the key is improper exits program
                {
                    System.out.println("The key file is formatted incorrectly either fix it or create a new one");
                    System.exit(-1);
                }

            }
            decryptedMessage += decryptedLetters + " ";  //adds decrypted words into a sentence
        }
        return decryptedMessage; //gives back whole message
    }
}
