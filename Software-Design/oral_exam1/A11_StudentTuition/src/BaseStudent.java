/**
 * Base class that will be inherited by all other classes
 */
public abstract class BaseStudent {
    private String studentID;
    private String firstName;
    private String lastName;
    private int numberOfRegisteredHours;
    private static double baseClassHourTuitionRate = 350;

    /**
     * takes in several values to initialize student information
     * @param studentID the students id number
     * @param firstName the students first name
     * @param lastName the students last name
     * @param numberOfRegisteredHours how many hours the student is registered for
     */
    public BaseStudent(String studentID, String firstName, String lastName, int numberOfRegisteredHours)
    {
        if (!(numberOfRegisteredHours > 0)){ //makes sure that the number of registered hours is valid
            throw new IllegalArgumentException ("Student can only register for hours above 0");
        }
        //sets all variables to their values
        this.numberOfRegisteredHours = numberOfRegisteredHours;
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //getters and setters for all variables in class
    public String getStudentID() {return studentID;}
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public int getNumberOfRegisteredHours()
    {
        return numberOfRegisteredHours;
    }
    public double getBaseClassHourTuitionRate() {return baseClassHourTuitionRate;}
    public static void setBaseClassHourTuitionRate(double newHourTuitionRate)
    {
        if (newHourTuitionRate <= 0)
        {
            throw new IllegalArgumentException("Tuition has to be above 0");
        }
        baseClassHourTuitionRate = newHourTuitionRate;
    }
    public void setStudentID(String studentID){this.studentID = studentID;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setNumberOfRegisteredHours(int numberOfRegisteredHours){ //setter throws exception if they try to change the hours to a negative number
        if (numberOfRegisteredHours <= 0){
            throw new IllegalArgumentException ("Student can only register for hours above 0");
        }
        this.numberOfRegisteredHours = numberOfRegisteredHours;
    }

    /**
     * overrides javas toString method to properly format the way its needed
     * @return returns the Strings and int as a string but formatted properly with labels before each.
     */
    @Override //overides javas toString to make own string
    public String toString()
    {
        return String.format("Student Id: %s First Name: %s Last Name: %s Registered Hours: %s", getStudentID(),getFirstName(), getLastName(), getNumberOfRegisteredHours());
    }
    //abstract methods to be inherited by next class

    /**
     * abstract method to be inherited, its purpose is to get the hours over a full load
     */
    public abstract double getHoursOver();

    /**
     * abstract method to be inherited , its purpose is to calculate how much the student owes dependent on their residency
     */
    public abstract double calculateResidency();

    /**
     * abstract method to be inherited, its purpose is to add the college fee dependent on what college they are enrolled at
     */
    public abstract double collegeEnrolledFee();

}

