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
       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GalvenaKlase().setVisible(true);
        });
    }
}