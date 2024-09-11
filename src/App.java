

import java.awt.*;
import javax.swing.*;

public class App {
    private static JFrame frame;
    private static JPanel playerPanel;
    private static JPanel dealerPanel;
    private static JPanel buttonPanel;
    private static Game game;
    private static Player player;
    private static Enemy dealer; 
    private static int levelCont = 0;
    private static JPanel dealerImagePanel = new JPanel();



    public static void main(String[] args) {
        game = new Game();
        game.buildDeck();
        game.shuffleDeck();

        player = new Player("Jogador");
        dealer = new Enemy("Dealer", "url_icon", Enemy.PlayStyle.SAFE);

        frame = new JFrame("BlackBerry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dealerPanel = new JPanel();
        dealerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnDrawCard = new JButton("Comprar Carta");
        JButton btnStay = new JButton("Passar");
        JButton btnReset = new JButton("Reiniciar Jogo");

        btnDrawCard.addActionListener(e -> playerDrawCard());
        btnStay.addActionListener(e -> dealerTurn());
        btnReset.addActionListener(e -> resetGame());

        buttonPanel.add(btnDrawCard);
        buttonPanel.add(btnStay);
        buttonPanel.add(btnReset);
        
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(playerPanel);
        southPanel.add(buttonPanel);

        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(dealerPanel, BorderLayout.NORTH);
        gamePanel.add(southPanel, BorderLayout.SOUTH);

        ImageIcon dealerImage = new ImageIcon("src/assets/images/dealer-" + "0" + ".png");
        JLabel dealerImageLabel = new JLabel(dealerImage);

        dealerImagePanel.setLayout(new BorderLayout());
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);

        dealerImagePanel.add(dealerImageLabel, BorderLayout.CENTER);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, dealerImagePanel);
        splitPane.setDividerLocation(frame.getWidth() / 2);
        splitPane.setResizeWeight(0.8);

        frame.getContentPane().add(splitPane);

        startGame();

        frame.setVisible(true);
    }

    private static void startGame() {
        if (levelCont > 4){
            System.exit(0); // ADD AQUI O QUE VAI ACONTECER CASO O PLAYER GANHE TODOS LEVELS
        }

        player.hand = new Hand();
        dealer.hand = new Hand();

        player.hand.addCard(game.giveCard());
        player.hand.addCard(game.giveCard());
        dealer.hand.addCard(game.giveCard());
        dealer.hand.addCard(game.giveCard());

        updatePlayerCards();
        updateDealerCards();
    }

    private static void updtadeLevel() {
        levelCont += 1;
        System.out.println(levelCont);
        dealerImagePanel.removeAll();
        ImageIcon dealerImage = new ImageIcon("src/assets/images/dealer-" + levelCont + ".png");
        JLabel dealerImageLabel = new JLabel(dealerImage);
        dealerImagePanel.setLayout(new BorderLayout());
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
        dealerImagePanel.add(dealerImageLabel, BorderLayout.CENTER);
        dealerImagePanel.repaint();
    }

    private static void playerDrawCard() {
        player.hand.addCard(game.giveCard());
        updatePlayerCards();

        if (player.hand.getHandValue() > 21) {
            JOptionPane.showMessageDialog(frame, "Você estourou! Dealer vence.");
            resetGame();
        }
    }

    private static void dealerTurn() {
        while (dealer.hand.getHandValue() < 17) {
            dealer.hand.addCard(game.giveCard());
            updateDealerCards();
        }

        int dealerPoints = dealer.hand.getHandValue();
        int playerPoints = player.hand.getHandValue();

        if (dealerPoints > 21) {
            JOptionPane.showMessageDialog(frame, "Dealer estourou! Você vence.");
            updtadeLevel();
        } else if (dealerPoints > playerPoints) {
            JOptionPane.showMessageDialog(frame, "Dealer vence com " + dealerPoints + " pontos.");
        } else if (dealerPoints < playerPoints) {
            JOptionPane.showMessageDialog(frame, "Você vence com " + playerPoints + " pontos.");
            updtadeLevel();
        } else {
            JOptionPane.showMessageDialog(frame, "Empate!");
            
        }
        resetGame();

    }

    private static void updatePlayerCards() {
        playerPanel.removeAll();
        for (Card card : player.hand.getCards()) {
            ImageIcon cardIcon = new ImageIcon("src/assets/cards/" + card.getImageName()); // Atualize o caminho conforme
            Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel cardLabel = new JLabel(scaledIcon);
            playerPanel.add(cardLabel);
        }
        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private static void updateDealerCards() {
        dealerPanel.removeAll();
        for (Card card : dealer.hand.getCards()) {
            ImageIcon cardIcon = new ImageIcon("src/assets/cards/" + card.getImageName());
            Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel cardLabel = new JLabel(scaledIcon);
            dealerPanel.add(cardLabel);
        }
        dealerPanel.revalidate();
        dealerPanel.repaint();
    }

    private static void resetGame() {
        playerPanel.removeAll();
        dealerPanel.removeAll();
        playerPanel.revalidate();
        dealerPanel.revalidate();
        playerPanel.repaint();
        dealerPanel.repaint();
        dealerImagePanel.repaint();
        game.buildDeck();
        game.shuffleDeck();
        startGame();
    }
}
