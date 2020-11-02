package BaccaratGame;

import java.io.Serializable;

public class BaccaratInfo implements Serializable {
    public double bid;
    public String hand;

    public BaccaratInfo(double bid, String hand) {
        this.bid = bid;
        this.hand = hand;
    }
}