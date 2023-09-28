/**
 * Class that handles the computation of the college tuition, inherits the base class.
 */
public class StudentStatus extends BaseStudent {
    private String studentStatus;
    private String placeOfResidence;
    private String collegeEnrolled;

    /**
     * takes in same values as base class plus some new ones for calculating tuition
     * @param studentId the students id
     * @param firstName the students first name
     * @param lastName the students last name
     * @param numberOfRegisteredHours the number of hours the student is registered for
     * @param studentStatus the status of the students enrollment i.e graduate, undergraduate, open enrolled
     * @param placeOfResidence the place the student is a resident of, i.e iowa, us, or foreign
     * @param collegeEnrolled the college the student is enrolled in, i.e engineering, or liberal arts
     */
    public StudentStatus(String studentId, String firstName, String lastName, int numberOfRegisteredHours, String studentStatus, String placeOfResidence, String collegeEnrolled) {
        super(studentId, firstName, lastName, numberOfRegisteredHours); //sends values to base class

        //all if statements are exception handlers if the user does not type in the proper values for each variable
        if (!studentStatus.equalsIgnoreCase("graduate") && !studentStatus.equalsIgnoreCase("undergraduate") && !studentStatus.equalsIgnoreCase("open enrolled")) {
            throw new IllegalArgumentException("Student can only be graduate, undergraduate, or open enrolled");

        }
        this.studentStatus = studentStatus;

        if (!(placeOfResidence.equalsIgnoreCase("foreign") || placeOfResidence.equalsIgnoreCase("us") || placeOfResidence.equalsIgnoreCase("iowa") || placeOfResidence.equalsIgnoreCase("domestic")))
        {
            throw new IllegalArgumentException("Student can only be classified as foreign, iowa, us, or domestic");
        }
        this.placeOfResidence = placeOfResidence;

        if (!(collegeEnrolled.equalsIgnoreCase("liberal arts") || collegeEnrolled.equalsIgnoreCase("engineering")))
        {
            throw new IllegalArgumentException("Student can only be in liberal arts or engineering");
        }
        this.collegeEnrolled = collegeEnrolled;
    }
    //getters and setters for all new variables in this class
    public String getStudentStatus()
    {
        return studentStatus;
    }
    public String getPlaceOfResidence()
    {
        return placeOfResidence;
    }
    public String getCollegeEnrolled()
    {
        return collegeEnrolled;
    }

    //all setters have exception handling incase their values are to be changed to invalid values
    public void setStudentStatus(String studentStatus)
    {
        if (!(getStudentStatus().equalsIgnoreCase("graduate") || getStudentStatus().equalsIgnoreCase("undergraduate") || getStudentStatus().equalsIgnoreCase("open enrolled")))
        {
            throw new IllegalArgumentException("Student can only be graduate, undergraduate, or open enrolled");
        }
        this.studentStatus = studentStatus;
    }
    public void setPlaceOfResidence(String placeOfResidence)
    {
        if (!(placeOfResidence.equalsIgnoreCase("foreign") || placeOfResidence.equalsIgnoreCase("us") || placeOfResidence.equalsIgnoreCase("iowa") || placeOfResidence.equalsIgnoreCase("domestic")))
        {
            throw new IllegalArgumentException("Student can only be classified as foreign, iowa, us, or domestic");
        }
        this.placeOfResidence = placeOfResidence;
    }
    public void setCollegeEnrolled(String collegeEnrolled)
    {
        if (!(collegeEnrolled.equalsIgnoreCase("liberal arts") || collegeEnrolled.equalsIgnoreCase("engineering"))) {
            throw new IllegalArgumentException("Student can only be in liberal arts or engineering");
        }
        this.collegeEnrolled = collegeEnrolled;
    }

    /**
     * inherited Methods to get the students enrollment status and find the amount of hours over a full load
     * @return the amount of hours over a full load, or 0 if they aren't over a full load
     */

