package picerija;

public class Order {
private String customerName;
private String phoneNumber;
private String address;
private Picca pizza;
private boolean isDelivery;
private double totalPrice;


public Order(String customerName, String phoneNumber,
		String address, Picca pizza, boolean isDelivery) {
	this.customerName = customerName;
	this.phoneNumber = phoneNumber;
	this.address = address;
	this.pizza = pizza;
	this.isDelivery = isDelivery;
	this.totalPrice = metodes.aprekinatCenu(pizza, isDelivery);
	
}
}
