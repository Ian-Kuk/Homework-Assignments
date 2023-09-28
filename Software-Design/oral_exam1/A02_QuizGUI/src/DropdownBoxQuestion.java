import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates a GUI for the quiz that utilizes combo boxes for the user to chose their answer.
 */
public class DropdownBoxQuestion extends JFrame // implements ActionListener
{
    /**
     * Boolean for driver to know when to hide the GUI
     */
    public boolean exitThree = true;
    /**
     * Combobox of type string to put on GUI
     */
    private final JComboBox<String> answerChoices;
    /**
     * String array of answers to be in the combo box
     */
    private static final String[] choices = {" ","Hexadecimal","Binary","Decimal", "Octal"}; //String array of possible choices
    /**
     * String of the answer the user chose
     */
    public static String theirChoice;
    /**
     * int of the score the user got for this question
     */
    public static int questionThreeScore;

    /**
     * Creates a dropdown box that holds four potential answers to the question asked above the dropdown box
     * creates a button for the user to submit their answer and go to the next page
     */
    public DropdownBoxQuestion(){

        //sets default information like title,layout,size,and close operation
        setTitle("Question 3");
        setLayout(null);
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creates jlabel to give user the question and adds it to frame
        JLabel questionThree = new JLabel("What system do humans use to count?");
        questionThree.setBounds(30,0,300,25);
        add(questionThree);

        //creates a new combobox with the choice being the strings inside the string array choices, and adds it
        answerChoices = new JComboBox<>(choices);
        answerChoices.setMaximumRowCount(5);
        answerChoices.setBounds(25,30,225,50);
        add(answerChoices);

        //creates jbutton labeled Next and adds it
        JButton next = new JButton("Next");
        next.setBounds(200,200,75,50);
        add(next);

        //registers the button event with a event handler
        NextHandler dropdownChoice = new NextHandler();
        next.addActionListener(dropdownChoice);







    }
    /**
     * This is a private class that is an event handler for the next button on the GUI
     */
    private class NextHandler implements ActionListener//invokes actionListener interface
    {
        /**
         * An action listener that uses if statements to increase points if the user has the right option selected when the button was pressed
         * It makes the class string theirChoice to whatever they chose right or wrong, but only adds a point if they chose right.
         * At the end changes a boolean to make driver hide the program
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) //handler for next button getting clicked event
        {
            String userChoice =(String) answerChoices.getSelectedItem(); //gets the string value of what the user selected
            //checks if to add point, always adds the name of what they selected
            assert userChoice != null; //makes program believe userchoice isn't null
            if(userChoice.equals("Decimal")){
                theirChoice = "Decimal";
                questionThreeScore = 1;
            }
            if(userChoice.equals("Hexadecimal")){
                theirChoice = "Hexadecimal";
            }
            if(userChoice.equals("Binary")){
                theirChoice = "Binary";
            }
            if(userChoice.equals("Octal")){
                theirChoice = "Octal";
            }
            if(userChoice.equals(" ")){ //blank option is default on list, it means they never selected an answer
                theirChoice = "Unanswered";
            }
            exitThree = false; //lets while loop know its done

        }
    }
}
