import java.awt.*;
import javax.swing.*;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String filePath) {
        setBackgroundImage(filePath);
    }

    public void setBackgroundImage(String filePath) {
        try {
            backgroundImage = new ImageIcon(filePath).getImage();
            repaint();
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
