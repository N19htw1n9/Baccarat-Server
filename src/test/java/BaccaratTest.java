import BaccaratGame.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.TreeSet;

public class BaccaratTest
{
    private Card c1, c2, c3, bdC1, bglHand1C1, bglHand1C2, bglHand2C1, bglHand2C2,
            bglHand3C1, bglHand3C2, bglHand4C1, bglHand4C2, nullCard, bglHand5C1, bglHand5C2;
    private BaccaratDealer bd;
    private BaccaratGameLogic bgl;
    private BaccaratInfo bi;
    private BaccaratGame bg, bg2;
    private ArrayList<Card> bglHand1, bglHand2, bglHand3, bglHand4, bglHand5;
    private String[] suitsArr;
    private ArrayList<Card> bdCardList;
    private ArrayList<String> bgCardToString1;
    private ArrayList<String> bgCardToString2;

    @Before
    public void init()
    {
        c1 = new Card("clubs", 1);
        c2 = new Card("hearts", 3);
        c3 = new Card("spades", 9);

        bd = new BaccaratDealer();
        bgl = new BaccaratGameLogic();
        suitsArr = new String[] { "clubs", "diamonds", "hearts", "spades" };

        bglHand1C1 = new Card("clubs", 2);
        bglHand1C2 = new Card("diamonds", 3);
        bglHand2C1 = new Card("hearts", 11);
        bglHand2C2 = new Card("spades", 1);
        bglHand3C1 = new Card("hearts", 3);
        bglHand3C2 = new Card("clubs", 2);
        bglHand4C1 = new Card("spades", 0);
        bglHand4C2 = new Card("diamonds", 0);
        bglHand5C1 = new Card("hearts", 3);
        bglHand5C2 = new Card("clubs", 4);
        nullCard = null;

        bglHand1 = new ArrayList<>();
        bglHand1.add(bglHand1C1);
        bglHand1.add(bglHand1C2);

        bglHand2 = new ArrayList<>();
        bglHand2.add(bglHand2C1);
        bglHand2.add(bglHand2C2);

        bglHand3 = new ArrayList<>();
        bglHand3.add(bglHand3C1);
        bglHand3.add(bglHand3C2);

        bglHand4 = new ArrayList<>();
        bglHand4.add(bglHand4C1);
        bglHand4.add(bglHand4C2);

        bglHand5 = new ArrayList<>();
        bglHand5.add(bglHand5C1);
        bglHand5.add(bglHand5C2);

        bi = new BaccaratInfo(100, "Banker");

        bg = new BaccaratGame(1000, "Player");
        bg2 = new BaccaratGame(10, "S");
    }

    @Test
    public void CardTest()
    {
        //Constructor test
        assertEquals("clubs", c1.getSuite());
        assertEquals(1, c1.getValue());

        //setSuite() test
        c1.setSuite("");
        assertEquals("", c1.getSuite());
        c1.setSuite("hearts");
        assertEquals("hearts", c1.getSuite());

        //setValue() test
        c1.setValue(11);
        assertEquals(11, c1.getValue());
        c1.setValue(3);
        assertEquals(3, c1.getValue());

        //equals() test
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
    }

    @Test
    public void BaccaratDealerTest()
    {
        //Constructor test
        assertTrue(bd.deck.isEmpty());
        assertEquals(0, bd.deckSize()); //deckSize() test
        for(int k = 0; k < suitsArr.length; k++)
            assertEquals(suitsArr[k], bd.getSuits()[k]);

        //generateDeck() test
        bd.generateDeck();
        assertEquals(52, bd.deckSize()); //deckSize() test
        Set<Card> duplicatesCheck = new HashSet<Card>(bd.deck);
        assertEquals(bd.deck.size(), duplicatesCheck.size());

        int clubsCount = 0;
        int diamondsCount = 0;
        int heartsCount = 0;
        int spadesCount = 0;
        int valArr[] = new int[13];
        for(int k = 0; k < bd.deckSize(); k++)
        {
            if(bd.deck.get(k).getSuite() == "clubs")
                clubsCount++;
            else if(bd.deck.get(k).getSuite() == "diamonds")
                diamondsCount++;
            else if(bd.deck.get(k).getSuite() == "hearts")
                heartsCount++;
            else if(bd.deck.get(k).getSuite() == "spades")
                spadesCount++;
        }

        assertEquals(clubsCount, 13);
        assertEquals(clubsCount, 13);
        assertEquals(diamondsCount, 13);
        assertEquals(heartsCount, 13);
        assertEquals(spadesCount, 13);

        for(int k = 0; k < bd.deckSize(); k++)
        {
            assertTrue((bd.deck.get(k).getValue() >= 1) && (bd.deck.get(k).getValue() <= 13));
        }

        //drawOne() test
        bdC1 = bd.drawOne();
        assertEquals(51, bd.deckSize());
        assertTrue(bdC1.getSuite().equals("clubs") || bdC1.getSuite().equals("diamonds")
                || bdC1.getSuite().equals("hearts") || bdC1.getSuite().equals("spades"));
        assertTrue((bdC1.getValue() <= 13) && (bdC1.getValue() >= 1));

        //dealHand() test
        bdCardList = bd.dealHand();
        assertEquals(2, bdCardList.size());
        assertTrue(bdCardList.get(0).getSuite().equals("clubs") || bdCardList.get(0).getSuite().equals("diamonds")
                || bdCardList.get(0).getSuite().equals("hearts") || bdCardList.get(0).getSuite().equals("spades"));
        assertTrue(bdCardList.get(1).getSuite().equals("clubs") || bdCardList.get(1).getSuite().equals("diamonds")
                || bdCardList.get(1).getSuite().equals("hearts") || bdCardList.get(1).getSuite().equals("spades"));
        assertTrue((bdCardList.get(0).getValue() <= 13) && (bdCardList.get(0).getValue() >= 1));
        assertTrue((bdCardList.get(1).getValue() <= 13) && (bdCardList.get(1).getValue() >= 1));

        //getSuits() test
        assertEquals(4, bd.getSuits().length);
        for(int k = 0; k < bd.getSuits().length; k++)
            assertEquals(suitsArr[k], bd.getSuits()[k]);

        //shuffleDeck()
    }

