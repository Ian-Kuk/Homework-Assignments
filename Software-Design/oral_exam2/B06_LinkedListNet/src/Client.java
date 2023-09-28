import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends JFrame
{
    private static ObjectOutputStream output;
    private  static ObjectInputStream input;
    private static Socket client;
    private static String server;
    private static JTextArea listOut;
    private static JTextField userChoice;
    private static String listInfo = "";

    /**
     * Constructor that sets the GUI interface for the client. It sets all the instruction labels, and all the text fields as well as having an action listener if the user types into the text field.
     * @param host String that is the hosts address. The server will be equal to this string
     */
    public Client(String host)
    {
        server = host;
        JLabel instructionsOne = new JLabel("Type Insert followed by the node placement and value to insert an element EX(insert,1,2)"); //basic intstructions
        JLabel instructionsTwo = new JLabel("Type Delete followed by the node placement to delete an element EX(delete,1)");
        JLabel instructionsFour = new JLabel("Type Print to print the list");
        JLabel instructionsFive = new JLabel("Type Exit to disconnect");
        JLabel typeInstructions = new JLabel("What would like to do?");
        userChoice = new JTextField(); //where the user types what they want
        listOut = new JTextArea();

        userChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                listDuty(event.getActionCommand()); //sends the contents of the field to method listDuty
                userChoice.setText("");//resets the text field to blank
            }
        });
        //Basic set up of the GUI
        setLayout(null);
        setSize(800,400);

        instructionsOne.setBounds(20,15,800,15);
        add(instructionsOne);

        instructionsTwo.setBounds(20,35,600,15);
        add(instructionsTwo);

        instructionsFour.setBounds(20,55,380,15);
        add(instructionsFour);

        instructionsFive.setBounds(20,75,380,15);
        add(instructionsFive);

        typeInstructions.setBounds(20,100,380,20);
        add(typeInstructions);

        userChoice.setBounds(20,140,150,20);
        add(userChoice);

        JScrollPane scroll = new JScrollPane(listOut);
        scroll.setBounds(20,190,350,160);
        add(scroll);

        setVisible(true);


    }

    /**
     * Beginning method that calls getConnection getStreams, and getListInfo. Calling these methods run the program. When try catch is finished it calls closeClient to end the client
     */
    public void runClient()
    {
        try
        {
            //Calls various methods to run the program
            getConnection();
            getStreams();
            getListInfo();
        }
        catch(EOFException eofException)
        {
            displayMessage("Terminated\n");

        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            //when the methods are done running it closes the client
            closeClient();
        }
    }

    /**
     * Method that creates a new socket with the given server and port. Tells user that it is waiting for client to connect, once client connects it informs them the client has connected.
     * @throws IOException
     */
    private static void getConnection() throws IOException
    {
        displayMessage("Attempting to connect to server\n");
        client = new Socket(InetAddress.getByName(server), 23625); //creation of socket with given port and socket
        displayMessage("Connected to " + client.getInetAddress().getHostName() + "\n");
    }

    /**
     * Method that assigns the clients input and output streams to given variables
     * @throws IOException
     */
    private static void getStreams() throws IOException
    {
        output = new ObjectOutputStream(client.getOutputStream()); //sets output to the clients output stream
        output.flush(); //flushes output to make sure no residual data
        input = new ObjectInputStream(client.getInputStream()); //sets input to clients input stream

    }

    /**
     * Method that sets a String equal to the value of its input stream. Depending on what the string is the client will print out several messages
     * @throws IOException
     */
    private static void getListInfo()  throws IOException
    {
        while(!listInfo.equals("Client Terminated"))//when the client wants to exit the sever will send the string "Client Terminated" making it exit the loop
        {
            try
            {
                listInfo =(String) input.readObject();
                if(listInfo.length() >= 8 && listInfo.charAt(7) == ',') //if the message the server sent was Printed, followed by the list, it will print the list
                {
                    String[] words = listInfo.split(",");
                    displayMessage(words[1]);
                }
                if(listInfo.equals("ac")) //Server sends ac(all clear) when it properly does the duty
                {
                    displayMessage("The list has been update");
                }
                if(listInfo.equals("ins tb")) //Server send ins tb(insert to big) when client tries to insert to a node bigger than list.
                {
                    displayMessage("The node your tried to add is bigger than the list");
                }
                if(listInfo.equals("ins nv"))//Server send ins nv (insert negative number) when client tries to insert to a negative node
                {
                    displayMessage("You can only enter positive nodes");
                }
                if(listInfo.equals("del nl")) //Server sends del nl (delete no list) when there is no list to delete a node from
                {
                    displayMessage("The list is empty you cannot delete anything");
                }
                if(listInfo.equals("del nv"))//server send del nv (delete no value) when the node client is trying to delete doesnt exist
                {
                    displayMessage("You are trying to delete a node that does not exist.");
                }
            }
            catch (ClassNotFoundException classNotFoundException)
            {
                displayMessage("There was a problem retrieving your list information\n");
            }
        }
    }

    /**
     * Method that takes the string that was in the text area from the constructors action listener and will send various things to the server depending on what the user typed into the text area
     * @param action String containing the action the user wants to preform to the list
     */
    private static void listDuty(String action)
    {
        if(action.equalsIgnoreCase("Exit")) //Sends terminate connection to server which will exit a while loop on servers side when clients wants to leave
        {
            sendMessage("Terminate connection");
        }
        if(action.equalsIgnoreCase("Print")) //Sends print message to server telling it to print the list
        {
            sendMessage("print");
        }
        if(action.length() >= 7 && action.charAt(6) == ',')//insert, and delete, are both minimum 7 long with a , at 6 so this makes sure they are following basic formatiing
        {
            String[] words = action.split(",");//splits message into array
            if(words[0].equalsIgnoreCase("insert"))
            {
                if(words.length == 3)//array should be 3 long if user followed proper formatting
                {
                    if(!words[1].equalsIgnoreCase("") && !words[2].equalsIgnoreCase("")) { //sends the array to the server to be handled
                        try{
                            Double.parseDouble(words[1]); //makes sure the numbers are numbers
                            Double.parseDouble(words[2]);
                            sendMessage(words[0] + "," + words[1] + "," + words[2]);
                        }
                        catch (NumberFormatException numberFormatException)
                        {
                            displayMessage("You have to include two numbers seperated by commas, try again");
                        }

                    }
                    else{
                        displayMessage("You have to include two numbers seperated by commas, try again");
                    }
                }
                if(words.length ==2 || words.length ==1 ) //if the array isn't 3 long client didnt follow proper formatting
                {
                    displayMessage("You have to include two numbers seperated by commas, try again");
                }
            }
            if(words[0].equalsIgnoreCase("delete"))
            {
                if(words.length == 2) //makes sure client follow proper formatting
                {
                    sendMessage(words[0] + "," + words[1]);
                }
                if(words.length == 1) //if client did follow proper formatting
                {
                    displayMessage("You must include a node to delete");
                }
            }

        }
        if(!action.equalsIgnoreCase("Exit") && !action.equalsIgnoreCase("Print") && action.length() <= 7) //if client didnt follow proper formatting or didnt chose a correction option
        {
            displayMessage("Formatting incorrect try again");
        }

    }

    /**
     * Method that writes a string of what the server should do to its output. It flushes it to its output stream and the server will then read what it needs to do.
     * @param listDuties a string that the server will read and know what to do with
     */
    private static void sendMessage(String listDuties)
    {
        try
        {
            output.writeObject(listDuties); //writes the string of what the server needs to do
            output.flush(); //sends it to server
        }
        catch (IOException ioException)
        {
            displayMessage("There was a problem sending your instructions to the server \n");
        }

    }

    /**
     * Method that takes in a string and updates a text area on the gui woth the new string
     * @param listChanges a string of what needs to be added to the text area
     */
    private static void displayMessage(String listChanges)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // updates displayArea
                    {
                        listOut.append(listChanges + "\n"); // append message
                    }
                }
        );
    }

    /**
     * A method that is called when the client needs to close. It closes in input and output streams and closes the client
     */
    private static void closeClient()
    {
        displayMessage("\nClosing client");
        try
        {
            output.close(); //closes output
            input.close(); //closes input
            client.close(); //closes client connection
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }
}
