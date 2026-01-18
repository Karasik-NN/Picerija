package picerija;

public class Order {
    private String customerName;
    private String phoneNumber;
    private String address;
    private Picca pizza;
    private String drink;
    private String snack;
    private boolean isDelivery;
    private double totalPrice;

    
    public Order(String customerName, String phoneNumber, String address, 
                 Picca pizza, String drink, String snack, boolean isDelivery) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pizza = pizza;
        this.drink = drink;
        this.snack = snack;
        this.isDelivery = isDelivery;
        
      
        this.totalPrice = metodes.aprekinatGaloCenu(pizza, drink, snack, isDelivery);
    }

   
    public double getTotalPrice() {
        return totalPrice;
    }
}