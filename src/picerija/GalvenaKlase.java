package picerija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GalvenaKlase extends JFrame {

    public GalvenaKlase() {
        setTitle("Picu Pasaule - Galvenā Izvēlne");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        //fons
        JLabel background = new JLabel(new ImageIcon("fon.jpg"));//fonas bilde
        background.setBounds(0, 0, 800, 600);
        setContentPane(background);
        background.setLayout(null);

        //logo
        JLabel logo = new JLabel(new ImageIcon("image/logo.png")); //logo bilde
        logo.setBounds(300, 20, 200, 100); 
        background.add(logo);
       
        //pogas
        JButton btnOrder = createButton("Sākt Pasūtījumu", 300, 150);
        JButton btnMenu = createButton("Apskatīt Menu", 300, 220);
        JButton btnHistory = createButton("Vēsture", 300, 290);
        JButton btnExit = createButton("Iziet", 300, 360);
        
       
        }
    //izveidot pogu
    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 200, 50);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(Color.ORANGE);
        btn.setFocusPainted(false);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GalvenaKlase().setVisible(true);
        });
    }
}