    @Override //inherited class from base student
    public double getHoursOver() {
        if (studentStatus.equalsIgnoreCase("graduate")) //checks if they are graduate
        {
            if(super.getNumberOfRegisteredHours() > 9) //if they have more than 9 hours return their hours subtracted by 9 for hours over course load
            {
                return super.getNumberOfRegisteredHours() - 9;
            }
        }
        if (studentStatus.equalsIgnoreCase("undergraduate"))//checks if they are undergraduate
        {
            if(super.getNumberOfRegisteredHours()> 15){//if they have more than 15 hours return their hours subtracted by 15 for hours over course load
                return (super.getNumberOfRegisteredHours() - 15);
            }
        }
        if (studentStatus.equalsIgnoreCase("open enrolled")){//checks if they are open enrolled
            if(super.getNumberOfRegisteredHours() > 3){//if they have more than 3 hours return their hours subtracted by 3 for hours over course load
                return super.getNumberOfRegisteredHours() - 3;
            }
        }
        return 0; //will return 0 if they have no hours over full load
    }

    /**
     * inherited method to calculate the tuition owed by the student, it calls get hours over in it to calculate said value
     * multiplies the full load hours by the discount or surcharge and adds the hours over full load with an extra discount, or just a normal amount
     * @return the tuition owed after discount or surcharge.
     */
   @Override //inherited class from base student
    public double calculateResidency(){
        if (placeOfResidence.equalsIgnoreCase("iowa")) { //checks if they are from iowa
            //in each calculation it calls get hours over to figure out if they are over or under a full load
            return (((super.getBaseClassHourTuitionRate() * .65) * (super.getNumberOfRegisteredHours() - getHoursOver())) + ((super.getBaseClassHourTuitionRate() * .9) * getHoursOver()));
            // does tuition * the opposite of the discount, so you don't have to do tuition - (tuition * discount)
            //multiplies tuition by the number of hours they have no over a course load, then adds the tuition they do have over the course load with its proper discount
        }
        //in each calculation it calls get hours over to figure out if they are over or under a full load
        if (placeOfResidence.equalsIgnoreCase("foreign")) { //checks if they are foreign
            return (((super.getBaseClassHourTuitionRate() * .03) + super.getBaseClassHourTuitionRate()) * (super.getNumberOfRegisteredHours() - getHoursOver()) + (getHoursOver() * super.getBaseClassHourTuitionRate()));
            //increases the tuition within a full course load by 3% then just adds the rest of their tuition that isn't surcharged
        }
        return (super.getBaseClassHourTuitionRate() * super.getNumberOfRegisteredHours()); //non iowans and non-foreign don't have any penalty or benefits so its just hours * cost

    }

    /**
     * inherited method to add an extra fee depending on the college enrolled
     * only adds the fee if they are over the amount of enrolled hours necessary
     * calls calculate residency inside of it
     * @return the tuition with discount or surcharge plus the extra fee of the college
     */
    @Override //inherited class from base student
    public double collegeEnrolledFee()
    {
        if (collegeEnrolled.equals("liberal arts") && super.getNumberOfRegisteredHours() > 3){ //if they are liberal arts and over the needed hours chalk on the fee 400
            return calculateResidency() + 400; //before adding fee calculates the residency so the fee doesn't interfere with the percentage discount
        }
        if (collegeEnrolled.equals("engineering") && (super.getNumberOfRegisteredHours() > 6)){ //if they are engineering and over the needed hours chalk on the fee 0f 200
            return calculateResidency() + 200; //before adding fee calculates the residency so the fee doesn't interfere with the percentage discount
        }
        return calculateResidency();
    }

    /**
     * overrides java to string method to format a string that way I wanted
     * @return each variable formatted with the proper label in front of it
     */
    @Override
    public String toString()
    {
        return String.format("%s Student Status: %s Place of Residence: %s College Enrolled: %s",super.toString(),getStudentStatus(),getPlaceOfResidence(),getCollegeEnrolled());
    }
}

