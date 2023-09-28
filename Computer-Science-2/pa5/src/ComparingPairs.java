import java.util.Comparator;

// Implement the compare method. This method takes in two Pairs and compares them on the basis of their "value" (count).
public class ComparingPairs implements Comparator<Pair<String,Integer>> {


    @Override
    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        return o1.getValue() - o2.getValue();
    }
}

