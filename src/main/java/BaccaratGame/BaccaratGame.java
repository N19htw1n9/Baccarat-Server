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
        this.hand = hand;
    }

    public ArrayList<String> convertCardToString(ArrayList<Card> cards) {
        ArrayList<String> stringCard = new ArrayList<>();
        Card c1 = cards.get(0);
        Card c2 = cards.get(1);
        stringCard.add(c1.getValue() + "" + c1.getSuite().substring(0, 1).toUpperCase());
        stringCard.add(c2.getValue() + "" + c2.getSuite().substring(0, 1).toUpperCase());
        return stringCard;
    }

    public double evaluateWinnings() {
        String winner = BaccaratGameLogic.whoWon(this.bankerHand, this.playerHand);
        if (winner.equals(this.hand))
            return this.currentBet;
        else if (winner.equals("Tie"))
            return 0.0;
        return -1 * this.currentBet;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getBankerHand() {
        return bankerHand;
    }

    public BaccaratDealer getTheDealer() {
        return theDealer;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public double getTotalWinnings() {
        return totalWinnings;
    }

    public String getHand() {
        return hand;
    }
}