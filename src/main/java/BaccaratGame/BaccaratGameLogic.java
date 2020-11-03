package BaccaratGame;

import java.util.ArrayList;

public class BaccaratGameLogic {
    public static String whoWon(ArrayList<Card> bankerHand, ArrayList<Card> playerHand) {
        int bankerBaccaratHand = handTotal(bankerHand);
        int playerBaccaratHand = handTotal(playerHand);

        int playerDiff = 9 - playerBaccaratHand;
        int bankerDiff = 9 - bankerBaccaratHand;

        // The diff that is the smallest is the winner
        if (playerDiff < bankerDiff)
            return "Player";
        else if (bankerDiff < playerDiff)
            return "Banker";
        return "Draw";
    }

    public static int handTotal(ArrayList<Card> hand) {
        int value = 0;
        for (Card c : hand)
            value += c.getValue();

        if (value < 10)
            return value;
        return value % 10;
    }

    public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
        int bankerTotal = handTotal(hand);
        if (bankerTotal == 0 && playerCard != null)
            return true;
        return false;
    }

    public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        int playerTotal = handTotal(hand);
        return playerTotal <= 5;
    }
}
