import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class App {
    private static JPanel cardPanel;
    private static JPanel cardPanelDealer;
    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 135;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);
        frame.setTitle("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        ImageIcon icon = new ImageIcon(App.class.getResource("logo.png"));
        frame.setIconImage(icon.getImage());

        cardPanel = new JPanel();
        cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        cardPanelDealer = new JPanel();
        cardPanelDealer.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon buttonIcon = new ImageIcon(App.class.getResource("logo.png"));
        Image scaledImage = buttonIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        ImageIcon deleteIcon = new ImageIcon(App.class.getResource("assets/X.png"));
        Image scaledDelete = deleteIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledDeleteIcon = new ImageIcon(scaledDelete);

        JButton clearBord = new JButton(scaledDeleteIcon);
        clearBord.setBorderPainted(false);
        clearBord.setContentAreaFilled(false);

        JButton btnGetCard = new JButton(scaledIcon);
        btnGetCard.setBorderPainted(false);
        btnGetCard.setContentAreaFilled(false);

        JButton btnGetCardDealer = new JButton(scaledIcon);
        btnGetCardDealer.setBorderPainted(false);
        btnGetCardDealer.setContentAreaFilled(false);

        btnGetCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pegarCarta();
            }
        });

        btnGetCardDealer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pegarCartaDealer();
            }
        });

        clearBord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.revalidate();
                cardPanel.repaint();
                cardPanelDealer.removeAll();
                cardPanelDealer.revalidate();
                cardPanelDealer.repaint();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnGetCardDealer);
        buttonPanel.add(btnGetCard);
        buttonPanel.add(clearBord);

        JPanel mesaPanel = new JPanel(new BorderLayout());
        mesaPanel.add(buttonPanel, BorderLayout.NORTH);
        mesaPanel.add(cardPanelDealer, BorderLayout.CENTER);
        mesaPanel.add(cardPanel, BorderLayout.SOUTH);

        JPanel personagemPanel = new JPanel();
        personagemPanel.setLayout(new BorderLayout());

        ImageIcon personagemIcon = new ImageIcon(App.class.getResource("/assets/oponents/casal-programador.png"));
        JLabel personagemLabel = new JLabel(personagemIcon);
        personagemPanel.add(personagemLabel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mesaPanel, personagemPanel);
        splitPane.setDividerLocation(0.8);
        splitPane.setResizeWeight(0.8);

        frame.add(splitPane);
        frame.setVisible(true);
    }

    private static void pegarCarta() {
        ImageIcon cardImage = new ImageIcon(App.class.getResource("/assets/cards/1-C.png"));

        Image image = cardImage.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledCardImage = new ImageIcon(image);

        JLabel cardLabel = new JLabel(scaledCardImage);
        cardPanel.add(cardLabel);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private static void pegarCartaDealer() {
        ImageIcon cardImage = new ImageIcon(App.class.getResource("/assets/cards/back.png"));

        Image image = cardImage.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledCardImage = new ImageIcon(image);

        JLabel cardLabel = new JLabel(scaledCardImage);
        cardPanelDealer.add(cardLabel);
        cardPanelDealer.revalidate();
        cardPanelDealer.repaint();
    }
}
