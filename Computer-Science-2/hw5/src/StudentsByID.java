import java.util.Comparator;

public class StudentsByID implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getID(),s2.getID());

        // sort ID by ascending order, and can be used to sort an array of Student by ID
    }
}
