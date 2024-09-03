package src;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	Random random = new Random();
	ArrayList<Card> deck;

	public void buildDeck() {
		deck = new ArrayList<>();
		String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
		String[] suits = {"C", "E", "P", "O"};
		
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < values.length; j++) {
				Card card = new Card(values[j], suits[i]);
				deck.add(card);
			}
		}
	}

	public void shuffleDeck(){
		for (int i = 0; i < deck.size(); i++){
			int j = random.nextInt(deck.size());
			Card currentCard = deck.get(i);
			Card randomCard = deck.get(j);
			deck.set(i, randomCard);
			deck.set(j, currentCard);
		}		
	}

	public void printCards (){
		ArrayList<String> tmp;
		tmp = new ArrayList<>();

		for (int i = 0; i < deck.size(); i++) {
			tmp.add(deck.get(i).value + "-" + deck.get(i).suit);
		}

		System.out.println(tmp);
	}

	public Card giveCard () {
		Card card_given = deck.remove(deck.size() - 1);
		return card_given;
	}
}
