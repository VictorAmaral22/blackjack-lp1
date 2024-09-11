public class Enemy extends Player {
    public enum PlayStyle {
        RISKY,
        MODERATE,
        SAFE
    }

    String icon_url;
    PlayStyle style;

    public Enemy(String name, String icon_url, PlayStyle style) {
        super(name);
        this.icon_url = icon_url;
        this.style = style;
        this.hand = new Hand();
    }

    public String getIcon() {
        return this.icon_url;
    }

    public Enemy.GameAction decisionMaking(int hand_value) {
        if (this.style == PlayStyle.RISKY) {
            if (hand_value == 21 || hand_value >= 19) {
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
            if (hand_value == 21 || hand_value >= 16) {
                return Enemy.GameAction.PASS;
            } else {
                return Enemy.GameAction.BUY;
            }
        }

        return Enemy.GameAction.PASS;
    }
}
