import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Decryption extends RepeatedMethods implements EncryptionAndDecryption{
    private static String[] encryptedMessage;
    private static String wordsInMessage = "";
    private final int startingPosition;
    public static final ArrayList<Integer>letterPlacement = new ArrayList<>();
    public Decryption()throws FileNotFoundException {
        String encryptedFilePath = checkFilePath(); //uses test to get the file path and check thats its valid
        Scanner readFile = new Scanner(new File(encryptedFilePath)); //new scanner that reads the file
        startingPosition = (Integer.parseInt(readFile.nextLine())); //gets the starting point of the encryption process from the first line
        encryptedMessage = readFile.nextLine().toLowerCase().split(" "); //sets the words in message to all
    }
    public String returnWordsInMessage() {
        return wordsInMessage;
    }
    public void getLetterPlacement()  //embedded for loops to go through each word in message and each letter in each word
    {
        for (String words : encryptedMessage)
        {
            for (int j = 0; j < words.length(); j++)
            {
                int placement = getIndexOfLetter(words.charAt(j)); //uses test interface to get the letters index in the alphabet array
                if (placement == -1)
                {
                    System.out.println("Encrypted message contained illegal characters, fix the message, or re-encrypted original message."); //checks to make sure that encrypted message is only letters
                    System.exit(-1);
                }
                else
                {
                    letterPlacement.add(placement); //adds the index of the letters to an array list
                }
            }
        }
    }
    @Override
    public void getKeyFileInfo() throws IOException //method to get all cipher numbers from file and make an array with only ones that are needed
    {
        String keyFilePath = checkFilePath(); //uses test interface to get key file path and check it exists
        ArrayList<Integer> allKeyNumbers = FileHandler.readCipherNumbers(keyFilePath); //gets all key numbers by calling method in fileReader
        allKeyNumbers.remove(allKeyNumbers.size()-1); //if wanted can create a variable for the ending position and assign it to last index of allKeyNumbers, but was not needed for my implementation.
        int[] cipherKey = FileHandler.getNeededCipherKey(startingPosition,allKeyNumbers,letterPlacement); //uses method in fileReader to get only the cipher keys needed
        decryptMessage(cipherKey); //calls method to decrypt the message

    }
    public void decryptMessage(int[] cipherKey)
    {
        int counter = 0; //counter outside for loop made to increase after every letter, used as an index for the .get() of the letter placement array
        for (String words : encryptedMessage)
        {
            String decryptedLetters = "";
            for (int j = 0; j < words.length(); j++)
            {
                int indexInAlphabet = letterPlacement.get(counter) - cipherKey[counter]; //local variable to hold the value of the index of the changed letter minus how much it was changed by
                while ( indexInAlphabet < 0) //checks to see if index is lower than the array and will loop through until it has usable number
                {
                    indexInAlphabet += 26;
                }
                decryptedLetters += alphabetArray[indexInAlphabet]; //adds the letters from each letter of each word together
                counter += 1;
            }
            wordsInMessage += (decryptedLetters + " "); //adds the words together
        }
    }
}
