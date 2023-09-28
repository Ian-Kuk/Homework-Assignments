import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler{
    public static ArrayList<Integer> readCipherNumbers(String filePath) throws IOException
    {
        Scanner readKeyFileInfo = new Scanner(new File(filePath)).useDelimiter(","); //creates a new scanner that reads in the info from the file user gives
        ArrayList<Integer> allCipherNumbers = new ArrayList<>();                     // uses the delimiter to separate the numbers from the commas in the file
        int startingPosition = Integer.parseInt(readKeyFileInfo.nextLine());// the starting position is always the first line in the file
        while (readKeyFileInfo.hasNext())//reads the file until nothing is left
        {
            String cipherNumber = readKeyFileInfo.next(); //sets the next input from scanner to a variable so multiple modifications can be made to the same input
            cipherNumber = cipherNumber.replace("\r", ""); //replaces the return and indent from the last character in the file with empty string
            cipherNumber = cipherNumber.replace("\n", ""); //if not replaced when parseInt is used it will produce inputMismatch
            try
            {
                allCipherNumbers.add(Integer.parseInt(cipherNumber)); //adds the integer value of the cipher to an array list for all cipher numbers
            }
            catch (NumberFormatException a) //catches exception if the keys are something other than just numbers seperated by commas
            {
                System.out.println("Your cipher key contained something other than numbers and commas, change your key in order to encrypt");
                System.exit(-1);
            }
        }
        allCipherNumbers.add(startingPosition); //adds the starting position to the end of all the cipher numbers
        return allCipherNumbers;
    }
    public static int[] getNeededCipherKey(int startingPosition, ArrayList<Integer> allKeyNumbers, ArrayList<Integer> placementOfLetters)
    {
        int amountOfKeysNeeded = placementOfLetters.size();  //placementOfLetters.size() tells how many letters are in the message. Local var created because its used multiple times
        int[]cipherNumbersToUse = new int[amountOfKeysNeeded]; //creates array to the size of how many letters
        int cipherNumbersToUseIndex = 0; //starting index number for the array that holds the cipher Numbers that are needed
        for (int j = startingPosition; j < startingPosition + amountOfKeysNeeded; j++) //loop starts at the starting position number and ends at the ending position of the key
        {
            cipherNumbersToUse[cipherNumbersToUseIndex] = (allKeyNumbers.get(j)); //adds only the needed numbers to another array
            cipherNumbersToUseIndex += 1;
        }
        return cipherNumbersToUse;
    }
    public static void rewriteKeyFile(String filePath, int startingPosition, ArrayList<Integer> placementOfLetters) throws IOException
    {
        String previousStartingNumber = ""; //String that will hold the starting position and the rest of the keys
        BufferedReader readKeyFile = new BufferedReader(new java.io.FileReader(filePath)); //creates buffer reader to read through the file
        String currentLine = readKeyFile.readLine(); //sets string current line to the first line of the page
        while (currentLine != null)
        {
            previousStartingNumber = previousStartingNumber + currentLine + System.lineSeparator(); //sets previous starting number to the starting number then creates a new line use line.seperator, then the line of the cipher keys will be added underneath
            currentLine = readKeyFile.readLine(); //line seperator makes sure the starting position doesnt get joined with the other cipher key numbers
        }
        String newKey = previousStartingNumber.replaceFirst(String.valueOf(startingPosition), String.valueOf(startingPosition + placementOfLetters.size())); //replaces the old starting point with the new starting point but keeps the rest of the cipher numbers
        FileWriter replaceOldStartingPosition = new FileWriter(filePath); //creates a file writer to write in the new starting number
        replaceOldStartingPosition.write(newKey); //writes the entire string with the new starting number
        readKeyFile.close(); //closes the buffer reader and the file writer
        replaceOldStartingPosition.close();

    }
    public static void writeEncryptedFile(String encryptedFile, int startingPosition, String encryptedMessage) throws IOException
    {

        new FileOutputStream(encryptedFile).close(); //erases everything on the text file incase something was already written there
        FileOutputStream writeOnEncryptedFile = new FileOutputStream(encryptedFile, true); //Opens the text file from the file path, the true allows it to be appended
        byte[] startingPlacementAsByte = (startingPosition + "\n").getBytes(); //turns the starting cipher key position into a byte so it can be written in the text file
        byte[] encryptedMessageAsByte = (encryptedMessage).getBytes();//turns the message into a byte so it can be written
        writeOnEncryptedFile.write(startingPlacementAsByte); //Writes the starting position and the cypher code into the text file
        writeOnEncryptedFile.write(encryptedMessageAsByte);
        writeOnEncryptedFile.close(); //closes the outputStream, so it can no longer write onto the file
    }
}