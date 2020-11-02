import BaccaratGame.BaccaratDealer;
import BaccaratGame.Card;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.TreeSet;

public class BaccaratTest
{
    private Card c1, c2, c3;
    private BaccaratDealer bd;
    private String[] suitsArr;

    @Before
    public void init()
    {
        c1 = new Card("clubs", 1);
        c2 = new Card("hearts", 3);
        c3 = new Card("spades", 9);

        bd = new BaccaratDealer();
        suitsArr = new String[] { "clubs", "diamonds", "hearts", "spades" };
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
        assertEquals(0, bd.deckSize());
        for(int k = 0; k < suitsArr.length; k++)
            assertEquals(suitsArr[k], bd.getSuits()[k]);

        //generateDeck() test
        bd.generateDeck();
        assertEquals(52, bd.deckSize());
        Set<Card> duplicatesCheck = new HashSet<Card>(bd.deck);
        assertEquals(bd.deck.size(), duplicatesCheck.size());

        int clubsCount = 0;
        int diamondsCount = 0;
        int heartsCount = 0;
        int spadesCount = 0;
        int valArr[] = new int[13];
        for(int k = 0; k < bd.deck.size(); k++)
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

        for(int k = 0; k < bd.deck.size(); k++)
        {
            assertTrue((bd.deck.get(k).getValue() >= 1) && (bd.deck.get(k).getValue() <= 13));
        }
    }
}
