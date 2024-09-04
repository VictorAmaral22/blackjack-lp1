package src;
import java.util.ArrayList;

public class Hand extends Deck {
    String player_name;

    Hand(ArrayList<Card> cards, String player_name){
        this.deck = cards;
        this.player_name = player_name;
    }

    public String getPlayerName () {
        return player_name;
    }

    public int getHandValue () {
        int sum = 0;
        for (int i = 0; i < this.deck.size(); i++) {
            sum += this.deck.get(i).getValue();
        }
        return sum;
    }

    public void addCard (Card card) {
        this.deck.add(card);
    }
}