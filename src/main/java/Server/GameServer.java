package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import Server.BaccaratGame.BaccaratInfo;

import java.lang.Thread;

public class GameServer {
    int count = 1;
    int port = 5555;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    ServerThread server;
    private Consumer<Serializable> callback;

    public static void main(String[] args) {
        new GameServer(null, 5555);
    }

    public GameServer(Consumer<Serializable> call, int port) {
        this.callback = call;
        this.port = port;
        server = new ServerThread();
        server.start();
    }

    class ServerThread extends Thread {
        ServerSocket socket;

        public void run() {
            try {
                this.socket = new ServerSocket(port);

                System.out.println("Server is waiting for a client!");

                while (true) {
                    ClientThread client = new ClientThread(this.socket.accept(), count);
                    System.out.println("New client connected...");
                    clients.add(client);
                    client.start();

                    count++;
                }
            } catch (Exception e) {
            }
        }

        public void close() throws IOException {
            this.socket.close();
        }
    }

    class ClientThread extends Thread {
        Socket connection;
        int count;
        ObjectOutputStream out;
        ObjectInputStream in;

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
                this.out = new ObjectOutputStream(this.connection.getOutputStream());
                this.in = new ObjectInputStream(this.connection.getInputStream());
                this.connection.setTcpNoDelay(true);
            } catch (Exception e) {
                System.out.println("Streams not open");
            }

            while (!this.connection.isClosed()) {
                try {
                    BaccaratInfo req = (BaccaratInfo) in.readObject();

                    System.out.println("Client #" + this.count + " sent:");
                    System.out.printf("\tBid: %d\nHand: %s\n\n", req.bid, req.hand);

                    // BaccaratInfo res = req;
                    out.writeObject(req);
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.printf("Something went wrong... Could not fetch request from client #%d\n", this.count);
                    try {
                        this.connection.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            System.out.println("Connection closed");
        }
    }
}