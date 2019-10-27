import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;

    public static void main(String[] args) throws IOException {

        serverSocket = new ServerSocket(8080);

        try {
            // Waiting for connection
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Show client socket address
            System.out.println("new connection from: " + clientSocket.getRemoteSocketAddress().toString());
            String inputLine;

            // Read, Modify, Return message
            while ((inputLine = in.readLine()) != null) {
                System.out.println("have read from client: " + inputLine);
                out.println(Integer.valueOf(inputLine) + 1);
                System.out.println("have wrote to client: " + (Integer.valueOf(inputLine) + 1));
            }

            System.out.println("client has disconnected...");

        } catch (Throwable e) {
            System.out.println("\t\tError:\t\t" + e.getMessage());
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
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
