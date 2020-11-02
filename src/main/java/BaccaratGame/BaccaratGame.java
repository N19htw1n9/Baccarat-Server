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

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public ArrayList<Card> getBankerHand() {
        return bankerHand;
    }

    public void setBankerHand(ArrayList<Card> bankerHand) {
        this.bankerHand = bankerHand;
    }

    public BaccaratDealer getTheDealer() {
        return theDealer;
    }

    public void setTheDealer(BaccaratDealer theDealer) {
        this.theDealer = theDealer;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(double currentBet) {
        this.currentBet = currentBet;
    }

    public double getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(double totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

}