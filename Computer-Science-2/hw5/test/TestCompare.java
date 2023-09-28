import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;


public class TestCompare {
    Comparator tester1, tester2;
    Student s1, s2, s3, s4, s5;
    public void init(){
        tester1 = new StudentsByID();
        tester2 = new StudentsByName();
        s1 = new Student("Fa","L",11111);
        s2 = new Student("Fb","L",22222);
        s3 = new Student("F","Lc",-33333);
        s4 = new Student("F", "Ad", 11114);
        s5 = new Student("Fe", "L", 11111);
    }


    @Test
    public void test_compare_id_0()
    {
        init();
        assertTrue(tester1.compare(s1, s2) < 0);
        assertTrue(tester1.compare(s1, s3) > 0);
        assertTrue(tester1.compare(s4, s5) > 0);
    }

    @Test
    public void test_compare_id_1()
    {
        init();
        Student[] students = {s2, s1};
        Arrays.sort(students, tester1);
        assertEquals(students[0].toString(), "L, Fa ID: 11111");
        assertEquals(students[1].toString(), "L, Fb ID: 22222");
    }


    @Test
    public void test_compare_id_2()
    {
        init();
        Student[] students = {s5, s1};
        Arrays.sort(students, tester1);
        assertEquals(students[0].toString(), "L, Fe ID: 11111");
        assertEquals(students[1].toString(), "L, Fa ID: 11111");
    }


    @Test
    public void test_compare_name_0()
    {
        init();
        assertTrue(tester2.compare(s1, s2) < 0);
        assertTrue(tester2.compare(s1, s4) > 0);
        assertTrue(tester2.compare(s5, s4) > 0);
    }

    @Test
    public void test_compare_name_1()
    {
        init();
        Student[] students = {s2, s1};
        Arrays.sort(students, tester2);
        assertEquals(students[0].toString(), "L, Fa ID: 11111");
        assertEquals(students[1].toString(), "L, Fb ID: 22222");
    }

    @Test
    public void test_compare_name_2()
    {
        init();
        Student[] students = {s3, s4};
        Arrays.sort(students, tester2);
        assertEquals(students[0].toString(), "Ad, F ID: 11114");
        assertEquals(students[1].toString(), "Lc, F ID: -33333");
    }


    @Test
    public void test_compare_id_and_name()
    {
        init();
        Student[] students = {s1, s2, s3, s4, s5};

        Arrays.sort(students, tester1);
        assertEquals(students[0].toString(), "Lc, F ID: -33333");
        assertEquals(students[1].toString(), "L, Fa ID: 11111");
        assertEquals(students[2].toString(), "L, Fe ID: 11111");
        assertEquals(students[3].toString(), "Ad, F ID: 11114");
        assertEquals(students[4].toString(), "L, Fb ID: 22222");

        Arrays.sort(students, tester2);
        assertEquals(students[0].toString(), "Ad, F ID: 11114");
        assertEquals(students[1].toString(), "L, Fa ID: 11111");
        assertEquals(students[2].toString(), "L, Fb ID: 22222");
        assertEquals(students[3].toString(), "L, Fe ID: 11111");
        assertEquals(students[4].toString(), "Lc, F ID: -33333");
    }


    @Test
    public void test_compare_name_and_id()
    {
        init();
        Student[] students = {s1, s2, s3, s4, s5};

        Arrays.sort(students, tester2);
        assertEquals(students[0].toString(), "Ad, F ID: 11114");
        assertEquals(students[1].toString(), "L, Fa ID: 11111");
        assertEquals(students[2].toString(), "L, Fb ID: 22222");
        assertEquals(students[3].toString(), "L, Fe ID: 11111");
        assertEquals(students[4].toString(), "Lc, F ID: -33333");

        Arrays.sort(students, tester1);
        assertEquals(students[0].toString(), "Lc, F ID: -33333");
        assertEquals(students[1].toString(), "L, Fa ID: 11111");
        assertEquals(students[2].toString(), "L, Fe ID: 11111");
        assertEquals(students[3].toString(), "Ad, F ID: 11114");
        assertEquals(students[4].toString(), "L, Fb ID: 22222");
    }

}
