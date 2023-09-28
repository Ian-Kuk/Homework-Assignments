import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class EmployeesIterator implements Iterator<Employee> {
    private Employee[] employees;
    private int index = 0;
    private int numberOfEmployees;
    // Store all the workers whose department matches the provided departmentName in the employees array.
    // The size of employees array should be equal to the number of workers who match the departmentName.
    // Example:
    // e1.department = "Accounting"  &&  e2.department = "Finance" && e3.department = "CS" && e4.department = "CS"
    // EmployeesIterator([e1, e2, e3, e4], "Accounting") --> employees array contains only one element which is e1
    // EmployeesIterator([e1, e2, e3, e4], "CS") --> employees array contains two elements which are e3 and e4
    EmployeesIterator(Employee[] workers, String departmentName) {
        for (Employee worker : workers) {
            if (worker.getDepartmentName().equals(departmentName)) {
                numberOfEmployees++;
            }
        }
        employees = new Employee[numberOfEmployees];
        for(Employee worker:workers)
        {
            if(worker.getDepartmentName().equals(departmentName)){
                employees[index] = worker;
                index++;
            }
        }
        index = 0;
    }

    // You can't modify this function.
    public int getLength() {
        return employees.length;
    }

    // Return true if there is a next employee in the array.
    public boolean hasNext() {
        if(index < getLength()){
            return true;
        }
        else{
            return false;
        }
    }

    // Return the next employee. If there is no next employee throw NoSuchElementException
    public Employee next() {
        if(hasNext()){
            index ++;
            return employees[index-1];
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    public int getNumberOfEmployeesWithSalaryHigherThan(int s) {
        int employeesHigher = 0;
        while(hasNext()){
            if(next().getSalary() > s){
                employeesHigher ++;
            }
        }
        return employeesHigher;
    }

}
