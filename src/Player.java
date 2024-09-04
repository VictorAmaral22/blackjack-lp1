package src;

public class Player {
    String name;
    int wins;

    Player (String name) {
        this.name = name;
        this.wins = 0;
    }

    public void addWin () {
        this.wins += 1;
    }

    public String getName() {
        return this.name;
    }

    public int getWins() {
        return this.wins;
    }
}