import java.io.IOException;
import java.util.Scanner;
public class EncryptionAndDecryptionDriver {
    public static void main(String[] args) throws IOException {
        boolean userMenu = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to encrypt a message or decrypt an already encrypted message");

        while (userMenu)
        {
            System.out.println();
            System.out.print("Type encrypt to encrypt, type decrypt to decrypt, or type exit to leave:");
            String userChoice = input.next().toLowerCase();
            boolean check = true;
            if (userChoice.equals("encrypt")){
                System.out.println("Type your message, make sure not to include any punctuation, or numbers");
                Encryption encrypt = new Encryption();
                encrypt.getLetterPlacement();
                System.out.print("Enter the file path of the Key file: ");
                encrypt.getKeyFileInfo();
                System.out.print("Enter the file path of the File you want the encrypted message on: ");
                encrypt.getEncryptedFileInfo();
                System.out.println("Your message has been encrypted to the file location specified");
                check = false;
                //A:\Downloads\programTest\baseKey.txt
                //A:\Downloads\programTest\toEncrypt.txt
            }
            if (userChoice.equals("decrypt"))
            {
                System.out.println("Enter the file path of the file with the encrypted message");
                Decryption decrypt = new Decryption();
                decrypt.getLetterPlacement();
                System.out.println("Enter the file path of the key used to encrypt the message");
                decrypt.getKeyFileInfo();
                System.out.println(decrypt.returnWordsInMessage());
                check = false;
            }
            if (userChoice.equals("exit")){
                System.out.println("thank you goodbye");
                userMenu = false;
            }
            else if(check)
            {
                System.out.println();
                System.out.println("That was not a valid choice!");
            }
        }
    }
}