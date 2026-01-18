package picerija;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class metodes {

    public static void saktPasutijumu() {
 
        String name = "";
        while (true) {
            name = JOptionPane.showInputDialog("Ievadiet pircēja vārdu:");
            if (name == null) return;
            if (name.trim().length() >= 2 && name.matches("[a-zA-ZāčēģīķļņšūžĀČĒĢĪĶĻŅŠŪŽ\\s]+")) {
                break;
            }
            JOptionPane.showMessageDialog(null, "Kļūda! Ievadiet derīgu vārdu (tikai burti).", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }

        String phone = "";
        while (true) {
            phone = JOptionPane.showInputDialog("Ievadiet telefona numuru (8 cipari):");
            if (phone == null) return;
            if (phone.matches("\\d{8}")) {
                break;
            }
            JOptionPane.showMessageDialog(null, "Kļūda! Numuram jābūt tieši 7 cipariem.", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    
        String[] picasVeidi = {"Margarita", "Kapri", "Studentu", "Havajas", "Pepperoni", "Čili", "Sicīlijas", "Kalifornijas"};
        String veids = (String) JOptionPane.showInputDialog(null, "Izvēlieties picu:", "Picas izvēle",
                JOptionPane.QUESTION_MESSAGE, null, picasVeidi, picasVeidi[0]);
        if (veids == null) return;

        String[] izmeri = {"Mazā", "Vidēja", "Lielā"};
        String izmers = (String) JOptionPane.showInputDialog(null, "Izvēlieties izmēru:", "Izmērs",
                JOptionPane.QUESTION_MESSAGE, null, izmeri, izmeri[0]);
        if (izmers == null) return;

        Picca pica = new Picca(veids, izmers, "Tomātu");

        String[] piedevas = {"Dubultais siers", "Bekons", "Sēnes", "Sīpoli", "Olīvas"};
        while (true) {
            int result = JOptionPane.showConfirmDialog(null, "Vai vēlaties pievienot papildus piedevas (1.20€/gab)?", "Piedevas", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                String izveletaPiedeva = (String) JOptionPane.showInputDialog(null, "Izvēlieties piedevu:", "Piedevas",
                        JOptionPane.QUESTION_MESSAGE, null, piedevas, piedevas[0]);
                if (izveletaPiedeva != null) {
                    pica.addTopping(izveletaPiedeva);
                }
            } else {
                break;
            }
        }

        String[] dzerieni = {"Nav", "Coca-Cola (1.50€)", "Fanta (1.50€)", "Ūdens (1.00€)", "Kafija (2.00€)"};
        String izveletaisDzeriens = (String) JOptionPane.showInputDialog(null, "Izvēlieties dzērienu:", "Dzērieni",
                JOptionPane.QUESTION_MESSAGE, null, dzerieni, dzerieni[0]);
        if (izveletaisDzeriens == null) izveletaisDzeriens = "Nav";
        
        String[] uzkodas = {"Nav", "Frī kartupeļi (2.50€)", "Ķiploku grauzdiņi (3.00€)", "Sīpolu gredzeni (2.80€)"};
        String izveletaUzkoda = (String) JOptionPane.showInputDialog(null, "Izvēlieties uzkodu:", "Uzkodas",
                JOptionPane.QUESTION_MESSAGE, null, uzkodas, uzkodas[0]);
        if (izveletaUzkoda == null) izveletaUzkoda = "Nav";

        int deliveryOpt = JOptionPane.showConfirmDialog(null, "Vai nepieciešama piegāde (3.50€)?", "Piegāde", JOptionPane.YES_NO_OPTION);
        boolean isDelivery = (deliveryOpt == JOptionPane.YES_OPTION);
        String address = isDelivery ? JOptionPane.showInputDialog("Ievadiet piegādes adresi:") : "-";

        double galaSuma = aprekinatGaloCenu(pica, izveletaisDzeriens, izveletaUzkoda, isDelivery);
        Order pasutijums = new Order(name, phone, address, pica, izveletaisDzeriens, izveletaUzkoda, isDelivery);

        String ceks = String.format(
            "--- PICĒRIJAS ČEKS ---\n" +
            "KLIENTS: %s (%s)\n" +
            "ADRESE: %s\n" +
            "PICA: %s [%s] (Piedevas: %d)\n" +
            "DZĒRIENS: %s\n" +
            "UZKODA: %s\n" +
            "----------------------\n" +
            "KOPĀ: %.2f EUR", 
            name, phone, address, veids, izmers, pica.getToppings().size(), 
            izveletaisDzeriens, izveletaUzkoda, galaSuma
        );

        JOptionPane.showMessageDialog(null, ceks, "Pasūtījuma kopsavilkums", JOptionPane.INFORMATION_MESSAGE);
        saglabatFaila(ceks);
    }

    public static double aprekinatGaloCenu(Picca pizza, String dzeriens, String uzkoda, boolean isDelivery) {
        // Picas cena 
        double total = pizza.getTotalPizzaPrice(); 
        
        // Dzēriena cena
        if (dzeriens.contains("Coca-Cola") || dzeriens.contains("Fanta")) total += 1.50;
        else if (dzeriens.contains("Ūdens")) total += 1.00;
        else if (dzeriens.contains("Kafija")) total += 2.00;

        // Uzkodas cena
        if (uzkoda.contains("Frī")) total += 2.50;
        else if (uzkoda.contains("Ķiploku")) total += 3.00;
        else if (uzkoda.contains("Sīpolu")) total += 2.80;

        // Piegāde
        if (isDelivery) total += 3.50;

        return total;
    }

    private static final String FILE_NAME = "pasutijumi.txt";
    
    public static void saglabatFaila(String info) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(info);
            writer.newLine();
            writer.write("==========================================");
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kļūda saglabājot failā!", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<String> lasitNoFaila() {
        ArrayList<String> vesture = new ArrayList<>();
        File fails = new File(FILE_NAME);
        if (!fails.exists()) return vesture;

        try (BufferedReader reader = new BufferedReader(new FileReader(fails))) {
            String line;
            while ((line = reader.readLine()) != null) {
                vesture.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kļūda lasot vēsturi!", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
        return vesture;
    }
}