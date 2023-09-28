import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class Encryption extends RepeatedMethods
{
    private static int lettersInMessage = 0;
    private static String encryptedFile;
    private static String keyFile;
    private static String[] wordsInMessage;
    private static String[] key;
    private static String keyAsString;
    private static int startingPosition;

    /**
     * Constructor that creates a new file to write the encrypted message on or
     * Wipes a file that already exists to rewrite onto.
     * @param choice String that sees if they want to create new file or overwrite an existing one
     * @throws IOException
     */
    public Encryption(String choice) throws IOException {
        Scanner input = new Scanner(System.in);
        if(choice.equals("1")) //choice to create new file
        {
            while(true) {
                System.out.println("What would you like to name the file(exclude the .txt");
                String file = input.next(); //gets file name

                if (new File(file + ".txt").isFile()) //checks if file already exists
                {
                    System.out.println("That file already exists either restart the program or type a new name");
                }
                else {
                    encryptedFile = file + ".txt"; //creates a new file with desire name if it doesnt already exist
                    File keyFile = new File(file + ".txt");
                    keyFile.createNewFile();
                    break;
                }
            }
        }
        if(choice.equals("2"))  //choice to overwrite existing file
        {
            while (true) {
                System.out.println("What is the name of the file(include the .txt)");
                String file = input.next();
                if (new File(file).isFile()) { //makes sure file exists then wipes it
                    encryptedFile = file;
                    new FileOutputStream(file).close();
                    break;
                } else {
                    System.out.println("That file doesnt exists either restart the program or type a new name"); //tell user that the file doesnt exist
                }
            }
        }
    }

    /**
     * Method to get the message to encrypt from the user
     * Has user type in the message then uses a method from abstract class to check for invalid characters.
     */
    public void getMessage()
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter your message");
            String message = input.nextLine().toLowerCase();
            if(checkLetters(message.replaceAll(" ","")) == -1) //uses repeated method check letters to see if there is an ivalid character in users message
            {
                System.out.println("There was an invalid character in your message try again");
            }
            else
            {
                wordsInMessage = message.split(" "); //puts message into array
                lettersInMessage = message.replaceAll(" ","").length(); //gets the length of the message
                break;
            }
        }
    }

    /**
     * Method that will ask user for the key file and then get the starting position and the key from the file
     * @throws IOException
     */
    public void getKey() throws IOException {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter the file the key is on");
            keyFile = input.next();
            if(new File(keyFile).isFile()) //makes sure its a real file
            {
                try{
                    BufferedReader readKeyFile = new BufferedReader(new FileReader(keyFile)); //creates buffer reader to read through the file
                    String currentLine = readKeyFile.readLine(); //sets string current line to the first line of the page
                    startingPosition = Integer.parseInt(currentLine); //gets the previous starting position
                    keyAsString = readKeyFile.readLine(); //gets the key as a plain string
                    key = keyAsString.replace("[", "").replace("]",  "").split(","); //replaces characters in the key and splits it into and array
                    break;
                }
                catch (NumberFormatException numberFormatException) //for improperly formatted key
                {
                    System.out.println("This file does not have a properly formatted key");
                }

            }
            else //if the file doesnt exist
            {
                System.out.println("That file does not exist try again");
            }
        }

    }

    /**
     * Method that will take the key and its starting position, and the message and go letter by letter encrypting the message following the key
     * Once the message has been encrypted it writes out the encrypted message to a previously given file
     * It will also update the starting position in the key file so it can be used for decryption
     * @throws IOException
     */
    public void encryptFile() throws IOException {
        String message = "";
        int counter = startingPosition;
        if(lettersInMessage > key.length || startingPosition + lettersInMessage > key.length) //makes sure the message isnt longer than the key has available
        {
            System.out.println("The key is shorter than the message encryption is impossible");
        }
        else
        {
            for(String words:wordsInMessage) //every word in message
            {
                String letters = "";
                for(int i = 0; i < words.length(); i++) //every letter in word
                {
                    try {
                        int indexInAlphabet = getIndexOfLetter(words.charAt(i)) + Integer.parseInt(key[counter].replace(" ", "")); //gets its new index placement
                        while (indexInAlphabet > 25) //while index placement is greater than the size of the alphabet array it
                        {                             //subtracts 26 essentially looping the alphabet till it's a usable number
                            indexInAlphabet -= 26;
                        }
                        letters += alphabetArray[indexInAlphabet]; //gets the new encrypted letter
                        counter++; //move up in key array
                    }
                    catch (NumberFormatException numberFormatException) //if key isnt properly formatted
                    {
                        System.out.println("There key file is formatted incorrectly either fix it or create a new one");
                        System.exit(-1);
                    }
                }
                message += letters + " "; //adds encrypted word to sentence
            }
            startingPosition += lettersInMessage; //updates new starting positions
            new FileOutputStream(keyFile).close(); //wipes file
            FileWriter makeKey = new FileWriter(keyFile);
            makeKey.write(startingPosition + "\n"); //writes new starting position
            makeKey.write(keyAsString); //writes the key
            makeKey.close();
            new FileOutputStream(encryptedFile).close(); //wipes file
            FileWriter writeEncryption = new FileWriter(encryptedFile);
            writeEncryption.write(message);//writes onto file
            writeEncryption.close();
            System.out.println("Message has been encrypted");//tells user file has been encrypted
        }
    }

}
