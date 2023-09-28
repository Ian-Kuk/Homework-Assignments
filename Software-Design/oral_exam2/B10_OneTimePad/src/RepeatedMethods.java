/**
 * Abstract class the has 2 method used by multiple classes that use it
 */
public abstract class RepeatedMethods {
    static char[] alphabetArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * Method that will return the index location of a specific letter in the alphabet array
     * @param letter a char that is the letter to look for in the array
     * @return returns the letters placement in the array or -1 if it isnt in the array
     */
    static int getIndexOfLetter(char letter) { //compares input char to each char in array and returns index
        for (int a = 0; a <= 25; a++) {
            if (alphabetArray[a] == (letter)) {
                return a;
            }
        }
        return -1; //used as signal that message has a invalid character
    }

    /**
     * Method that is a for loop to check to make sure every letter is valid in the encrypted or decrypted message
     * Implemented to clean up code in main classes
     * @param message a string that every letter in it will be checked
     * @return
     */
    static int checkLetters(String message)
    {
        for (int i = 0; i < message.length(); i++) { //for loop to go through every letter in the message
            if (getIndexOfLetter(message.charAt(i)) == -1) {
                return -1;
            }
        }
        return 0;

    }
}
