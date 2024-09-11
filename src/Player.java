public class Player {
    String name;
    Hand hand;
    int lives;

    Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.lives = 3;
    }

    public enum GameAction {
        PASS,
        QUIT,
        BUY
    }

    public void removeLives() {
        this.lives -= 1;
    }
    
    public int getLives() {
        return this.lives;
    }

    public void setLives(int value) {
        this.lives = value;
    }

    public void resetLives() {
        this.lives = 3;
    }

    public String getName() {
        return this.name;
    }
    
    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
