import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class MorseCodeClient extends JFrame{
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final JTextField userMessage;
    private Socket client;
    private String message = "";
    private final JTextArea clientPage;
    private final String server;
    public MorseCodeClient(String host){
        super ("Morse Code Client");
        server = host;
        userMessage = new JTextField("Please enter english or morse code message");

        userMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                sendMessage(event.getActionCommand());
                userMessage.setText("Please enter english or morse code message");

            }
        });

        setLayout(null);
        setSize(400,400);
        userMessage.setBounds(50,50, 300 , 20);
        add(userMessage);
        clientPage = new JTextArea();
        clientPage.setBounds(50,100,300,100);
        JScrollPane scroll = new JScrollPane(clientPage);
        scroll.setBounds(50,100,300,100);
        add(scroll);

        setVisible(true);
    }
    public void runMorseCodeClient(){
        try{
            getConnection();
            getStreams();
            getServerMessage();
        }
        /*catch(EOFException eofException)
        {
            displayMessage(" Terminated");
        }*/
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally{
            displayMessage("\n Closing client");
            try {
                output.close();
                input.close();
                client.close();
            }
            catch(IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
    }

    private void getConnection() throws IOException
    {
        displayMessage("Trying to connect\n");
        client = new Socket(InetAddress.getByName(server), 23625);
        displayMessage("Connected to " + client.getInetAddress().getHostName());
    }

    private void getStreams() throws IOException
    {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
    }

    private void getServerMessage() throws IOException
    {
        while (!message.equals("<Server Shutdown>"))
        {
           try
           {
               message = (String) input.readObject();
               displayMessage(message);
           }
           catch (ClassNotFoundException classNotFoundException)
           {
               displayMessage("There was a problem with the translation");
           }
        }
    }

    private void sendMessage(String message)
    {
        try
        {
            output.writeObject(message);
            output.flush();
        }
        catch (IOException ioException)
        {
            displayMessage("\n There was a problem writing your message");
        }
        catch (NullPointerException nullPointerException)
        {
            System.out.println("got here");
        }

    }
    private void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // updates displayArea
                    {
                        clientPage.append(messageToDisplay + "\n"); // append message
                    }
                }
        );
    }
}
