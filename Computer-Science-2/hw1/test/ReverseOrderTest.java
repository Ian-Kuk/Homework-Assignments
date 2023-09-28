import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseOrderTest {
    ReverseOrder tester;
    public void init(){
        tester = new ReverseOrder();
    }
    @Test
    public void testReverseArray_1()
    {
        init();
        int[] arr = new int[]{7,3,2,9};
        int[] ans = new int[]{9,2,3,7};
        for (int i = 0; i < 4; i++) {
            assertEquals(tester.ReverseArray(4, arr)[i], ans[i]);
        }
    }
    @Test
    public void testReverseArray_2()
    {
        init();
        int[] arr = new int[]{1,2,3};
        int[] ans = new int[]{3,2,1};
        for (int i = 0; i < 3; i++) {
            assertEquals(tester.ReverseArray(3, arr)[i], ans[i]);
        }
    }
}