import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class testColoredPoint {
    ColoredPoint tester;
    public void init()
    {
        tester = new ColoredPoint(2,3, "red");
    }
    @Test
    public void test_getX_getY()
    {
        init();
        assertEquals(tester.getX(),2.0, 0.0);
        assertEquals(tester.getY(),3.0, 0.0);
    }
    @Test
    public void test_getLocation()
    {
        init();
        Point q = tester.getLocation();
        assertEquals(q.getX(),2.0, 0.0);
        assertEquals(q.getY(),3.0, 0.0);
    }
    @Test
    public void test_translate_positive()
    {
        init();
        tester.translate(5.0, 1.0);
        assertEquals(tester.getX(),7.0, 0.0);
        assertEquals(tester.getY(),4.0, 0.0);
    }
    @Test
    public void test_getColor()
    {
        init();
        assertEquals(tester.getColor(),"red");
    }
    @Test
    public void test_toString()
    {
        init();
        assertEquals(tester.toString(),"(2.0,3.0,red)");
    }
    @Test
    public void test_equals_false_different_types()
    {
        init();
        assertEquals(tester.equals(tester.getLocation()),false);
    }
    @Test
    public void test_equals_true()
    {
        init();
        Point q = tester.getLocation();
        assertEquals(tester.equals(new ColoredPoint(q,"red")),true);
    }
}

