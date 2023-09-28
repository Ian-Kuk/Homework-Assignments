import java.io.IOException;
import java.util.Scanner;

public class KeyFileDriver {
    /**
     * main method that will run the KeyFileCreator. It will get if the user wants to overwrite a file or create a new one,
     * Also it gets how many keys the user wants in the file
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while (!exit)
        {
            System.out.println("Type 1 to create a new key file");
            System.out.println("Type 2 to create a new key on an already existing key file");
            System.out.println("Type 3 to Exit");
            boolean inChoice = false;
            String choice = input.next();
            if(choice.equals("1")) //if they want to create new key file
            {
                inChoice = true;
                while(true)
                {
                    System.out.println("How many keys would you like");
                    String number = input.next();
                    try
                    {
                        KeyFileCreator keyFile = new KeyFileCreator(Integer.parseInt(number)); //sends the amount of keys wanted to the constructor
                        keyFile.writeKeyFile(); //calls special method that will create new file
                        break;
                    }
                    catch (NumberFormatException numberFormatException) { //if user doesnt follow directions
                        System.out.println("You must enter a number");
                    }
                }
            }
            if(choice.equals("2"))
            {
                inChoice = true;
                while(true)
                {
                    System.out.println("How many keys would you like");
                    String number = input.next();
                    try
                    {
                        KeyFileCreator keyFile = new KeyFileCreator(Integer.parseInt(number)); //sends amount of keys wanted to constructor
                        keyFile.overrideKeyFile();//calls special method that will overwrite an existing file.
                        break;
                    }
                    catch (NumberFormatException numberFormatException) { //if user doesnt follow directions
                        System.out.println("You must enter a number");
                    }
                }
            }
            if(choice.equals("3")) //exits program
            {
                System.out.println("Goodbye");
                exit = true;
            }
            else if(!inChoice) //if user didnt follow directions
            {
                System.out.println("Invalid choice try again");
            }
        }
    }
}
