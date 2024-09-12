import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


public class App {
    private static JFrame frame;
    private static BackgroundPanel dealerBackgroundPanel;
    
    private static Game game;
    private static Player player;
    private static ArrayList<Enemy> dealers = new ArrayList<>(); 

    private static JPanel playerPanel;
    private static JPanel dealerPanel;
    private static JPanel dealerImagePanel;
    private static JPanel buttonPanel;
    private static JPanel livesPanel;

    private static final SoundUtil soundUtil = new SoundUtil();
    private static int levelCont = 0;
    private static Font pixelMplus;    
    private static final JFrame frameMenuInicial = new JFrame();  

    private static BackgroundPanel gamePanel;

    public static void main(String[] args) {
        try{
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/PixelMplus10-Regular.ttf")).deriveFont(30f);	
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/PixelMplus10-Regular.ttf")));			
        }
        catch(IOException | FontFormatException e){}

        soundUtil.playBackgroundSound("assets/sounds/main_title.wav");

        frameMenuInicial.setTitle("BlackBerry");
        frameMenuInicial.setSize(600, 400);
        frameMenuInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenuInicial.setResizable(false);
        frameMenuInicial.setLocationRelativeTo(null); 
        frameMenuInicial.getContentPane().setBackground(Color.black);

        frameMenuInicial.setLayout(null);

        JLabel title = new JLabel("BlackBerry");
        title.setForeground(Color.WHITE);
        title.setFont(pixelMplus);
        title.setBounds(200, 50, 200, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        frameMenuInicial.add(title);

        JButton btn_Iniciar = new JButton("Iniciar");
        btn_Iniciar.setBackground(Color.WHITE);
        btn_Iniciar.setBorder(null);
        btn_Iniciar.setFocusPainted(false);
        btn_Iniciar.setBounds(200, 140, 200, 50);
        btn_Iniciar.setFont(pixelMplus);
        btn_Iniciar.addActionListener(e -> {
            frameMenuInicial.setVisible(false);
            soundUtil.stopBackgroundSound();
            try {
                startGame();
            } catch (InterruptedException ex) {
            }
        });
        frameMenuInicial.add(btn_Iniciar);

        JButton btn_Sair = new JButton("Sair");
        btn_Sair.setBackground(Color.WHITE);
        btn_Sair.setBorder(null);
        btn_Sair.setFocusPainted(false);
        btn_Sair.setBounds(200, 220, 200, 50);
        btn_Sair.setFont(pixelMplus);
        btn_Sair.addActionListener(e -> System.exit(0)); 
        frameMenuInicial.add(btn_Sair); 

        frameMenuInicial.setVisible(true);
    }

    static public void startGame () throws InterruptedException {        
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
        playerPanel.setOpaque(false);

        dealerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dealerPanel.setOpaque(false);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        ImageIcon livesImage = new ImageIcon("assets/lives/vidas-3.png");
        Image scaledImage = livesImage.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel livesImageLabel = new JLabel(scaledIcon);
        livesImageLabel.setHorizontalAlignment(JLabel.LEFT);
        livesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        livesPanel.add(livesImageLabel);
        livesPanel.setOpaque(false);
        Dimension size = new Dimension(180, 180);
        livesPanel.setMaximumSize(size);

        JButton btnDrawCard = new JButton("Comprar Carta");
        JButton btnStay = new JButton("Passar");
        JButton btnReset = new JButton("Desistir");

        btnDrawCard.setHorizontalAlignment(JLabel.RIGHT);
        btnStay.setHorizontalAlignment(JLabel.RIGHT);
        btnReset.setHorizontalAlignment(JLabel.RIGHT);

        btnDrawCard.addActionListener((var e) -> {
            try {
                playerDrawCard();
            } catch (InterruptedException ex) {}
        });
        btnStay.addActionListener(e -> {
            try {
                dealerTurn();
            } catch (InterruptedException ex) {}
        });
        btnReset.addActionListener(e -> {
            System.exit(0);
        });
        buttonPanel.add(btnDrawCard);
        buttonPanel.add(btnStay);
        buttonPanel.add(btnReset);

        buttonPanel.add(livesPanel);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setOpaque(false); // Painel transparente
        southPanel.add(playerPanel);
        southPanel.add(buttonPanel);

        ImageIcon deckImage = new ImageIcon("assets/deck.png");
        JLabel deckImageLabel = new JLabel(deckImage);
        deckImageLabel.setHorizontalAlignment(JLabel.CENTER);

        gamePanel = new BackgroundPanel("assets/tables/mesa" + levelCont + ".png"); // Imagem inicial do fundo
        gamePanel.setLayout(new BorderLayout());

        gamePanel.add(deckImageLabel, BorderLayout.CENTER);
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
        soundUtil.playBackgroundSound("assets/sounds/nivel" + levelCont + ".wav");
    }

    private static void runRound() throws InterruptedException {
        player.hand = game.buildHand(player);
        dealers.get(levelCont).hand = game.buildHand(dealers.get(levelCont));

        updatePlayerCards();
        updateDealerCards(false);
    }

    private static void updateLevel() {
        levelCont += 1;

        if (levelCont == 5){
            player.setLives(1);
        } 
        // else {
        //     player.resetLives();
        // }

        if (levelCont > dealers.size()-1) {
            soundUtil.stopBackgroundSound();
            frame.setVisible(false);
            finalScreen();
        }

        dealerImagePanel.removeAll();
        ImageIcon dealerImage = new ImageIcon(dealers.get(levelCont).getIcon());
        JLabel dealerImageLabel = new JLabel(dealerImage);
        dealerImagePanel.setLayout(new BorderLayout());
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
        dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
        dealerImagePanel.repaint();
        soundUtil.stopBackgroundSound();
        JOptionPane.showMessageDialog(frame, "Nível "+(levelCont+1));
        gamePanel.setBackgroundImage("assets/tables/mesa" + levelCont + ".png");
        soundUtil.playBackgroundSound("assets/sounds/nivel" + levelCont + ".wav");


        livesHandler();
    }

    private static void playerDefeated () throws InterruptedException {
        ImageIcon dealerImage;

        if (player.getLives() - 1 == 0) {
            JOptionPane.showMessageDialog(frame, "Suas tentativas acabaram!");
            levelCont = 0;
            player.resetLives();
            gamePanel.setBackgroundImage("assets/tables/mesa" + levelCont + ".png");
            soundUtil.stopBackgroundSound();
            soundUtil.playBackgroundSound("assets/sounds/nivel" + levelCont + ".wav");
            dealerImage = new ImageIcon(dealers.get(levelCont).getIcon());
        } else {
            player.removeLives();
            dealerImage = new ImageIcon(dealers.get(levelCont).getIconDefeat());
        }        

        updateDealerCards(true);
        dealerImagePanel.removeAll();
        
        JLabel dealerImageLabel = new JLabel(dealerImage);
        dealerImagePanel.setLayout(new BorderLayout());
        dealerImageLabel.setHorizontalAlignment(JLabel.CENTER);
        dealerImagePanel.add(dealerImageLabel, BorderLayout.SOUTH);
        dealerImagePanel.revalidate();
        dealerImagePanel.repaint(); 

        livesHandler();  

        resetGame();
    }

    private static void playerDrawCard() throws InterruptedException {
        player.hand.addCard(game.giveCard());
        updatePlayerCards();

        soundUtil.playSoundEffect("assets/sounds/card.wav");

        if (player.hand.getHandValue() > 21) {
            JOptionPane.showMessageDialog(frame, "Você estourou! Dealer vence.");
            playerDefeated();
        }
    }

    private static void dealerTurn() throws InterruptedException {
        updateDealerCards(true);
        while (dealers.get(levelCont).decisionMaking() == Enemy.GameAction.BUY) {
            dealers.get(levelCont).hand.addCard(game.giveCard());
            updateDealerCards(true);
        }
        
        int dealerPoints = dealers.get(levelCont).hand.getHandValue();
        int playerPoints = player.hand.getHandValue();

        if (dealerPoints > 21) {
            JOptionPane.showMessageDialog(frame, "Dealer estourou! Você vence.");

            updateLevel();
        } else if (dealerPoints > playerPoints) {
            JOptionPane.showMessageDialog(frame, "Dealer vence com " + dealerPoints + " pontos.");
            
            playerDefeated();
        } else if (dealerPoints < playerPoints) {
            JOptionPane.showMessageDialog(frame, "Você vence com " + playerPoints + " pontos.");

            updateLevel();
        } else {
            JOptionPane.showMessageDialog(frame, "Empate!");            
        }
        resetGame();
    }

    private static void updatePlayerCards() throws InterruptedException {
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

    private static void updateDealerCards(boolean showAll) throws InterruptedException {
        dealerPanel.removeAll();
        int c = 0;
        for (Card card : dealers.get(levelCont).hand.getCards()) {
            ImageIcon cardIcon;

            if (c == 0 && !showAll) {
                cardIcon = new ImageIcon("assets/cards/back.png");
            } else {
                cardIcon = new ImageIcon("assets/cards/" + card.getImageName());
            }

            Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel cardLabel = new JLabel(scaledIcon);
            dealerPanel.add(cardLabel);
            c += 1;
            dealerPanel.revalidate();
            dealerPanel.repaint();
        }
    }

    private static void resetGame() throws InterruptedException {
        playerPanel.removeAll();
        dealerPanel.removeAll();
        
        playerPanel.revalidate();
        playerPanel.repaint();

        dealerPanel.revalidate();
        dealerPanel.repaint();
        
        dealerImagePanel.revalidate();
        dealerImagePanel.repaint();
        
        game.buildDeck();
        game.shuffleDeck();

        runRound();
    }

    private static void livesHandler() {
        livesPanel.removeAll();
        ImageIcon livesImage = new ImageIcon("assets/lives/vidas-" + player.getLives() + ".png");
        Image scaledImage = livesImage.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel livesImageLabel = new JLabel(scaledIcon);
        livesImageLabel.setHorizontalAlignment(JLabel.LEFT);
        livesPanel.add(livesImageLabel);

        livesPanel.revalidate();
        livesPanel.repaint();
    }
    private static void finalScreen() {
        soundUtil.playBackgroundSound("assets/sounds/finalscreensong.wav");

        frameFinalScreen.setTitle("BlackBerry");
        frameFinalScreen.setSize(1920, 1080);
        frameFinalScreen.setResizable(true);
        frameFinalScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameFinalScreen.setLocationRelativeTo(null); 


        BackgroundPanel finalPanel = new BackgroundPanel("assets/backgrounds/barryfinal.png");
        finalPanel.setLayout(new BorderLayout());
        frameFinalScreen.getContentPane().add(finalPanel);

        frameFinalScreen.setVisible(true);            
    }
}
