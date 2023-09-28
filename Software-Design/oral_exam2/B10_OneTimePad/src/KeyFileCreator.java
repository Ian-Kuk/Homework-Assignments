import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main program to create a new key. Will get the location where it shoudl write and how many keys it should produce.
 * Will write to location given or create the location given and create a key with the amount specified.
 * The numbers in the key will be between 1 and 26
 */
public class KeyFileCreator
{
    private static ArrayList<Integer> key = new ArrayList<>();

    /**
     * Constructor for the key class which will add to array the amount of keys wanted
     * @param numberOfKeys interger from driver that is how many keys the user wants
     */
    public KeyFileCreator(int numberOfKeys)
    {
        for(int i = 0; i< numberOfKeys ; i++){ //loops through wanted key amount
            key.add(((int) Math.floor(Math.random() * (26 - 1)) + 1)); //random number 1-26 gets added
        }
    }

    /**
     * Method that will create a new key file and then write the starting position at 0 and the key in the next line.
     * @throws IOException
     */
    public void writeKeyFile() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean exit = true;
        while(exit)
        {
            System.out.println("What you like to name the key file (exclude the .txt part)");
            String keyPath = input.next() + ".txt";
            if(new File(keyPath).isFile()) //if the file already exists
            {
                System.out.println("That file already exists either restart the program or type a new name");
            }
            else //if the file doesnt exist
            {
                File keyFile = new File(keyPath);
                keyFile.createNewFile(); //create file
                FileWriter makeKey = new FileWriter(keyPath);
                makeKey.write("0\n"); //write start at 0
                makeKey.write(key.toString()); //writes array of keys
                makeKey.close();
                System.out.println("The file has been created");
                exit = false;
            }
        }
    }

    /**
     * Method that will take an already existing file wipe it clean then write the new starting position and key
     * @throws IOException
     */
    public void overrideKeyFile() throws IOException
    {
        Scanner input = new Scanner(System.in);
        boolean exit = true;
        while(exit) {
            System.out.println("Please enter the name of the key file(include the .txt");
            String keyFile = input.next();
            if (new File(keyFile).isFile()) //if the file exists
            {
                new FileOutputStream(keyFile).close(); //erases everything on the text file incase something was already written there
                FileWriter makeKey = new FileWriter(keyFile);
                makeKey.write("0\n"); //writes starting position 0
                makeKey.write(key.toString()); //writes key array
                makeKey.close();
                System.out.println("The file has been overridden");
                exit = false;
            }
            else //if file doesnt exist
            {
                System.out.println("That file does not exist");
            }
        }
    }
}
