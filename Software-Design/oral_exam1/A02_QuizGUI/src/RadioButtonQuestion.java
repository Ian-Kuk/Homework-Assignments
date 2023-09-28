import javax.swing.*;
import java.awt.event.*;

/**
 * class that creates a GUI for the quiz that uses radio buttons for the user to pick their answer
 */

public class RadioButtonQuestion extends JFrame
{
    /**
     * Boolean for driver to know when to hide the GUI
     */
    public boolean exit = true;
    /**
     * Radio buttons that will have possible answers
     */
    private final JRadioButton answerOneButton,answerTwoButton,answerThreeButton,answerFourButton;
    /**
     * holds the score of the user for this question
     */
    public static int questionOneScore;
    /**
     * holds the string of the answer the user chose
     */
    public static String theirChoice;

    /**
     * Creates radio buttons a positions them
     * It also creates a group of the buttons so only one can be selected at a time.
     * Creates a button that when clicked goes to the next page
     */
    public RadioButtonQuestion()
    {
        //default constructors of the gui size, name, layout, and close operation
        setTitle("Question One");
        setLayout(null);
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creates the label with the question, sets its position and adds it to frame
        JLabel questionOne = new JLabel("What is 1001 in decimal?");
        questionOne.setBounds(0, 0, 200, 50);
        add(questionOne);

        //Creates the radio buttons with their name and if they will start selected
        //Sets their position and adds them to frame
        answerOneButton = new JRadioButton("9", false);
        answerOneButton.setBounds(0, 50, 100, 30);
        add(answerOneButton);

        answerTwoButton = new JRadioButton("7", false);
        answerTwoButton.setBounds(0, 100, 100, 30);
        add(answerTwoButton);

        answerThreeButton = new JRadioButton("4", false);
        answerThreeButton.setBounds(0, 150, 100, 30);
        add(answerThreeButton);

        answerFourButton = new JRadioButton("6", false);
        answerFourButton.setBounds(0, 200, 100, 30);
        add(answerFourButton);

        //Creates next button sets its position and adds it to frame
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(200, 200, 75, 50);
        add(nextButton);

        //creates new instance of the private class and uses add action listener to register the event handler for each component
        NextHandler radioChoice = new NextHandler();
        nextButton.addActionListener(radioChoice);

        //creates a button group of all radio buttons, so only one button can be selected at a time
        ButtonGroup answers = new ButtonGroup();
        answers.add(answerOneButton);
        answers.add(answerTwoButton);
        answers.add(answerThreeButton);
        answers.add(answerFourButton);
    }

    /**
     * event handler for the next button being pushed.
     */
    private class NextHandler implements ActionListener //invokes actionListener interface
    {
        /**
         * action listener that uses if statements to decide if the user has the right question selected when they clicked the next button
         * Changes the boolean to false when done so the driver knows to hide the gui
         * adds a point if answered correctly and always sets the string theirChoice to what they selected
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)  //handler for the next button getting clicked event
        {
            // if they got it right increase the score. Each button adds the choice the user made, to be used in the quiz summary
            if (answerOneButton.isSelected())
            {
                questionOneScore = 1;
                theirChoice = "9";
            }
            if (answerTwoButton.isSelected())
            {
                theirChoice = "7";
            }
            if (answerThreeButton.isSelected())
            {
                theirChoice = "4";
            }
            if (answerFourButton.isSelected())
            {
                theirChoice = "6";
            }
            if (!answerOneButton.isSelected() && !answerTwoButton.isSelected() && !answerThreeButton.isSelected() && !answerFourButton.isSelected())
            {
                theirChoice = "Unanswered";
            }
            exit = false; //lets while loop know its done and to make it no longer visible
            //return 0;
        }
    }
}
