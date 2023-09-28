import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates a GUI that will present all the information from the entire quiz.
 * It will show the users name, each correct answer, the users answer, the points they got on each question, and their final score
 */
public class QuizSummary extends JFrame
{
    /** Method that combines each JFrame component to show every piece of information
     *
     */
    public QuizSummary(){
        //default information on title,layout,size,and close operation **/
        setTitle("Quiz summary");
        setLayout(null);
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        double score = (RadioButtonQuestion.questionOneScore + CheckboxQuestion.questionTwoScore + DropdownBoxQuestion.questionThreeScore + ListQuestion.questionFourScore); //adds score from each class
        //prints out all information about the quiz. It gives the final score and shows every question.
        //Shows the right answer to every question, the answer they chose, and the points obtained from each question

        JLabel finalScore = new JLabel("Your final score: " + score + "/4");
        finalScore.setBounds(0,550,300,50);
        add(finalScore);

        JLabel summary = new JLabel("Quiz summary:");
        summary.setBounds(0,0,300,50);
        add(summary);

        JLabel userName = new JLabel("Name: " + FrontPage.enteredStudentName);
        userName.setBounds(0,25,400,50);
        add(userName);

        JLabel questionOne = new JLabel("Question 1: What is 1001 in decimal?");
        questionOne.setBounds(0,50,300,50);
        add(questionOne);

        JLabel questionOneAnswer = new JLabel("Correct answer: 9");
        questionOneAnswer.setBounds(0,75,300,50);
        add(questionOneAnswer);

        JLabel questionOneTheirAnswer = new JLabel("You answered: " + RadioButtonQuestion.theirChoice);
        questionOneTheirAnswer.setBounds(0,100,300,50);
        add(questionOneTheirAnswer);

        JLabel questionOnePoint = new JLabel("Points: " + RadioButtonQuestion.questionOneScore);
        questionOnePoint.setBounds(0,125,300,50);
        add(questionOnePoint);

        JLabel questionTwo = new JLabel("Question 2: What computer parts are essential to run?");
        questionTwo.setBounds(0,175,450,50);
        add(questionTwo);

        JLabel questionTwoAnswer = new JLabel("Correct answers CPU and Motherboard");
        questionTwoAnswer.setBounds(0,200,300,50);
        add(questionTwoAnswer);

        JLabel questionTwoTheirAnswer = new JLabel("You answer: " + CheckboxQuestion.theirChoice);
        questionTwoTheirAnswer.setBounds(0,225,300,50);
        add(questionTwoTheirAnswer);

        JLabel questionTwoPoint = new JLabel("Points: " + CheckboxQuestion.questionTwoScore);
        questionTwoPoint.setBounds(0,250,300,50);
        add(questionTwoPoint);

        JLabel questionThree = new JLabel("Question 3: What system do humans use to count?");
        questionThree.setBounds(0,300,450,50);
        add(questionThree);

        JLabel questionThreeAnswer = new JLabel("Correct answer: Decimal");
        questionThreeAnswer.setBounds(0,325,300,50);
        add(questionThreeAnswer);

        JLabel questionThreeTheirAnswer = new JLabel("Your answer: " + DropdownBoxQuestion.theirChoice);
        questionThreeTheirAnswer.setBounds(0,350,300,50);
        add(questionThreeTheirAnswer);

        JLabel questionThreePoint = new JLabel("Points: " + DropdownBoxQuestion.questionThreeScore);
        questionThreePoint.setBounds(0,375,300,50);
        add(questionThreePoint);

        JLabel questionFour = new JLabel("Question 4: Which choice equals 124(decimal)?");
        questionFour.setBounds(0,425,350,50);
        add(questionFour);

        JLabel questionFourAnswer = new JLabel("Correct answer: 7C");
        questionFourAnswer.setBounds(0,450,300,50);
        add(questionFourAnswer);

        JLabel questionFourTheirAnswer = new JLabel("Your answer: " + ListQuestion.theirChoice);
        questionFourTheirAnswer.setBounds(0,475,300,50);
        add(questionFourTheirAnswer);

        JLabel questionFourPoint = new JLabel("Points: " + ListQuestion.questionFourScore);
        questionFourPoint.setBounds(0,500,300,50);
        add(questionFourPoint);

        //Creates jbutton called quit to be used to exit program
        JButton quit = new JButton("Quit");
        quit.setBounds(380,550,100,50);
        add(quit);

        //registers quit button with event handler
        QuitHandler quitQuiz = new QuitHandler();
        quit.addActionListener(quitQuiz);


    }

    /**
     * Event handler for the Quit button being pressed
     */

    private static class QuitHandler implements ActionListener//invokes actionListener interface
    {
        /**
         * if the account receives the action the button was pushed it exits the entire code
         *
         */
        @Override
        public void actionPerformed(ActionEvent e)  //handles what to do when button is clicked
        {
            System.exit(0); //exits the program when the user presses quit
        }
    }

}