    @Test
    public void BaccaratDealerShuffleDeckTest()
    {
        //shuffleDeck() test
        bd.shuffleDeck();
        assertEquals(52, bd.deckSize()); //deckSize() test
        Set<Card> duplicatesCheck = new HashSet<Card>(bd.deck);
        assertEquals(bd.deck.size(), duplicatesCheck.size());

        int clubsCount = 0;
        int diamondsCount = 0;
        int heartsCount = 0;
        int spadesCount = 0;
        int valArr[] = new int[13];
        for(int k = 0; k < bd.deckSize(); k++)
        {
            if(bd.deck.get(k).getSuite() == "clubs")
                clubsCount++;
            else if(bd.deck.get(k).getSuite() == "diamonds")
                diamondsCount++;
            else if(bd.deck.get(k).getSuite() == "hearts")
                heartsCount++;
            else if(bd.deck.get(k).getSuite() == "spades")
                spadesCount++;
        }

        assertEquals(clubsCount, 13);
        assertEquals(clubsCount, 13);
        assertEquals(diamondsCount, 13);
        assertEquals(heartsCount, 13);
        assertEquals(spadesCount, 13);

        for(int k = 0; k < bd.deckSize(); k++)
        {
            assertTrue((bd.deck.get(k).getValue() >= 1) && (bd.deck.get(k).getValue() <= 13));
        }
    }

    @Test
    public void BaccaratGameLogicTest()
    {
        //handTotal() test
        assertEquals(5, bgl.handTotal(bglHand1));
        assertEquals(2, bgl.handTotal(bglHand2));

        //whoWon() test
        assertEquals("Banker", bgl.whoWon(bglHand1, bglHand2));
        assertEquals("Draw", bgl.whoWon(bglHand1, bglHand3));

        //evaluateBankerDraw() test
        assertFalse(bgl.evaluateBankerDraw(bglHand1, bglHand1C1));
        assertFalse(bgl.evaluateBankerDraw(bglHand4, nullCard));
        assertTrue(bgl.evaluateBankerDraw(bglHand4, bglHand4C1));

        //evaluatePlayerDraw() test
        assertTrue(bgl.evaluatePlayerDraw(bglHand4));
        assertFalse(bgl.evaluatePlayerDraw(bglHand5));
    }

    @Test
    public void BaccaratGameTest()
    {
        //Constructor test
        assertEquals(1000, bg.getCurrentBet());
        assertEquals("Player", bg.getHand());
        assertEquals(48, bg.getTheDealer().deckSize());

        //evaluateWinnings() test
        assertEquals(-10, bg2.evaluateWinnings());
        System.out.println(bg.evaluateWinnings());
        assertTrue((bg.evaluateWinnings() == 0.0) || (bg.evaluateWinnings() == 950)
                || (bg.evaluateWinnings() == 1000) || (bg.evaluateWinnings() == -1000));

        bgCardToString1 = bg.convertCardToString(bglHand1);
        bgCardToString2 = bg.convertCardToString(bglHand2);

        //convertCardToString() test
        assertEquals("2C", bgCardToString1.get(0));
        assertEquals("3D", bgCardToString1.get(1));
        assertEquals("11H", bgCardToString2.get(0));
        assertEquals("1S", bgCardToString2.get(1));
    }

    @Test
    public void BaccaratInfoTest()
    {
        //Constructor test
        assertEquals(100, bi.bid);
        assertEquals("Banker", bi.hand);
    }
}
