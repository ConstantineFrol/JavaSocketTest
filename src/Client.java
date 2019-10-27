import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;

    public static void main(String[] args) throws IOException {

        try {

            // Establish a connection
            socket = new Socket("127.0.0.1", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;

            // Sending message to server
            out.println(1);
            System.out.println("have wrote to server: 1");

            // Waiting for response from server
            while ((inputLine = in.readLine()) != null) {
                System.out.println("have read from server: " + inputLine);
                int number = Integer.valueOf(inputLine);

                // Communication time
                if (number >= 10) {
                    break;
                }

                number++;

                out.println(number);
                System.out.println("have wrote to server: " + number);
                Thread.sleep(2000);
            }

            System.out.println("disconnecting...");

        } catch (Throwable e) {
            System.out.println("\t\tError:\t\t" + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }
}
