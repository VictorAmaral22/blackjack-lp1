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
        return "assets/dealers/dealer-" + this.id + ".png";
    }

    public String getIconDefeat() {
        return "assets/dealers/dealer-" + this.id + "-derrota.png";
    }

    public Enemy.GameAction decisionMaking() {
        if (this.style == PlayStyle.RISKY) {
            if (this.hand.getHandValue() == 21 || this.hand.getHandValue() >= 20) {
                return Enemy.GameAction.PASS;
            } else {
                return Enemy.GameAction.BUY;
            }
        }
        if (this.style == PlayStyle.MODERATE) {
            if (this.hand.getHandValue() == 21 || this.hand.getHandValue() >= 18) {
                return Enemy.GameAction.PASS;
            } else {
                return Enemy.GameAction.BUY;
            }
        }
        if (this.style == PlayStyle.SAFE) {
            if (this.hand.getHandValue() == 21 || this.hand.getHandValue() >= 17) {
                return Enemy.GameAction.PASS;
            } else {
                return Enemy.GameAction.BUY;
            }
        }

        return Enemy.GameAction.PASS;
    }
}
