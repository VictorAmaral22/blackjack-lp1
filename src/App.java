package src;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App {
    public static void main (String[] args) throws Exception {
        Game game = new Game();
        System.out.println(game.deck); // ta printando o deck embaralhado

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(420, 420);
        frame.setTitle("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        
    
    }
}