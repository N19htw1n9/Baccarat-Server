package BaccaratGame;

import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable {
    public double bid;
    public String hand;

    public ArrayList<String> playerHand = new ArrayList<>(); // ["1C", "12H"]
    public ArrayList<String> bankerHand = new ArrayList<>(); // ["3D", "4S"]

    public String winner = "";

    public double winnings = 0.0;

    public BaccaratInfo(double bid, String hand) {
        this.bid = bid;
        this.hand = hand;
    }
}