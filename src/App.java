import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class SoundUtil {
    private Clip backgroundClip;

    // Método para tocar som de fundo em loop
    public void playBackgroundSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Método para parar o som de fundo
    public void stopBackgroundSound() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    // Método para tocar efeito sonoro uma vez
    public void playSoundEffect(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

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

    private static SoundUtil soundUtil = new SoundUtil();

    private static BackgroundPanel gamePanel;

    public static void main(String[] args) {
        game = new Game();
        game.buildDeck();
        game.shuffleDeck();

        player = new Player("Jogador");
        dealer = new Enemy("Dealer", "url_icon", Enemy.PlayStyle.SAFE);

        gamePanel = new BackgroundPanel("assets/mesa.png"); // Imagem inicial do fundo
        gamePanel.setLayout(new BorderLayout());

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

        btnDrawCard.addActionListener(e -> playerDrawCard());
        btnStay.addActionListener(e -> dealerTurn());
        btnReset.addActionListener(e -> resetGame());

        buttonPanel.add(btnDrawCard);
        buttonPanel.add(btnStay);
        buttonPanel.add(btnReset);
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setOpaque(false); // Painel transparente
        southPanel.add(playerPanel);
        southPanel.add(buttonPanel);

        gamePanel = new BackgroundPanel("assets/mesa.png");
        gamePanel.setLayout(new BorderLayout());

        // Outros componentes adicionados ao gamePanel
        gamePanel.add(dealerPanel, BorderLayout.NORTH);
        gamePanel.add(southPanel, BorderLayout.SOUTH);
        
        ImageIcon dealerImage = new ImageIcon("assets/images/dealer-" + "0" + ".png");
        JLabel dealerImageLabel = new JLabel(dealerImage);

        soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\nivel1.wav");

        dealerImagePanel.setLayout(new BorderLayout());
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);

        dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, dealerImagePanel);
        splitPane.setDividerLocation(frame.getWidth() / 2); // Divida o painel
        splitPane.setResizeWeight(0.8);
        splitPane.setOpaque(false);

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
        ImageIcon dealerImage = new ImageIcon("assets/images/dealer-" + levelCont + ".png");
        JLabel dealerImageLabel = new JLabel(dealerImage);
        dealerImagePanel.setLayout(new BorderLayout());
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
        dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
        dealerImagePanel.repaint();
        if (levelCont == 1) {
            soundUtil.stopBackgroundSound();
            JOptionPane.showMessageDialog(frame, "Nível 2");
            gamePanel.setBackgroundImage("assets/mesa2.jpeg"); // Troca a imagem de fundo para o nível 2
            soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\nivel2.wav");
        }
        if (levelCont == 2) {
            soundUtil.stopBackgroundSound();
            JOptionPane.showMessageDialog(frame, "Nível 3");
            gamePanel.setBackgroundImage("assets/mesa3.jpg");
            soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\nivel3.wav");
        }
        if (levelCont == 3) {
            soundUtil.stopBackgroundSound();
            JOptionPane.showMessageDialog(frame,"O Desafio final chegou");
            gamePanel.setBackgroundImage("assets/mesa4.jpg");
            soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\berry.wav");
        }
    }

    private static void playerDrawCard() {
        player.hand.addCard(game.giveCard());
        updatePlayerCards();

        soundUtil.playSoundEffect("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\card.wav");

        if (player.hand.getHandValue() > 21) {
            JOptionPane.showMessageDialog(frame, "Você estourou! Dealer vence.");
            ImageIcon dealerImage = new ImageIcon("assets/images/dealer-" + levelCont + "-derrota.png");
            JLabel dealerImageLabel = new JLabel(dealerImage);
            dealerImagePanel.setLayout(new BorderLayout());
            dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
            dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
            dealerImagePanel.repaint();
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
            ImageIcon dealerImage = new ImageIcon("assets/images/dealer-" + levelCont + "-derrota.png");
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
            ImageIcon cardIcon = new ImageIcon("assets/cards/" + card.getImageName()); // Atualize o caminho conforme
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
        startGame();
    }
}
