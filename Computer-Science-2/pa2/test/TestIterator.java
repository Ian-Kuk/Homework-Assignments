import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class TestIterator {

    Employee e1, e2, e3, e4, e5, e6;

    public void init() {
        e1 = new Employee(25, 9300, "T", "L", "Accounting");
        e2 = new Employee(30, 15000, "M", "Q", "Sales");
        e3 = new Employee(28, 7200, "N", "H", "Sales");
        e4 = new Employee(36, 2800, "J", "M", "Accounting");
        e5 = new Employee(40, 5500, "B", "D", "Marketing");
        e6 = new Employee(28, 13000, "C", "P", "Engineering");
    }

    @Test
    public void testLength1() {
        init();
        assertEquals(1, new EmployeesIterator(new Employee[] {e1}, "Accounting").getLength());
    }

    @Test(timeout=5000)
    public void testEmployeeIterator1() {
        init();
        Iterator<Employee> iter = new EmployeesIterator(new Employee[] {e1}, "Accounting");
        String s = "";
        while (iter.hasNext()) {
            s += iter.next().toString() + "\n";
        }
        assertEquals("Department: Accounting, Name: T L, Age: 25, Salary: 9300\n", s);
    }

    @Test(timeout=5000)
    public void testEmployeeIterator4() {
        init();
        int size = 0;
        Iterator<Employee> iter = new EmployeesIterator(new Employee[] {}, "Marketing");
        while (iter.hasNext()) {
            iter.next();
            size += 1;
        }
        assertEquals(0, size);
    }

    @Test(timeout=5000)
    public void testNumberOfEmployees1() {
        init();
        Iterator<Employee> iter = new EmployeesIterator(new Employee[] {e1, e2, e3, e4, e5, e6}, "Marketing");
        assertEquals(0, ((EmployeesIterator) iter).getNumberOfEmployeesWithSalaryHigherThan(6000));
    }
}

