import javax.swing.*;

public class MorseCodeClientDriver {
    public static void main(String[] args)
    {
        MorseCodeClient application; // declare client application

        // if no command line args
        if (args.length == 0)
            application = new MorseCodeClient("127.0.0.1"); // connect to localhost
        else
            application = new MorseCodeClient(args[0]); // use args to connect

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runMorseCodeClient(); // run client application
    }
}
