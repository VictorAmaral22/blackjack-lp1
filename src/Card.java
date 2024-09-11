public class Card {
    String value;
    String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return this.value;
    }

    public String getSuit() {
        return this.suit;
    }

    public String getImageName() {
        return value + "-" + suit + ".png";
    }

}