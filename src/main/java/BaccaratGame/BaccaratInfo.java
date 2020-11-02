package BaccaratGame;

import java.io.Serializable;

public class BaccaratInfo implements Serializable {
    public int bid;
    public String hand;

    public BaccaratInfo(int bid, String hand) {
        this.bid = bid;
        this.hand = hand;
    }
}