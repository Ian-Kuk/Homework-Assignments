import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class WordOccurrence {
  // Given any Scanner input, this function returns a HashMap of
  public static HashMap<String,Integer> countWords(Scanner scnr) {
    HashMap<String,Integer> wordCount = new HashMap<String,Integer>();

    String line;
    while ( scnr.hasNextLine() ) {
      line = scnr.nextLine();
      // stripping punctuation, and splitting line into words (do not worry if you do not understand)
      // we are creating one words array for each line
      String[] words = line.replaceAll("[^\\p{L}\\s']"," ")
                           .toLowerCase().split("\\s+");
      //loop over "words" array to update the occurrences in the HashMap(wordCount).
      for (String word : words)
      {
        if (!wordCount.containsKey(word)) {
          wordCount.put(word, 0);
        }
        wordCount.put(word, wordCount.get(word) + 1);
      }
    }
    return wordCount;
  }

  public static ArrayList<Pair<String,Integer>> sortWordCounts(HashMap<String,Integer> wordCount) {
    ArrayList<Pair<String,Integer>> wcList = new ArrayList<>();
    for(String word : wordCount.keySet()){
      wcList.add(new Pair<>(word,wordCount.get(word)));
    }
    Comparator<Pair<String, Integer>> comparePairs = new ComparingPairs();
    wcList.sort(comparePairs);
    return wcList;
  }

  public static void main(String[] args) {
    HashMap<String,Integer> wordCount;
    ArrayList<Pair<String,Integer>> wcList;
    Scanner input;
    if ( args.length > 0 ) {
      try {
        input = new Scanner(new File(args[0]));
      }
      catch(FileNotFoundException e) {
        System.out.println("Cannot find file "+args[0]);
        System.out.println("Exiting program");
        input = new Scanner("");
        System.exit(0);
      }
    } else {
      input = new Scanner("Now is the time for all good men to come to the aid of the party."+
        "How now brown cow? "+"Time flies like an arrow, but fruit flies like a banana."+
        "To be or not to be, that is the question.");
    }
    wordCount = countWords(input);
    wcList    = sortWordCounts(wordCount);
    for ( Pair<String,Integer> p : wcList ) {
      System.out.println(p.getKey()+"\t"+p.getValue());
    }

  }
}
