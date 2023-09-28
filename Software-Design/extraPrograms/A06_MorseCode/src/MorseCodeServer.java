import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class MorseCodeServer extends JFrame {
    private ServerSocket server;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;
    private JTextArea serverPage;
    private String[] possibleCodes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..",".----","..---","...--","....-",".....","-....","--...","---..","----.","-----"};
    private String[] englishLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","0"};
    //private char[] englishLetters =  {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};
    public MorseCodeServer(){
        super ("Morse Code Server");
        serverPage = new JTextArea();
        add(serverPage);
        setSize(400,200);
        setVisible(true);
    }
    public void runMorseCodeServer(){
        try
        {
            server = new ServerSocket(23625,2);
            while(true){
                try{
                    getConnection();
                    getStreams();
                    getClientMessage();

                }
                catch(EOFException eofException)
                {
                    displayMessage("\nServer closed");
                }
                finally
                {
                    closeServer();
                }
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }

    }

    public void getConnection() throws IOException
    {
       displayMessage("Waiting for client to connect");
       connection = server.accept();
       displayMessage("Client connected");
    }

    public void getStreams()throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
    }

    public void getClientMessage() throws IOException
    {
        String text = "";
        output.writeObject("Connection successful");
        output.flush();
        while (!text.equals("<User Left>"))
        {
            try
            {

                text = (String) input.readObject();
                sendData(text);
                translateMessage(text);
            }
            catch (ClassNotFoundException classNotFoundException)
            {
                displayMessage("Error occurred while receiving the message");
            }
        }
    }

    private void closeServer()
    {
        displayMessage("\nClosing server");
        try
        {
            output.close();
            input.close();
            connection.close();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    private void sendData(String translated)
    {
        try
        {
            output.writeObject("Your message is " + translated);
            output.flush();
        }
        catch (IOException ioException)
        {
            displayMessage("\n There was a problem sending the translated text");
        }
    }

    private void displayMessage(final String messageToDisplay)
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

    private void translateMessage(String message) throws IOException
    {
        String sentence = "";
        String[] words;
        boolean improperCharacter = false;
        if (message.charAt(0) == '.' || message.charAt(0) == '-')
        {
            words = message.split("   ");
            for(String word: words){
                String[] letters = word.split(" ");
                String indivLetters = "";
                for(String letter: letters){
                    int indexPlace = Arrays.asList(possibleCodes).indexOf(letter);
                    if(indexPlace == -1){
                        improperMessage();
                        improperCharacter = true;
                        break;
                    }
                    indivLetters = indivLetters + englishLetters[indexPlace];
                }
                sentence = sentence + indivLetters + " ";
            }
            if(!improperCharacter){
            output.writeObject("The message in english is: " + sentence);}
        }
        else
        {
            words = message.split(" ");
            for(String word: words){
                String indivLetters = "";
                for(int i = 0; i < word.length(); i++){
                    int indexPlace = Arrays.asList(englishLetters).indexOf(String.valueOf(word.charAt(i)).toUpperCase());
                    if(indexPlace == -1){
                        improperMessage();
                        improperCharacter = true;
                        break;
                    }
                    indivLetters = indivLetters + possibleCodes[indexPlace] + " ";
                }
                sentence = sentence + indivLetters + "   ";
            }
            if(!improperCharacter){
            output.writeObject("The message in Morse Code is: " + sentence);}
        }
        output.flush();
    }
    private void improperMessage() throws IOException {
        output.writeObject("You entered a character that cannot be translated in morse code");
    }
}
