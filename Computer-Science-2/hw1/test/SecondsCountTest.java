import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecondsCountTest {
    SecondsCount tester;
    public void init(){
        tester = new SecondsCount();
    }
    @Test
    public void testhowManySeconds_3_hours()
    {
        init();
        assertEquals(tester.howManySeconds(3), 10800);
    }
    @Test
    public void testhowManySeconds_5_hours()
    {
        init();
        assertEquals(tester.howManySeconds(5), 18000);
    }
}