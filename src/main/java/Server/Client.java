package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Client clientOb = new Client();
        try {
            clientOb.clientCode();
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public void clientCode() throws IOException, ClassNotFoundException {

        Socket socketClient = new Socket("127.0.0.1", 5555);
        System.out.println("Client: " + "Connection Established");

        System.out
                .println("This is the remote address client is connected to: " + socketClient.getRemoteSocketAddress());
        System.out.println("And the remote port: " + socketClient.getPort());

        Scanner scanner = new Scanner(System.in);
        ObjectOutputStream out = new ObjectOutputStream(socketClient.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socketClient.getInputStream());
        socketClient.setTcpNoDelay(true);

        String req = "";

        while (true) {
            req = scanner.next();

            if (req.equals("q"))
                break;

            out.writeObject(req);
            String res = (String) in.readObject();

            System.out.println("Request: " + req.toString());
            System.out.println("Server Response: " + res + "\n");
        }
        scanner.close();
        socketClient.close();
    }
}