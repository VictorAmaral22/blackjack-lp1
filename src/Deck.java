package src;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
  private class Card {
    String valor;
    String tipo;
    
    Card(String valor, String tipo){
      this.valor = valor;
      this.tipo = tipo;
    }

    public String toString(){ // transforma o nome das cartas q estavam em pointer em strings pra poder pegar no assets
      return valor + "-" + tipo;
    }

    // public int getValue(){
    //   if ("C".contains(valor) || "E".contains(valor) || "P".contains(valor) || "O".contains(valor)){
    //     return Integer.parseInt(valor);
    //   }
    // }

  }

  Random random = new Random();
  ArrayList<Card> deck;

  public void buildDeck() {
    deck = new ArrayList<Card>();
    String[] valores = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    String[] naipes = {"C", "E", "P", "O"};
    
    for (int i = 0; i < naipes.length; i++) {
      for (int j = 0; j < valores.length; j++) {
        Card card = new Card(valores[j], naipes[i]);
        deck.add(card);
      }
    }
  }

  public void embaralhaDeck(){
    for (int i = 0; i < deck.size(); i++){
      int j = random.nextInt(deck.size());
      Card cardAtual = deck.get(i);
      Card randomCard = deck.get(j);
      deck.set(i, randomCard);
      deck.set(j, cardAtual);
    }
    
  }

}
