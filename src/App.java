import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.JFrame;

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
    private static BackgroundPanel gamePanel;
    
    private static Deck deck;
    private static Player player;
    private static ArrayList<Enemy> dealers = new ArrayList<>(); 
    // private static JPanel playerPanel;
    // private static JPanel dealerPanel;
    // private static JPanel buttonPanel;
    // private static int levelCont = 0;
    // private static JPanel dealerImagePanel = new JPanel();
    // private static SoundUtil soundUtil = new SoundUtil();


    public static void main(String[] args) {
        frame = new JFrame("BlackBerry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        gamePanel = new BackgroundPanel("assets/mesa.png"); // Imagem inicial do fundo
        gamePanel.setLayout(new BorderLayout());

        deck = new Deck();
        deck.buildDeck();
        deck.shuffleDeck();

        player = new Player("Jogador");
        dealers.add(new Enemy("Dev Júnior", 0, Enemy.PlayStyle.RISKY));
        dealers.add(new Enemy("Fã do React", 1, Enemy.PlayStyle.RISKY));
        dealers.add(new Enemy("Casal programador", 2, Enemy.PlayStyle.MODERATE));
        dealers.add(new Enemy("Mestre dos Cursinhos", 3, Enemy.PlayStyle.MODERATE));
        dealers.add(new Enemy("Programador Tranquilão", 4, Enemy.PlayStyle.SAFE));
        dealers.add(new Enemy("Rafael \"O Professor\" Berri", 5, Enemy.PlayStyle.SAFE));

        


        // playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // playerPanel.setOpaque(false); // Painel transparente

        // dealerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // dealerPanel.setOpaque(false); // Painel transparente

        // buttonPanel = new JPanel(new FlowLayout());
        // buttonPanel.setOpaque(false); // Painel transparente

        // JButton btnDrawCard = new JButton("Comprar Carta");
        // JButton btnStay = new JButton("Passar");
        // JButton btnReset = new JButton("Reiniciar Jogo");

        // btnDrawCard.addActionListener(e -> playerDrawCard());
        // btnStay.addActionListener(e -> dealerTurn());
        // btnReset.addActionListener(e -> resetGame());

        // buttonPanel.add(btnDrawCard);
        // buttonPanel.add(btnStay);
        // buttonPanel.add(btnReset);
        
        // JPanel southPanel = new JPanel();
        // southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        // southPanel.setOpaque(false); // Painel transparente
        // southPanel.add(playerPanel);
        // southPanel.add(buttonPanel);


        // Outros componentes adicionados ao gamePanel
        // gamePanel.add(dealerPanel, BorderLayout.NORTH);
        // gamePanel.add(southPanel, BorderLayout.SOUTH);
        
        // ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIcon());
        // JLabel dealerImageLabel = new JLabel(dealerImage);

        // soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\nivel1.wav");

        // dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);

        // dealerImagePanel.setLayout(new BorderLayout());
        // dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
        // dealerImagePanel.setVisible(true);
        
        // JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, dealerImagePanel);
        // splitPane.setDividerLocation(frame.getWidth() / 2); // Divida o painel
        // splitPane.setResizeWeight(0.8);
        // splitPane.setOpaque(false);

        // frame.getContentPane().add(splitPane);

        // startGame();

        // frame.setVisible(true);
    }

    // private static void startGame() {
    //     if (levelCont > 4){
    //         System.exit(0); // ADD AQUI O QUE VAI ACONTECER CASO O PLAYER GANHE TODOS LEVELS
    //     }

    //     player.hand = new Hand();
    //     dealers.get(levelCont).hand = new Hand();

    //     player.hand.addCard(deck.giveCard());
    //     player.hand.addCard(deck.giveCard());
    //     dealers.get(levelCont).hand.addCard(deck.giveCard());
    //     dealers.get(levelCont).hand.addCard(deck.giveCard());

    //     updatePlayerCards();
    //     updateDealerCards();
    // }

    // private static void updtadeLevel() {
    //     levelCont += 1;
    //     System.out.println(levelCont);
    //     dealerImagePanel.removeAll();
    //     ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIcon());
    //     JLabel dealerImageLabel = new JLabel(dealerImage);
    //     dealerImagePanel.setLayout(new BorderLayout());
    //     dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
    //     dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
    //     dealerImagePanel.repaint();
    //     if (levelCont == 1) {
    //         soundUtil.stopBackgroundSound();
    //         JOptionPane.showMessageDialog(frame, "Nível 2");
    //         gamePanel.setBackgroundImage("assets/mesa2.jpeg"); // Troca a imagem de fundo para o nível 2
    //         soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\nivel2.wav");
    //     }
    //     if (levelCont == 2) {
    //         soundUtil.stopBackgroundSound();
    //         JOptionPane.showMessageDialog(frame, "Nível 3");
    //         gamePanel.setBackgroundImage("assets/mesa3.jpg");
    //         soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\nivel3.wav");
    //     }
    //     if (levelCont == 3) {
    //         soundUtil.stopBackgroundSound();
    //         JOptionPane.showMessageDialog(frame,"O Desafio final chegou");
    //         gamePanel.setBackgroundImage("assets/mesa4.jpg");
    //         soundUtil.playBackgroundSound("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\berry.wav");
    //     }
    // }

    // private static void playerDrawCard() {
    //     player.hand.addCard(deck.giveCard());
    //     updatePlayerCards();

    //     soundUtil.playSoundEffect("C:\\\\Users\\\\shits\\\\Desktop\\\\blackjack-lp1-1\\\\src\\\\assets\\\\sounds\\\\card.wav");

    //     if (player.hand.getHandValue() > 21) {
    //         JOptionPane.showMessageDialog(frame, "Você estourou! Dealer vence.");
    //         ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIconDefeat());
    //         JLabel dealerImageLabel = new JLabel(dealerImage);
    //         dealerImagePanel.setLayout(new BorderLayout());
    //         dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
    //         dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
    //         dealerImagePanel.repaint();
    //         resetGame();
    //     }
    // }

    // private static void dealerTurn() {
    //     while (dealers.get(levelCont).hand.getHandValue() < 17) {
    //         dealers.get(levelCont).hand.addCard(deck.giveCard());
    //         updateDealerCards();
    //     }

    //     int dealerPoints = dealers.get(levelCont).hand.getHandValue();
    //     int playerPoints = player.hand.getHandValue();

    //     if (dealerPoints > 21) {
    //         JOptionPane.showMessageDialog(frame, "Dealer estourou! Você vence.");
    //         updtadeLevel();
    //     } else if (dealerPoints > playerPoints) {
    //         JOptionPane.showMessageDialog(frame, "Dealer vence com " + dealerPoints + " pontos.");
    //         ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIconDefeat());
    //         JLabel dealerImageLabel = new JLabel(dealerImage);
    //         dealerImagePanel.setLayout(new BorderLayout());
    //         dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
    //         dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
    //         dealerImagePanel.repaint();
    //         resetGame();
    //     } else if (dealerPoints < playerPoints) {
    //         JOptionPane.showMessageDialog(frame, "Você vence com " + playerPoints + " pontos.");
    //         updtadeLevel();
    //     } else {
    //         JOptionPane.showMessageDialog(frame, "Empate!");
            
    //     }
    //     resetGame();

    // }

    // private static void updatePlayerCards() {
    //     playerPanel.removeAll();
    //     for (Card card : player.hand.getCards()) {
    //         ImageIcon cardIcon = new ImageIcon("assets/cards/" + card.getImageName()); // Atualize o caminho conforme
    //         Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
    //         ImageIcon scaledIcon = new ImageIcon(scaledImage);
    //         JLabel cardLabel = new JLabel(scaledIcon);
    //         playerPanel.add(cardLabel);
    //     }
    //     playerPanel.revalidate();
    //     playerPanel.repaint();
    // }

    // private static void updateDealerCards() {
    //     dealerPanel.removeAll();
    //     for (Card card : dealers.get(levelCont).hand.getCards()) {
    //         ImageIcon cardIcon = new ImageIcon("assets/cards/" + card.getImageName());
    //         Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
    //         ImageIcon scaledIcon = new ImageIcon(scaledImage);
    //         JLabel cardLabel = new JLabel(scaledIcon);
    //         dealerPanel.add(cardLabel);
    //     }
    //     dealerPanel.revalidate();
    //     dealerPanel.repaint();
    // }

    // private static void resetGame() {
    //     playerPanel.removeAll();
    //     dealerPanel.removeAll();
    //     playerPanel.revalidate();
    //     dealerPanel.revalidate();
    //     playerPanel.repaint();
    //     dealerPanel.repaint();
    //     dealerImagePanel.repaint();
    //     deck.buildDeck();
    //     deck.shuffleDeck();
    //     startGame();
    // }
}
