package src;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class MenuInicial extends JFrame {   
	Font pixelMplus;
    
    public MenuInicial(){

        try{
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("src\\PixelMplus10-Regular.ttf")).deriveFont(30f);	
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\PixelMplus10-Regular.ttf")));			
        }
        catch(IOException | FontFormatException e){}

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.black);

        setLayout(null);

        JLabel title = new JLabel("BlackBarry");
        title.setForeground(Color.WHITE);
        title.setFont(pixelMplus);
        title.setBounds(200, 50, 200, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        JButton btn_Iniciar = new JButton("Iniciar");
        btn_Iniciar.setBackground(Color.WHITE);
        btn_Iniciar.setBorder(null);
        btn_Iniciar.setFocusPainted(false);
        btn_Iniciar.setBounds(200, 140, 200, 50);
        btn_Iniciar.setFont(pixelMplus);
        btn_Iniciar.addActionListener(e -> {Game start = new Game();}); // Adicionar a função que vai chamar a tela do jogo em si
        add(btn_Iniciar);

        JButton btn_Sair = new JButton("Sair");
        btn_Sair.setBackground(Color.WHITE);
        btn_Sair.setBorder(null);
        btn_Sair.setFocusPainted(false);
        btn_Sair.setBounds(200, 220, 200, 50);
        btn_Sair.setFont(pixelMplus);
        btn_Sair.addActionListener(e -> System.exit(0)); 
        add(btn_Sair); 

        setVisible(true);
    }

}