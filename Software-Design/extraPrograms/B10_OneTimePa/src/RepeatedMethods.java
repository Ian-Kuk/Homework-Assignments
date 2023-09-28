import java.io.File;
import java.util.Scanner;

public abstract class RepeatedMethods
{
    static char[] alphabetArray = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    static int getIndexOfLetter(char letter){ //compares input char to each char in array and returns index
        for(int a = 0; a <=25; a++){
            if (alphabetArray[a] == (letter)) {
                return a;
            }
        }
        return -1; //used as signal that message has a invalid character
    }
    static String checkFilePath() {
        Scanner readFile = new Scanner(System.in);
        String filePath = ""; //creates a scanner and gets a file path from the user
        boolean checkFilePath = true; //used to loop until user types in valid file path
        while (checkFilePath)
        {
            filePath = readFile.next();
            if (new File(filePath).isFile()) //if its a file exit the loop
            {
                checkFilePath = false;
            }
            else //keeps asking for file path name if file does not exist
            {
                System.out.println("File path not valid please retype");
            }
        }
        return filePath; //returns the file path user typed in
    }
}
