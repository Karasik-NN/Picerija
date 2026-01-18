package picerija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class GalvenaKlase extends JFrame {

    private JLabel background;
    private ImageIcon originalIcon;
    private JButton btnOrder, btnMenu, btnHistory, btnExit;
    
    public GalvenaKlase() {
        setTitle("Picu Pasaule - Galvenā Izvēlne");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        originalIcon = new ImageIcon("images/fon.jpg");

        background = new JLabel();
        setContentPane(background);
        background.setLayout(null); 

        btnOrder = createButton("Sākt Pasūtījumu");
        btnMenu = createButton("Apskatīt Menu");
        btnHistory = createButton("Vēsture");
        btnExit = createButton("Iziet");

        btnOrder.addActionListener(e -> metodes.saktPasutijumu());
        btnMenu.addActionListener(e -> showPizzaMenu());
        
        btnHistory.addActionListener(e -> {
            ArrayList<String> dati = metodes.lasitNoFaila();
            if (dati.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vēsture ir tukša!", "Informācija", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String vestureText = String.join("\n", dati);
                JTextArea textArea = new JTextArea(vestureText);
                textArea.setEditable(false);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(500, 400));
                
                JOptionPane.showMessageDialog(this, scrollPane, "Pasūtījumu vēsture", JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnExit.addActionListener(e -> System.exit(0));

        background.add(btnOrder);
        background.add(btnMenu);
        background.add(btnHistory);
        background.add(btnExit);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayout();
            }
        });
        
        updateLayout();
    }

    private void updateLayout() {
        int w = this.getWidth();
        int h = this.getHeight();
        if (w > 0 && h > 0) {
            Image img = originalIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            background.setIcon(new ImageIcon(img));
            int btnW = 220;
            int btnH = 50;
            int startY = h / 3;
            int gap = 70;
            int centerX = (w / 2) - (btnW / 2);
            btnOrder.setBounds(centerX, startY, btnW, btnH);
            btnMenu.setBounds(centerX, startY + gap, btnW, btnH);
            btnHistory.setBounds(centerX, startY + gap * 2, btnW, btnH);
            btnExit.setBounds(centerX, startY + gap * 3, btnW, btnH);
        }
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(Color.ORANGE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void showPizzaMenu() {
        JFrame menuFrame = new JFrame("Picu Menu");
        menuFrame.setSize(750, 600);
        menuFrame.setLayout(new GridLayout(0, 2, 10, 10)); 
        menuFrame.add(createPizzaCard("Margarita", "5.00 - 12.00 EUR", "images/margarita.jpg"));
        menuFrame.add(createPizzaCard("Kapri", "6.00 - 14.00 EUR", "images/kapri.jpg"));
        menuFrame.add(createPizzaCard("Studentu", "4.50 - 10.00 EUR", "images/studentu.jpg"));
        menuFrame.add(createPizzaCard("Havajas", "6.50 - 15.00 EUR", "images/havajas.jpg"));
        menuFrame.add(createPizzaCard("Sicilijas", "6.00 - 13.00 EUR", "images/Sicilijas.jpg"));
        menuFrame.add(createPizzaCard("Pepperoni", "5.50 - 14.50 EUR", "images/Pepperoni.jpg")); 
        menuFrame.add(createPizzaCard("Čili", "5.00 - 13.00 EUR", "images/Čili.jpg"));
        menuFrame.add(createPizzaCard("Kalifornijas", "6.50 - 13.50 EUR", "images/Kalifornijas.jpg"));
        menuFrame.setLocationRelativeTo(this);
        menuFrame.setVisible(true);
    }

    private JPanel createPizzaCard(String title, String price, String imgPath) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel imgLabel = new JLabel("", SwingConstants.CENTER);
        try {
            ImageIcon icon = new ImageIcon(imgPath);
            Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imgLabel.setText("Nav bildes");
        }
        panel.add(imgLabel, BorderLayout.CENTER);
        panel.add(new JLabel(title + " (" + price + ")", SwingConstants.CENTER), BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GalvenaKlase().setVisible(true));
    }
}