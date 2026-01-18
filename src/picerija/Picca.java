package picerija;

import java.util.ArrayList;

public class Picca {
    
    private String name;
    private String size;
    private ArrayList<String> toppings;
    private String sauce;
    private double basePrice;
    
    public Picca(String name, String size, String sauce) {
        this.name = name;
        this.size = size;
        this.sauce = sauce;
        this.toppings = new ArrayList<>();
        this.basePrice = calcBasePrice(size);
    }

    private double calcBasePrice(String size) {
     
        switch(size.toLowerCase()) {
            case "mazā":
            case "small":
                return 5.0;
            case "vidēja":
            case "medium":
                return 8.0;
            case "lielā":
            case "large":
                return 12.0;
            default:
                return 8.0;
        }
    }

  
    public double getTotalPizzaPrice() {
        double toppingsPrice = toppings.size() * 1.20; 
        return basePrice + toppingsPrice;
    }

    public void addTopping(String topping) {
        this.toppings.add(topping);
    }

    public String getName() { return name; }
    public String getSize() { return size; }
    public ArrayList<String> getToppings() { return toppings; }
    public double getBasePrice() { return basePrice; }
    public String getSauce() { return sauce; }
}