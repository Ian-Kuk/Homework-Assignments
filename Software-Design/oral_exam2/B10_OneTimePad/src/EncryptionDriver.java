import java.io.IOException;
import java.util.Scanner;

public class EncryptionDriver {
    /**
     * Main method that will run the encryption program
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        boolean menu = true;
        Scanner input = new Scanner(System.in);
        while(menu) //user menu loop to print choices
        {
            boolean inChoice = true;
            System.out.println("If the encrypted message will be written to a new file type 1");
            System.out.println("If the encrypted message will be written to an existing file type 2");
            System.out.println("To leave type 3");
            String file = input.next();
            if(file.equalsIgnoreCase("1") || file.equalsIgnoreCase("2")) //if the user wants to create or wtie an encrypted file
            {
                inChoice = false;
                Encryption encryption = new Encryption(file); //creates new instance
                encryption.getMessage(); //gets message from user
                encryption.getKey(); //gets the key from user
                encryption.encryptFile(); //encrypts the message and writes to file
            }
            if(file.equalsIgnoreCase("3"))
            {
                menu = false; //exits program
            }
            else if(inChoice) //if they didnt chose a valid option
            {
                System.out.println("Invalid choice try again");
            }
        }

    }
}
