import javax.swing.*;
import java.io.IOException;


public class ServerDriver {
    /**
     * main driver that will run Server
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Server application = new Server(); // create server
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runServer(); // run server application
    }
}
