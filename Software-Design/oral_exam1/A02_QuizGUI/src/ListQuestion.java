import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * This class implements a GUI that uses a list for the user to choose their answer
 */
public class ListQuestion extends JFrame //implements ActionListener
{
    /**
     * A list of type String
     */
    private final JList<String> answerChoices;
    /**
     * An int that holds the users score for this question
     */
    public static int questionFourScore;
    /**
     * A string that holds the users answer choice

     */
    public static String theirChoice;
    /**
     * A string array that holds the answer choices that will be on the list
     */
    private static final String[] answers = {"1010111", "7B", "150(Octal)", "7C", "153(Octal)"}; //array of choices for user to select
    /**
     * Boolean for driver to know when to hide the GUI
     */
    public boolean exitFour = true;

    /**
     * creates a list with five potential answers to the question proposed above
     * The list uses a string array, declared at the top of the class, with the potential answers
     * It also adds a button for the user to go to the next page
     */
    public ListQuestion(){
        //Sets default information like title, layout,size, and close operation
        setTitle("Question 4");
        setLayout(null);
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel questionFour = new JLabel("Which choice equals 124(decimal)?");
        questionFour.setBounds(0,0,250,50);
        add(questionFour);

        //Creates jlist with the choice of the strings inside the string array answers
        answerChoices = new JList<>(answers);
        answerChoices.setVisibleRowCount(3);
        answerChoices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //makes it so only one option can be selected
        answerChoices.setBounds(80,50,75,100);
        add(answerChoices);

        //adds jbutton with name next and adds it
        JButton next = new JButton("Next");
        next.setBounds(200,200,75,50);
        add(next);

        //registers the next button with an event handler and creates new instance of the private class
        NextHandler listChoice = new NextHandler();
        next.addActionListener(listChoice);





    }

    /**
     * A private class that acts as an event handler for the button being pressed
     */
    private class NextHandler implements ActionListener{//invokes actionListener interface
                                                    //event handler that is used if the next button is clicked

        /**
         * Action listener that uses if statements to decided if the user has the correct answer selected when the next button was selected
         * Sets its boolean to false when its done checking the if statements so the driver knows to hide it
         * sets the string their choice to what they selected and adds a point to their score if they answered correctly
         * has a try and catch, if the user doesn't select anything it throws null pointer, the catch just treats that as unanswered
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //If user selects nothing it throws a null pointer exception
            try {
                String userChoice = answerChoices.getSelectedValue(); //gets string value of what user selected
                //If they get it right increase score. Always save what choice user made
                if (userChoice.equals("7C")) {
                    questionFourScore = 1;
                    theirChoice = "7C";
                }
                if (userChoice.equals("1010111")) {
                    theirChoice = "1010111";
                }
                if (userChoice.equals("7B")) {
                    theirChoice = "7B";
                }
                if (userChoice.equals("150(Octal)")) {
                    theirChoice = "150(Octal)";
                }
                if (userChoice.equals("153(Octal)")) {
                    theirChoice = "153(Octal)";
                }

            }
            //NullPointException means they selected nothing meaning it was unanswered
            catch (java.lang.NullPointerException a)
            {
                theirChoice = "unanswered";
            }
            exitFour = false;//exits while loop when done
        }
    }

}
