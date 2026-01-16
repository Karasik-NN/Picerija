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
