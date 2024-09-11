import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class App {
    private static JFrame frame;
    private static BackgroundPanel gamePanel;
    private static BackgroundPanel dealerBackgroundPanel;
    
    private static Game game;
    private static Player player;
    private static ArrayList<Enemy> dealers = new ArrayList<>(); 

    private static JPanel playerPanel;
    private static JPanel dealerPanel;
    private static JPanel dealerImagePanel;
    private static JPanel buttonPanel;
    private static SoundUtil soundUtil = new SoundUtil();

    private static int levelCont = 0;
    // private static JPanel dealerImagePanel = new JPanel();

    

    public static void main(String[] args) {
        game = new Game();
        game.buildDeck();
        game.shuffleDeck();

        player = new Player("Jogador");
        
        dealers.add(new Enemy("Dev Júnior", 0, Enemy.PlayStyle.RISKY));
        dealers.add(new Enemy("Fã do React", 1, Enemy.PlayStyle.RISKY));
        dealers.add(new Enemy("Casal programador", 2, Enemy.PlayStyle.MODERATE));
        dealers.add(new Enemy("Mestre dos Cursinhos", 3, Enemy.PlayStyle.MODERATE));
        dealers.add(new Enemy("Programador Tranquilão", 4, Enemy.PlayStyle.SAFE));
        dealers.add(new Enemy("Rafael \"O Professor\" Berri", 5, Enemy.PlayStyle.SAFE));
       
        drawScreen();

        runRound();
    }

    static public void drawScreen() {
        frame = new JFrame("BlackBerry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerPanel.setOpaque(false); // Painel transparente

        dealerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dealerPanel.setOpaque(false); // Painel transparente

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false); // Painel transparente
        JButton btnDrawCard = new JButton("Comprar Carta");
        JButton btnStay = new JButton("Passar");
        JButton btnReset = new JButton("Reiniciar Jogo");
        btnDrawCard.addActionListener(e -> {
            playerDrawCard();
        });
        btnStay.addActionListener(e -> {
            dealerTurn();
        });
        btnReset.addActionListener(e -> {
            resetGame();
        });
        buttonPanel.add(btnDrawCard);
        buttonPanel.add(btnStay);
        buttonPanel.add(btnReset);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setOpaque(false); // Painel transparente
        southPanel.add(playerPanel);
        southPanel.add(buttonPanel);

        gamePanel = new BackgroundPanel("assets/tables/mesa0.png"); // Imagem inicial do fundo
        gamePanel.setLayout(new BorderLayout());       

        gamePanel.add(dealerPanel, BorderLayout.NORTH);
        gamePanel.add(southPanel, BorderLayout.SOUTH);

        ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIcon());
        JLabel dealerImageLabel = new JLabel(dealerImage);
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);

        dealerBackgroundPanel = new BackgroundPanel("assets/backgrounds/background-"+levelCont+".png"); // Imagem inicial do fundo
        dealerBackgroundPanel.setLayout(new BorderLayout());

        dealerImagePanel = new JPanel();
        dealerImagePanel.setLayout(new BorderLayout());
        dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
        dealerImagePanel.setVisible(true);
        dealerImagePanel.setOpaque(false);
        dealerBackgroundPanel.add(dealerImagePanel, BorderLayout.SOUTH);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, dealerBackgroundPanel);
        splitPane.setDividerLocation(frame.getWidth() / 2); // Divida o painel
        splitPane.setResizeWeight(0.8);
        splitPane.setOpaque(false);
        frame.getContentPane().add(splitPane);

        frame.setVisible(true);
        soundUtil.playBackgroundSound("assets/sounds/nivel0.wav");
    }

    private static void runRound() {
        if (levelCont > dealers.size()){
            System.exit(0); // ADD AQUI O QUE VAI ACONTECER CASO O PLAYER GANHE TODOS LEVELS
        }

        player.hand = game.buildHand(player);
        dealers.get(levelCont).hand = game.buildHand(dealers.get(levelCont));

        updatePlayerCards();
        updateDealerCards();
    }

    private static void updtadeLevel() {
        levelCont += 1;
        System.out.println(levelCont);
        dealerImagePanel.removeAll();
        ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIcon());
        JLabel dealerImageLabel = new JLabel(dealerImage);
        dealerImagePanel.setLayout(new BorderLayout());
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
        dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
        dealerImagePanel.repaint();

        soundUtil.stopBackgroundSound();
        JOptionPane.showMessageDialog(frame, "Nível "+(levelCont+1));
        gamePanel.setBackgroundImage("assets/tables/mesa" + levelCont + ".jpeg");
        soundUtil.playBackgroundSound("assets/sounds/nivel" + levelCont + ".wav");
    }

    private static void playerDrawCard() {
        player.hand.addCard(game.giveCard());
        updatePlayerCards();

        soundUtil.playSoundEffect("assets/sounds/card.wav");

        if (player.hand.getHandValue() > 21) {
            JOptionPane.showMessageDialog(frame, "Você estourou! Dealer vence.");
            ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIconDefeat());
            JLabel dealerImageLabel = new JLabel(dealerImage);
            dealerImagePanel.setLayout(new BorderLayout());
            dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
            dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
            dealerImagePanel.repaint();
            resetGame();
        }
    }

    private static void dealerTurn() {
        while (dealers.get(levelCont).hand.getHandValue() < 17) {
            dealers.get(levelCont).hand.addCard(game.giveCard());
            updateDealerCards();
        }

        int dealerPoints = dealers.get(levelCont).hand.getHandValue();
        int playerPoints = player.hand.getHandValue();

        if (dealerPoints > 21) {
            JOptionPane.showMessageDialog(frame, "Dealer estourou! Você vence.");
            updtadeLevel();
        } else if (dealerPoints > playerPoints) {
            JOptionPane.showMessageDialog(frame, "Dealer vence com " + dealerPoints + " pontos.");
            ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIconDefeat());
            JLabel dealerImageLabel = new JLabel(dealerImage);
            dealerImagePanel.setLayout(new BorderLayout());
            dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
            dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
            dealerImagePanel.repaint();
            resetGame();
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
            ImageIcon cardIcon = new ImageIcon("assets/cards/" + card.getImageName());
            Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            var cardLabel = new JLabel(scaledIcon);
            playerPanel.add(cardLabel);
        }
        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private static void updateDealerCards() {
        dealerPanel.removeAll();
        for (Card card : dealers.get(levelCont).hand.getCards()) {
            ImageIcon cardIcon = new ImageIcon("assets/cards/" + card.getImageName());
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
        runRound();
    }
}
