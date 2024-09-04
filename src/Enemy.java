package src;

public class Enemy extends Player {
    public Enemy(String name) {
        super(name);
    }

    public int decisionMaking (int hand_value) {
        if (hand_value == 21 || hand_value >= 18) {
            return 2;
        } else {
            return 1;
        }
    }    
}