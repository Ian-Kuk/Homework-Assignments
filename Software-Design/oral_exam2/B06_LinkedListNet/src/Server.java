import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {
    private static ServerSocket server;
    private static  ObjectOutputStream output;
    private static ObjectInputStream input;
    private static  Socket connection;
    private static  JTextArea serverPage;
    private static  JTextField terminal;
    private static List<Object> clientList;

    /**
     * Constructor that creates the GUI for the server and adds an action listener for a text field inside the GUI.
     */
    public Server()
    {
        //Basic gui creation
      super("Server");
      JLabel exitServer = new JLabel("Type 'Close Server' to shutdown server");
      terminal = new JTextField();
      serverPage = new JTextArea();
        terminal.addActionListener(new ActionListener() { //action listener for the text field on the server
            @Override
            public void actionPerformed(ActionEvent event) {
                closeServer(event.getActionCommand());
            }
        });
      add(serverPage);
      add(terminal);
      add(exitServer);
      setLayout(null);
      terminal.setBounds(0,20,200,20);
      exitServer.setBounds(0,0,300,20);
      serverPage.setBounds(0,40,400,360);

      setSize(400,400);
      setVisible(true);
    }

    /**
     * Method that will call several other methods to get the server running. It establishes the server socket with a given port and backlog.
     */
    public void runServer(){
        try
        {
            server = new ServerSocket(23625,10); //creates server socker

            while(true)
            {
                try{
                    getConnection(); //calls getConnection
                    getStreams(); //calls getStreams
                    getListInstructions(); //calls getListInstructions
                }
                catch(EOFException eofException)
                {
                    displayMessage("Server closed \n");
                    closeServer("Close Server");
                }
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }

    }

    /**
     * Method that establishes a connection with the client once they connection. Will update the server text area informing the client has connected, and will create a new list.
     * @throws IOException
     */
    public static void getConnection() throws IOException
    {
            displayMessage("Waiting for client to connect\n");
            connection = server.accept(); //establishes connection
            displayMessage("Client has connected\n");
            clientList = new List<>(); //creates a new linked list
    }

    /**
     * Method that establishes the input and output streams of the connection
     * @throws IOException
     */
    public static void getStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream()); //sets output to the output stream
        output.flush();
        input = new ObjectInputStream(connection.getInputStream()); //sets input to the input stream
        displayMessage("\nGot I/O streams\n");
    }

    /**
     * Method that reads the input given from the server. Depending on the input it will update the list, or just send a message back to the client.
     * @throws IOException
     */
    public static void getListInstructions() throws IOException
    {
        String temp = "";
        while (!temp.equals("Terminate connection"))//client sends Terminate connection when they want to leave
        {
            try
            {
                temp = (String) input.readObject(); //sets string equal to the input
                if(temp.equals("print"))//if client says they want to print server sends back printed, and then the list
                {
                    sendData("p  rinted," + clientList.print());
                }
                if(temp.equals("Terminate connection")) //if client wants to terminate display the message and send back a message that will make client exit its loop
                {
                    displayMessage("Client has left");
                    sendData("Client Terminated");
                }
                String[] words = temp.split(","); //splits message into array
                if (words[0].equalsIgnoreCase("insert")) //if client wants to insert
                {

                    if (Integer.parseInt(words[1]) > clientList.listSize() + 1) //makes sure the node isnt bigger than the list, it can be 1 node bigger than the list
                    {
                        sendData("ins tb");
                    }

                    if (Integer.parseInt(words[1]) <= 0) //makes sure they arent inserting to a node to small that doesnt exist
                    {
                        sendData("ins nv");
                    }

                    if (clientList.listSize()  >= 0 && Integer.parseInt(words[1]) > 0 && Integer.parseInt(words[1]) <= clientList.listSize() + 1) //if the node is proper add it to the list
                    {
                        clientList.addNode(Integer.parseInt(words[1]), Integer.parseInt(words[2]), clientList.listSize());
                        sendData("ac");
                    }

                }

                if (words[0].equalsIgnoreCase("delete"))
                {
                    if (clientList.listSize() == 0) //if list doesnt exit
                    {
                        sendData("del nl");
                    }
                    if (clientList.listSize() < Integer.parseInt(words[1]) && clientList.listSize() != 0 || Integer.parseInt(words[1]) <= 0) //if the list is smaller than want node and list isn't zero or if node its trying to delete is less than 0
                    {
                        sendData("del nv");
                    }
                    if (clientList.listSize() > 0 && clientList.listSize() >= Integer.parseInt(words[1]) && Integer.parseInt(words[1]) > 0)
                    {
                        clientList.removeNode(Integer.parseInt(words[1]), clientList.listSize());

                        sendData("ac");
                    }
                }
            }
            catch (ClassNotFoundException classNotFoundException)
            {
                displayMessage("There was trouble receiving info from the client\n");
            }

        }
    }

    /**
     * Method that will take the param and write it to its output for the client to read
     * @param listChanges a string telling the client what changed
     */
    private static void sendData(String listChanges)
    {
        try
        {
            output.writeObject(listChanges); //writes the string
            output.flush(); //sends the string
        }
        catch (IOException ioException)
        {
            displayMessage("There was a problem sending the changes\n");
        }
    }

    /**
     * Method that will close the server if the param is equal to a specific string that would be typed in the text field.
     * @param exit a string that will tell the server close
     */
    private static void closeServer(String exit)
    {
        if(exit.equalsIgnoreCase("Close Server")) { //if the server is to be closed, close everything a display that its closing
            try {
                displayMessage("Server shutting down");
                terminal.setText("");
                output.close();
                input.close();
                server.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            catch (NullPointerException nullPointerException)
            {
                displayMessage("");
            }
        }
    }

    /**
     * Method to update the text area on the GUI
     * @param messageToDisplay the string that is to be added to the text area
     */
    private static  void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        serverPage.append(messageToDisplay + "\n");
                    }
                }
        );
    }
}
