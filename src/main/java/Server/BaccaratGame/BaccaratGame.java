package Server.BaccaratGame;

import java.util.ArrayList;

public class BaccaratGame {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> bankerHand;
    private BaccaratDealer theDealer;
    private double currentBet;
    private double totalWinnings;

    public BaccaratGame() {
        theDealer = new BaccaratDealer();
    }

    public double evaluateWinnings() {
        return 0.0;
    }
}