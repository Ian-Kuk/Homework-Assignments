import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Encryption extends RepeatedMethods implements EncryptionAndDecryption{
    private String[] wordsInMessage;
    private static final ArrayList<Integer> letterPlacement = new ArrayList<>();
    private String encryptedMessage;
    private static int startingPosition;
    public void setWordsInMessage(String[] message) {this.wordsInMessage = message;} //instance variables that hold the original message to the object it was written on
    public String[] returnWordsInMessage(){
        return this.wordsInMessage;
    }
    public void setEncryptedMessage(String encMessage){this.encryptedMessage = encMessage;}//instance variables that hold the encrypted message to the object it was written on
    public String returnEncryptedMessage(){
        return this.encryptedMessage;
    }


    public Encryption() {
        Scanner userMessage = new Scanner(System.in);
        String message = userMessage.nextLine().toLowerCase(); //creates new scanner takes in the entire line from the scanner that contains message
        setWordsInMessage(message.split(" ")); //splits the message by spaces and assigns each word to a index of a string array
    }
    public void getLetterPlacement() {
        for (String words : wordsInMessage) { //embedded for loops that gets each word in the message and each letter in each word.
            for (int j = 0; j < words.length(); j++) {
                int placement = getIndexOfLetter(words.charAt(j)); //calls interface to get the index number of the letter it is handling
                if (placement == -1)//returns -1 if it cannot find the character in the alphabet array
                {
                    System.out.println("Message contained a something other than a letter please retype");
                    System.exit(-1); //lets user know the message wasn't right and exits program
                }
                else
                {
                    letterPlacement.add(placement); //adds the index of the letter to an array
                }
            }
        }
    }

    @Override
    public void getKeyFileInfo() throws IOException { //inherits from interface
        String keyFilePath = checkFilePath(); //interface has a method to get file path. it also checks if the file exists
        ArrayList<Integer> allKeyNumbers = FileHandler.readCipherNumbers(keyFilePath); //calls read cipher numbers top get every cipher key number from the file0
        startingPosition = (allKeyNumbers.get(allKeyNumbers.size()-1)); //read cipher number adds the starting position to the end of the array list, this assigns that value to the class variable
        allKeyNumbers.remove(allKeyNumbers.size()-1); //removes the starting position number
        if (allKeyNumbers.size() > (startingPosition + letterPlacement.size())) //makes sure there is enough keys by comparing the number of keys in the file to the number of keys that would be needed
        {
            int[] cipherKey = FileHandler.getNeededCipherKey(startingPosition,allKeyNumbers,letterPlacement);//calls get needed cipher key to get only the needed keys from the array list
            encryptMessage(cipherKey); //calls encrypt message to encrypt the message
            FileHandler.rewriteKeyFile(keyFilePath,startingPosition,letterPlacement); //calls rewrite file to replace the starting position with the ending position
        }
        else //if there aren't enough keys lets users know and exits program
        {
            System.out.println("You do not have enough keys in your file, or your starting position is to high");
            System.exit(-1);
        }

    }

    public void getEncryptedFileInfo() throws IOException //method to get the file to write the encrypted message on
    {
        String encryptedFilePath = checkFilePath(); //calls the interface to get the file path and check if its valid
        FileHandler.writeEncryptedFile(encryptedFilePath,startingPosition,returnEncryptedMessage());//calls write encrypted file in file reader class to write the encrypted message and the starting point
    }
    public void encryptMessage(int[] cipherKey){ //method to encrypt the message
        int counter = 0; //need a variable that wont change when a new word or letter gets used in embedded for loops. Used to in the .get() to get the index placement of letters inside letter placement
        String encryptedWord = "";
        for (String words : returnWordsInMessage())
        {
            String encryptedLetters = "";
            for (int j = 0; j < words.length(); j++)
            {
                int indexInAlphabet = letterPlacement.get(counter) + cipherKey[counter]; //adds the original index position to the number of the key to get the encrypted letter index
                while ( indexInAlphabet > 25) //while index placement is greater than the size of the alphabet array it
                {                             //subtracts 26 essentially looping the alphabet till it's a usable number
                    indexInAlphabet -= 26;
                }
                encryptedLetters += alphabetArray[indexInAlphabet]; //in for loop that handles each letter from a word, so this adds each letter it encrypts together
                counter += 1;//increases the index inside letter placement to make sure every letter is used
            }
            encryptedWord += (encryptedLetters + " "); //once internal for loop is done the first word has been handled, so it adds the word plus a space to eventually form the sentence
        }
        setEncryptedMessage(encryptedWord); //sets private instance variable encrypted message to the value of the encrypted message
    }

}
