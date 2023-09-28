import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage extends JFrame {
public boolean exit = true;
public static String enteredStudentName;
private final JTextField studentName;
    public FrontPage()
    {
        //sets default information like title,layout,size, and close operation
        setTitle("Question 2");
        setLayout(null);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel messageToStudent = new JLabel("Please enter your name below");
        messageToStudent.setBounds(50,70,200,30);
        add(messageToStudent);

        studentName = new JTextField();
        studentName.setBounds(40,110,200,40);
        add(studentName);

        //Creates next button sets its position and adds it to frame
        JButton nextButton = new JButton("Start");
        nextButton.setBounds(200, 200, 75, 50);
        add(nextButton);

        //creates new instance of the private class and uses add action listener to register the event handler for each component
        NextHandler userName = new NextHandler();
        nextButton.addActionListener(userName);
    }
    private class NextHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!studentName.getText().equals(""))
            {
                enteredStudentName = studentName.getText();
                exit = false;
            }

        }
    }

}
