// Please DO NOT modify
public class Employee {
    private int age;
    private int salary;
    private String firstName;
    private String lastName;
    private String departmentName;

    Employee(int age, int salary, String firstName, String lastName, String departmentName) {
        this.age = age;
        this.salary = salary;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String toString() {
        return "Department: " + getDepartmentName() + ", Name: " + getFirstName() + " " + getLastName() + ", Age: " + getAge() + ", Salary: " + getSalary();
    }
}
