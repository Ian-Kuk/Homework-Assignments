import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DecryptionDriver
{
    /**
     * main class to run the decryption program
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        boolean menu = true;
        Scanner input = new Scanner(System.in);
        while(menu) //while loop that presents users options
        {
            System.out.println("Please enter the name of the key file(include the .txt)");
            System.out.println("Or Type 1 to exit");
            String keyFile = input.next(); //gets the key file
            if(new File(keyFile).isFile())
            {
                Decryption decryption = new Decryption(keyFile); //creates instance of Decryption
                decryption.getMessage(); //gets message from file
                String decryptedMessage = decryption.decryptMessage(); //decrypts the message that was found
                System.out.println("The decrypted message is: " + decryptedMessage); //prints message
            }
            else if (keyFile.equalsIgnoreCase("1")) { //exits menu if user wants to
                System.out.println("Goodbye");
                menu = false;

            }
            else //if the file doesnt exist
            {
                System.out.println("The key file does not exist");
            }
        }

    }
}
