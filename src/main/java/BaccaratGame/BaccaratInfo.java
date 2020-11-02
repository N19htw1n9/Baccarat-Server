package BaccaratGame;

import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable {
    public double bid;
    public String hand;

    public ArrayList<String> playerHand; // ["1C", "12H"]
    public ArrayList<String> bankerHand; // ["3D", "4S"]

    public String winner;

    public double winnings;

    public BaccaratInfo(double bid, String hand) {
        this.bid = bid;
        this.hand = hand;
    }
}