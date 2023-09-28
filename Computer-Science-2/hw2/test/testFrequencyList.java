import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testFrequencyList {
    FrequencyList tester;
    public void init()
    {
        tester = new FrequencyList();
    }

    @Test
    public void test_get_word_frequencies_all()
    {
        init();
        String[] lines = {"hi", "hi"};
        int frequencies = tester.get_word_frequencies("hi", lines);
        assertEquals(frequencies,2);
    }
    @Test
    public void test_get_word_frequencies_none()
    {
        init();
        String[] lines = {"hi", "howdy", "do","you","like","ducks"};
        int frequencies = tester.get_word_frequencies("Quack", lines);
        assertEquals(frequencies,0);
    }

    @Test
    public void test_get_word_frequencies_some()
    {
        init();
        String[] lines = {"hi", "Quack", "do","you","like","ducks", "Quack"};
        int frequencies = tester.get_word_frequencies("Quack", lines);
        assertEquals(frequencies,2);
    }

    @Test
    public void test_get_unique_words_size()
    {
        init();
        String[] lines = {"hi", "Quack", "do","you","like","ducks", "Quack"};
        ArrayList<String> unique = new ArrayList<String>();
        unique.add("hi");
        unique.add("Quack");
        unique.add("do");
        unique.add("you");
        unique.add("like");
        unique.add("ducks");
        ArrayList<String> output = tester.get_unique_words(lines);
        assertEquals(unique.size(), output.size());
    }

    @Test
    public void test_get_unique_words_elements()
    {
        init();
        String[] lines = {"hi", "Quack", "do","you","like","ducks", "Quack"};
        ArrayList<String> unique = new ArrayList<String>();
        unique.add("hi");
        unique.add("Quack");
        unique.add("do");
        unique.add("you");
        unique.add("like");
        unique.add("ducks");
        ArrayList<String> output = tester.get_unique_words(lines);

        for(int i = 0; i < unique.size(); i++)
        {
            assertEquals(unique.get(i), output.get(i));
        }
    }
}
