package  src;

public class Enemy extends Player {
    public enum PlayStyle {
        RISKY,
        MODERATE,
        SAFE
    }

    int id;
    PlayStyle style;

    public Enemy(String name, int id, PlayStyle style) {
        super(name);
        this.id = id;
        this.style = style;
        this.hand = new Hand();
    }

    public String getIcon() {
        return "src/assets/dealers/dealer-" + this.id + ".png";
    }

    public String getIconDefeat() {
        return "src/assets/dealers/dealer-" + this.id + "-derrota.png";
    }

    public Enemy.GameAction decisionMaking(int hand_value) {
        if (this.style == PlayStyle.RISKY) {
            if (hand_value == 21 || hand_value >= 20) {
                return Enemy.GameAction.PASS;
            } else {
                return Enemy.GameAction.BUY;
            }
        }
        if (this.style == PlayStyle.MODERATE) {
            if (hand_value == 21 || hand_value >= 18) {
                return Enemy.GameAction.PASS;
            } else {
                return Enemy.GameAction.BUY;
            }
        }
        if (this.style == PlayStyle.SAFE) {
            if (hand_value == 21 || hand_value >= 17) {
                return Enemy.GameAction.PASS;
            } else {
                return Enemy.GameAction.BUY;
            }
        }

        return Enemy.GameAction.PASS;
    }
}
