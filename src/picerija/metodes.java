package picerija;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class metodes {
	public static void saktPasutijumu() {
		String name = JOptionPane.showInputDialog("Ievadiet pircēja vārdu:");
        if (name == null) return;

        String phone = JOptionPane.showInputDialog("Ievadiet telefona numuru:");
        if (phone == null) return;
    
        String[] picasVeidi = {"Margarita", "Kapri", "Studentu", "Havajas"};
        String veids = (String) JOptionPane.showInputDialog(null, "Izvēlieties picu:", "Picas izvēle",
                JOptionPane.QUESTION_MESSAGE, null, picasVeidi, picasVeidi[0]);

        String[] izmeri = {"Mazā", "Vidēja", "Lielā"};
        String izmers = (String) JOptionPane.showInputDialog(null, "Izvēlieties izmēru:", "Izmērs",
                JOptionPane.QUESTION_MESSAGE, null, izmeri, izmeri[0]);

       
        Picca pica = new Picca(veids, izmers, "Tomātu");

    
        String[] piedevas = {"Dubultais siers", "Bekons", "Sēnes", "Sīpoli", "Olīvas"};
        while (true) {
            int result = JOptionPane.showConfirmDialog(null, "Vai vēlaties pievienot papildus piedevas?", "Piedevas", JOptionPane.YES_NO_OPTION);
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

        
	}
	public static double aprekinatCenu(Picca pizza, boolean isDelivery) {
		double total = pizza.getBasePrice();
		total +=
				pizza.getToppings().size()*1.20;
		if(isDelivery == true) {
			total += 3.50;
		}
		return total;
	}

public static ArrayList<String>lasitNoFaila(){
	ArrayList<String> vesture = new ArrayList<>();
	try (BufferedReader reader = new BufferedReader(new FileReader("pasutijumi.txt"))){
		String line;
		while((line = reader.readLine()) !=null) {
			vesture.add(line);
		}
	}catch (IOException e) {
		JOptionPane.showMessageDialog(null,"Fails nav izveidots vai ir tukšs","Kļuda",JOptionPane.ERROR_MESSAGE);
	}
	return vesture;
}

}
