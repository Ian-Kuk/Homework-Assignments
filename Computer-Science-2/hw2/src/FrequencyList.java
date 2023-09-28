import java.util.ArrayList;
import java.util.Scanner;


public class FrequencyList
{
    /*
      Given a single String word, traverse the String[] line
      to find the number of times word is given in line

      input:
        String word: Word we are looking for
        String[] line: Array of words
      output:
        int: frequency of word in the array line
     */
    public static int get_word_frequencies(String word, String[] line)
    {
        int count = 0;
        for (int j = 0; j < line.length; j++){
            if (line[j].equals(word) == true )
                count += 1;
        }
        return count;

    }

    /*
        Create a String[] of size numwords that is populated with words a scanner

        input:
            int numwords: the number of words to be expected as input
        output:
            String[]: Array of words that is recieved from user via Scanner

     */
    public static String[] get_words(Scanner input, int numwords){
        String[] allWords = new String[numwords];
        //String check = input.next();
        //while (input.hasNext());
        //{
            //check = input.next();
            //System.out.println(check);
        //}

        for (int i = 0; i < numwords; i++)
        {
            allWords[i] = input.next();
        }
        return allWords;
    }

    /*
        Go through every element in String[] line and find all
        words

        input:
            String[] line: Array of words
        output:
            ArrayList<String>: An Arraylist containing all the unique words in line

        hint: Arraylists have a method called contains
     */
    public static ArrayList<String> get_unique_words(String[] line)
    {
        ArrayList<String> uWords = new ArrayList<>();
        for (int k = 0; k < line.length; k++){
            if (uWords.contains(line[k]) == false)
                uWords.add(line[k]);
        }
        return uWords;
    }

    public static void main(String[] args)
    {
        int numwords;
        Scanner input = new Scanner(System.in);
        // Implement a Scanner (call it input)
        numwords = input.nextInt();
        // Get an int from the Scanner and save it to numwords
        String[] line = get_words(input, numwords);
        ArrayList<String> unique_words = get_unique_words(line);
        for(int i=0;i< unique_words.size();i++)
        {
            int frequency = get_word_frequencies(unique_words.get(i),line);
            System.out.println("Word: " + unique_words.get(i) + "\tFrequency: " + frequency);
        }
    }
}
