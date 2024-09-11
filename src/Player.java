public class Player {
    String name;
    int wins;
    Hand hand; // Adiciona a mão do jogador

    Player(String name) {
        this.name = name;
        this.wins = 0;
        this.hand = new Hand(); // Inicializa a mão do jogador
    }

    public enum GameAction {
        PASS,
        QUIT,
        BUY
    }

    public void addWin() {
        this.wins += 1;
    }

    public String getName() {
        return this.name;
    }

    public int getWins() {
        return this.wins;
    }

    // Retorna a mão do jogador
    public Hand getHand() {
        return this.hand;
    }

    // Define uma nova mão para o jogador (útil ao reiniciar o jogo)
    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
