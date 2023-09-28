
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates a GUI for the quiz that implements a checkbox for the user to chose their answer
 */

public class CheckboxQuestion extends JFrame
{
    /**
     * Boolean used to hide GUI in driver
     */
    public boolean exitTwo = true;
    /**
     * Check boxes used in GUI
     */
    private final JCheckBox firstCheck, secondCheck, thirdCheck, fourthCheck;
    /**
     * String of the answer the user chose
     */
    public static String theirChoice = "";
    /**
     * double of the score the user gets for this question
     */
    public static double questionTwoScore;

    /**
     *Creates the checkbox question. It puts out four questions for the user to answer.
     * If the user answers correctly it adds .5 to their score if its incorrect it can subtract .5
     * It also adds a button that if clicked will change a boolean value that will make the driver hide it.
     */

    public CheckboxQuestion()
    {
        //sets default information like title,layout,size, and close operation
        setTitle("Question 2");
        setLayout(null);
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creates JLabels to tell user the question, and about point deduction
        JLabel questionTwo = new JLabel("What computer parts are essential to run?");
        questionTwo.setBounds(0, 0, 350, 50);
        add(questionTwo);

        JLabel lostPoints = new JLabel("Wrong answers will deduct half a point!");
        lostPoints.setBounds(0, 0, 300, 100);
        add(lostPoints);

        //Sets the JCheckBox's names and positions, and adds it to the frame
        firstCheck = new JCheckBox("RGB lights");
        firstCheck.setBounds(0, 75, 200, 20);
        add(firstCheck);

        secondCheck = new JCheckBox("CPU");
        secondCheck.setBounds(0, 115, 200, 20);
        add(secondCheck);

        thirdCheck = new JCheckBox("Motherboard");
        thirdCheck.setBounds(0, 155, 200, 20);
        add(thirdCheck);

        fourthCheck = new JCheckBox("Fans");
        fourthCheck.setBounds(0, 195, 200, 20);
        add(fourthCheck);

        //adds a JButton next, sets position, and adds it to the frame
        JButton next = new JButton("Next");
        next.setBounds(200, 200, 75, 50);
        add(next);

        //Creates new instance of the private class and registers events with a handler
        NextHandler checkboxChoice = new NextHandler();
        next.addActionListener(checkboxChoice);

    }

    /**
     * This is a private class that is an event handler for the next button on the GUI
     */
    private class NextHandler implements ActionListener//invokes actionListener interface
    {
        /**
         * Uses if statements to see what checkboxes were selected when the button was pressed
         * If the answer is correct add .5 to users score if incorrect subtract .5 and always add their choice to a string of their choices.
         * When the if statements are done it sets a boolean to false that will make the driver hide the gui
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) //handler for next button getting clicked event
        {
            //adds .5 points for each right answer and deducts .5 for each wrong answer
            //every button selected when next is clicked will be included with the user choice in the end
            if(secondCheck.isSelected())
            {
                questionTwoScore += .5;
                theirChoice += " CPU ";
            }
            if(thirdCheck.isSelected())
            {
                questionTwoScore += .5;
                theirChoice += " Motherboard ";
            }
            if(firstCheck.isSelected()){
                questionTwoScore -= .5;
                theirChoice += " RGB lights ";
            }
            if(fourthCheck.isSelected()){
                questionTwoScore -= .5;
                theirChoice += " Fans ";
            }
            //special case when nothing is selected
            if(!firstCheck.isSelected() && !secondCheck.isSelected() && !thirdCheck.isSelected() && !fourthCheck.isSelected()){
                theirChoice = "Unanswered";
            }
            exitTwo = false; //lets while loop know its done
        }
    }
}

