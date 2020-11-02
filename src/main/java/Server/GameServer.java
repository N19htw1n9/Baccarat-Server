package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import BaccaratGame.BaccaratGame;
import BaccaratGame.BaccaratGameLogic;
import BaccaratGame.BaccaratInfo;

import java.lang.Thread;

public class GameServer {
    int count = 1;
    int port = 5555;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    ServerThread server;
    private Consumer<Serializable> callback;

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

                callback.accept("Server is running on port " + port);
                callback.accept("Waiting for client to connect...");

                while (true) {
                    ClientThread client = new ClientThread(this.socket.accept(), count);
                    callback.accept("New client connected:");
                    callback.accept("\tClient #" + count);
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
        int id;
        ObjectOutputStream out;
        ObjectInputStream in;

        public ClientThread(Socket connection, int id) {
            this.connection = connection;
            this.id = id;
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
                callback.accept("Client #" + id);
                callback.accept("\tError: Could not open streams");
            }

            while (!this.connection.isClosed()) {
                try {
                    BaccaratInfo req = (BaccaratInfo) in.readObject();

                    callback.accept("Client #" + this.id + " sent a request: ");
                    callback.accept("\tBid: " + req.bid + "\tHand: " + req.hand + "\n");

                    BaccaratGame game = new BaccaratGame(req.bid, req.hand);

                    BaccaratInfo res = new BaccaratInfo(req.bid, req.hand);
                    // Convert card to map with image
                    // Card -> String: Card.value + Card.suit.subs
                    // [Card, Card] -> [1D, 12H]
                    res.bankerHand = game.convertCardToString(game.getBankerHand());
                    res.playerHand = game.convertCardToString(game.getPlayerHand());
                    // Set winner
                    res.winner = BaccaratGameLogic.whoWon(game.getBankerHand(), game.getPlayerHand());
                    res.winnings = game.evaluateWinnings();
                    out.writeObject(res);

                    callback.accept("\tResponse sent!");
                } catch (Exception e) {
                    callback.accept("Error: Could not fetch request from client #" + this.id);
                    try {
                        this.connection.close();
                    } catch (IOException e1) {
                        callback.accept("Error: Could not close connection for client #" + this.id);
                    }
                }
            }
            callback.accept("Connection closed for client #" + this.id);
        }
    }
}