public class Player {
    String name;
    int wins;
    Hand hand;

    Player(String name) {
        this.name = name;
        this.wins = 0;
        this.hand = new Hand();
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

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
