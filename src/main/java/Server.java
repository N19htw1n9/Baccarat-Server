import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.lang.Thread;

public class Server {
    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    ServerThread server;
    private Consumer<Serializable> callback;

    public Server(Consumer<Serializable> call) {
        this.callback = call;
        server = new ServerThread();
        server.start();
    }

    public static void main(String[] args) {
        Server s = new Server(null);
    }

    class ServerThread extends Thread {
        public void run() {
            int port = 5555;
            try {
                ServerSocket socket = new ServerSocket(port);

                System.out.println("Server is waiting for a client!");

                while (true) {
                    ClientThread client = new ClientThread(socket.accept(), count);
                    System.out.println("New client connected...");
                    clients.add(client);
                    client.start();
                    // callback.accept("Client #" + count + " added to pool");

                    count++;
                }
            } catch (Exception e) {
                // callback.accept("Server socket did not launch");
            }
        }
    }

    class ClientThread extends Thread {
        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        public ClientThread(Socket connection, int count) {
            this.connection = connection;
            this.count = count;
        }

        public void updateClients(String message) {
            for (ClientThread client : clients) {
                try {
                    client.out.writeObject(message);
                } catch (Exception e) {
                }
            }
        }

        public void run() {
            try {
                this.in = new ObjectInputStream(this.connection.getInputStream());
                this.out = new ObjectOutputStream(this.connection.getOutputStream());
                this.connection.setTcpNoDelay(true);
            } catch (Exception e) {
                System.out.println("Streams not open");
            }

            this.updateClients("New client on server, client #" + this.count);

            while (true) {
                try {
                    String req = (String) in.readObject();
                    // callback.accept("client: " + this.count + " sent: " + req);
                    this.out.writeObject("Message recieved: " + req);
                    updateClients("client #" + this.count + " said: " + req);
                } catch (Exception e) {
                }
            }
        }
    }
}