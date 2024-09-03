package src;
import java.util.ArrayList;

public final class Game extends Deck {
	Game(){
		startGame();
	}

	public void startGame(){
		buildDeck();
		shuffleDeck();
		printCards();

		ArrayList<Card> cards_given = new ArrayList<Card>();		
		cards_given.add(giveCard());
		cards_given.add(giveCard());

		Hand player_hand = new Hand(cards_given, "user");
		System.err.println("Player's hand: ");
		player_hand.printCards();

		ArrayList<Card> cards_table = new ArrayList<Card>();
		cards_table.add(giveCard());
		cards_table.add(giveCard());

		Hand table_hand = new Hand(cards_table, "table");
		System.err.println("Table's hand: ");
		table_hand.printCards();
	}
}