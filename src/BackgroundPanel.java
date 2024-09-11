import java.awt.*;
import javax.swing.*;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // Construtor para carregar a imagem inicial de fundo
    public BackgroundPanel(String filePath) {
        setBackgroundImage(filePath); // Inicializa com a imagem fornecida
    }

    // Método para alterar a imagem de fundo dinamicamente
    public void setBackgroundImage(String filePath) {
        try {
            backgroundImage = new ImageIcon(filePath).getImage();
            repaint(); // Repaint para atualizar a tela com a nova imagem
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
