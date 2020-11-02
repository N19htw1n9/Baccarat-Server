package BaccaratGame;

import java.util.ArrayList;

public class BaccaratGame {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> bankerHand;
    private BaccaratDealer theDealer;
    private double currentBet;
    private double totalWinnings;
    private String hand;

    public BaccaratGame(double currentBet, String hand) {
        this.theDealer = new BaccaratDealer();
        this.theDealer.generateDeck();
        this.playerHand = this.theDealer.dealHand();
        this.bankerHand = this.theDealer.dealHand();
        this.currentBet = currentBet;
    }

    public double evaluateWinnings() {
        String winner = BaccaratGameLogic.whoWon(this.bankerHand, this.playerHand);
        return 0.0;
    }
}