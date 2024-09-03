package src;

public class Card {
    String value;
    String suit;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getCardImg() {
        return value + "-" + suit;
    }

    public int getValue() {
        return Integer.parseInt(this.value);
    }
}
