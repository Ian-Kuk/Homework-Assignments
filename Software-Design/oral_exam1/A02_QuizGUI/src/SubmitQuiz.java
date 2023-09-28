import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that implements a GUI that acts as the anti-cheating policy
 */
public class SubmitQuiz extends JFrame //implements ActionListener
{
    /**
     * checkbox that user will click to confirm they did not cheat
     */
    private final JCheckBox cheat;
    /**
     * Boolean for driver to know when to hide the GUI
     */
    public boolean cheating = true;

    /**
     * creates an anti cheat page that makes the user select a box saying they didn't cheat before they can submit
     * It adds a checkbox and a button to go to the next page
     */
    public SubmitQuiz() {
        //basic information on title,size,layout,and close operation
        setTitle("Cheating Policy");
        setLayout(null);
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creates checkbox telling user to select the box if they did not cheat
        cheat = new JCheckBox("Click this box to certify you did not cheat");
        cheat.setBounds(0,100,350,50);
        add(cheat);

        //Creates jbutton with name submit that will be used to submit the quiz
        JButton next = new JButton("Submit");
        next.setBounds(200,200,100,50);
        add(next);

        //registers submit button with event handler and creates new instance of the private class
        SubmitListener notCheating = new SubmitListener();
        next.addActionListener(notCheating);



    }

    /**
     * Private class that acts an event handler for the submit button.
     */
    private class SubmitListener implements ActionListener{//invokes actionListener interface

        /**
         * action listener that sets the boolean to false hiding the gui only if the anti-cheating checkbox is selected when the user presses next
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) { //handles submit button press action
            if(cheat.isSelected()) { //only if the box is selected will the summary show
                cheating = false;
            }
        }
    }
}
