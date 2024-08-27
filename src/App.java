package src;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App {
    public static void main (String[] args) throws Exception {
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