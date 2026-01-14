package picerija;

import java.awt.List;
import java.util.ArrayList;

public class Picca {
	
	 private String name;
	 private String size;
	 private ArrayList<String> toppings;
	 private String sauce;
	 private double basePrice;
	 private double price;
	 
	 public Picca(String name, String size, String sauce) {
		 this.name = name;
		 this.size = size;
		 this.sauce = sauce;
		 this.toppings = new ArrayList<>();
		 this.basePrice = 0.0;
	 }
	private double calcBasePrice(String size) {
		switch(size.toLowerCase())
		{
		case"small":
		case"mazā":
			
			return 5.0;
			
		case"medium":
		case"vidēja":
			
			return 8.0;
			
		case"large":
		case"lielā":
			
			return 12.0;
			
			default:
			
			return 8.0;
					
		}
	}
	
	public void addTopping(String topping) {
		this.toppings.add(topping);
	}
	public String getName() {
		return name;
	}
	public String getSize() {
		return size;
	}
	public ArrayList<String>getToppings(){
		return toppings;
	}
	public double getBasePrice() {
		return basePrice;
	}
}
