/**
 * Main class that holds the driver and will run the program
 */
public class TuitionDriver {
    /**
     * driver that creates an array 18 long of the base class
     * then creates 18 instances of different students with specific values
     * polymorphises the base class with the new student
     * prints out the information of each student, changes the tuition and re prints their information
     */
    public static void main(String[] args){
        BaseStudent[] allStudents = new BaseStudent[18]; //creates array of 18 instances of BaseStudent
        //creates 18 students each with different names, id, registered hours, enrollment status, residency, and college they are in.
        StudentStatus student1 = new StudentStatus("10010231", "Mike", "Taylor", 6, "graduate", "iowa", "engineering");
        StudentStatus student2 = new StudentStatus("51215684", "Suzie", "Johnson", 11, "undergraduate", "iowa", "liberal arts");
        StudentStatus student3 = new StudentStatus("76843597", "Franklin", "Brown", 17, "undergraduate", "iowa", "engineering");
        StudentStatus student4 = new StudentStatus("24568175", "Megan", "Kuk", 12, "graduate", "iowa", "liberal arts");
        StudentStatus student5 = new StudentStatus("97134682", "Ian", "Kuk", 8, "open enrolled", "iowa", "engineering");
        StudentStatus student6 = new StudentStatus("64528775", "Patricia", "Williams", 2, "open enrolled", "iowa", "liberal arts");
        StudentStatus student7 = new StudentStatus("11325574", "Hans", "Johnson", 8, "graduate", "us", "engineering");
        StudentStatus student8 = new StudentStatus("55684991", "Ben", "Martin", 10, "graduate", "us", "liberal arts");
        StudentStatus student9 = new StudentStatus("44318546", "Noah", "Clark", 17, "undergraduate", "us", "engineering");
        StudentStatus student10 = new StudentStatus("12247584", "Flavio", "Rodriguez", 8, "undergraduate", "us", "liberal arts");
        StudentStatus student11 = new StudentStatus("13467985", "Keaton", "Franzin", 5, "open enrolled", "us", "engineering");
        StudentStatus student12 = new StudentStatus("97643125", "Katie", "Harris", 12, "open enrolled", "us", "liberal arts");
        StudentStatus student13 = new StudentStatus("46792851", "Jasmin", "Lee", 9, "graduate", "foreign", "engineering");
        StudentStatus student14 = new StudentStatus("19735846", "Cesar", "Madriz", 12, "graduate", "foreign", "liberal arts");
        StudentStatus student15 = new StudentStatus("64825587", "Ted", "Davis", 14, "undergraduate", "foreign", "engineering");
        StudentStatus student16 = new StudentStatus("99450213", "Adam", "Miller", 18, "undergraduate", "foreign", "liberal arts");
        StudentStatus student17 = new StudentStatus("78784201", "Josh", "Moga", 13, "open enrolled", "foreign", "engineering");
        StudentStatus student18 = new StudentStatus("11254463", "Darlene", "Ardelean", 7, "open enrolled", "foreign", "liberal arts");

        //polymorphises all students to equal the student
        allStudents[0] = student1;
        allStudents[1] = student2;
        allStudents[2] = student3;
        allStudents[3] = student4;
        allStudents[4] = student5;
        allStudents[5] = student6;
        allStudents[6] = student7;
        allStudents[7] = student8;
        allStudents[8] = student9;
        allStudents[9] = student10;
        allStudents[10] = student11;
        allStudents[11] = student12;
        allStudents[12] = student13;
        allStudents[13] = student14;
        allStudents[14] = student15;
        allStudents[15] = student16;
        allStudents[16] = student17;
        allStudents[17] = student18;

        //prints all the students information
        for (BaseStudent student: allStudents)
        {
            System.out.println(student); //prints student info
            System.out.println(student.getFirstName() + "'s owed tuition $" + student.collegeEnrolledFee()); //prints the students name and owed tuition
            System.out.println();
        }
        BaseStudent.setBaseClassHourTuitionRate(200); //changes the base tuition rate
        System.out.println("Tuition Rates and change");
        System.out.println();
        for (BaseStudent student: allStudents) //reprints all of the students information and their new tuitions.
        {
            System.out.println(student);
            System.out.println(student.getFirstName() + "'s owed tuition $" + student.collegeEnrolledFee());
            System.out.println();
        }
    }
}
