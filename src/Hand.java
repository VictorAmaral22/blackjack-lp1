import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private String owner;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(String owner) {
        this.cards = new ArrayList<>();
        this.owner = owner;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getHandValue() {
        int total = 0;
        for (Card card : cards) {
            // Converte o valor da carta em um número inteiro
            int cardValue = Integer.parseInt(card.getValue());
            // Embaralha as cartas com valores acima de 10 como se fossem 10 (valores de
            // figuras)
            total += Math.min(cardValue, 10);
        }
        return total;
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.getValue() + "-" + card.getSuit());
        }
    }
}
