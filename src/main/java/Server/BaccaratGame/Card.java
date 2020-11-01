package Server.BaccaratGame;

public class Card {
    private String suite;
    private int value;

    public Card(String suite, int value) {
        this.suite = suite;
        this.value = value;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Card) obj).getSuite().equals(this.suite) && ((Card) obj).getValue() == this.value);
    }

    public String toString() {
        return this.value + " of " + this.suite;
    }
}
