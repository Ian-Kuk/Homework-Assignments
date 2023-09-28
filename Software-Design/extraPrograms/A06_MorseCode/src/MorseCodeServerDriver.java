import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class MorseCodeServerDriver {
    public static void main(String[] args)
    {
        MorseCodeServer application = new MorseCodeServer(); // create server
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runMorseCodeServer(); // run server application
    }
}
