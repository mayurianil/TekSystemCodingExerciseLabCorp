package com.orderCode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* ****************************************************************************************

 Please remove all bugs from the code below to get the following output:

 <pre>

 *******Order 1*******
 1 book: 13.74
 1 music CD: 16.49
 1 chocolate bar: 0.94
 Sales Tax: 2.84
 Total: 28.33
 *******Order 2*******
 1 imported box of chocolate: 11.5
 1 imported bottle of perfume: 54.62
 Sales Tax: 8.62
 Total: 57.5
 *******Order 3*******
 1 Imported bottle of perfume: 32.19
 1 bottle of perfume: 20.89
 1 packet of headache pills: 10.73
 1 box of imported chocolates: 12.94
 Sales Tax: 8.77
 Total: 67.98
 Sum of orders: 153.81

 </pre>

 ******************************************************************************************** */

/*
 * represents an item, contains a price and a description.
 *
 */
class Item {

	private String description;
	private float price;

	public Item(String description, float price) {
		super();
		this.description = description;
		this.price = price;
	}

	// To get the String representation
	@Override
	public String toString() {
		return "Item [description=" + description + ", price=" + price + "]";
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}
}

/*
 * represents an order line which contains the @link Item and the quantity.
 */
class OrderLine {

	private int quantity;
	private Item item;

	/*
	 * @param item Item of the order
	 * 
	 * @param quantity Quantity of the item
	 */
	public OrderLine(Item item, int quantity) throws Exception {
		if (item == null) {
			System.err.println("ERROR - Item is NULL");
			throw new Exception("Item is NULL");
		}
		assert quantity > 0;
		this.item = item;
		this.quantity = quantity;

	}

	// To get the String representation
	@Override
	public String toString() {
		return "OrderLine [quantity=" + quantity + ", item=" + item + "]";
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
}

class Order {

	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}

		orderLines.add(o);
	}

	public int size() {
		return orderLines.size();
	}

	public OrderLine get(int i) {
		return orderLines.get(i);
	}

	public void clear() {
		this.orderLines.clear();
	}
}

class Calculator {

	double grandtotal = 0;

	public static double rounding(double value) {
		return (double) (Math.round(value * 100)) / 100;
	}

	/**
	 * receives a collection of orders. For each order, iterates on the order
	 * lines and calculate the total price which is the item's price * quantity
	 * * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without
	 * taxes for this order
	 */
	public double calculate(Map<String, Order> orderDetails) {

		// double grandtotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : orderDetails.entrySet()) {

			// for(String key: o.keySet()) {
			System.out.println("*******" + entry.getKey() + "*******");

			// grandtotal = 0; //removed

			Order r = entry.getValue();

			double totalTax = 0;
			double total = 0;

			// Iterate through the items in the order
			for (int i = 0; i < r.size(); i++) {

				// Calculate the taxes
				double tax = 0;

				// used toLoweCase()
				if (r.get(i).getItem().getDescription().toLowerCase()
						.contains("imported")) {
					tax = rounding(r.get(i).getItem().getPrice() * 0.15); // Extra
																			// 5%
																			// tax
																			// on
					// imported items
				} else {
					tax = rounding(r.get(i).getItem().getPrice() * 0.10);
				}

				// Calculate the total price
				double totalprice = r.get(i).getItem().getPrice() + (tax); // removed
																			// Math.floor

				// Print out the item's total price
				System.out.println(r.get(i).getQuantity() + " "
						+ r.get(i).getItem().getDescription() + ": "
						+ (rounding(totalprice))); // removed Math.floor instead
													// used rounding()

				// Keep a running total
				totalTax += tax;
				total += r.get(i).getItem().getPrice();
			}

			// Print out the total taxes
			System.out.println("Sales Tax: " + rounding(totalTax)); // called
																	// rounding()
																	// for
																	// totalTax

			// total = total + totalTax;// removed

			// Print out the total amount
			System.out.println("Total: " + Math.floor(total * 100) / 100);
			grandtotal += total;
		}

		// System.out.println("Sum of orders: " + Math.floor(grandtotal * 100) /
		// 100);
		return grandtotal;
	}
}

public class OrderSummary {

	public static void main(String[] args) throws Exception {

		Map<String, Order> orderDetails = new LinkedHashMap<String, Order>();// Used
																				// LinkedHashMap
																				// to
																				// maintain
																				// the
																				// insertion
																				// Order

		Order cart = new Order();

		Calculator computeOrder = new Calculator();

		double grandTotal = 0;

		cart.add(new OrderLine(new Item("book", (float) 12.49), 1));
		cart.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		cart.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		orderDetails.put("Order 1", cart);
		computeOrder.calculate(orderDetails);
		// Clear the map record for Order 1
		orderDetails.clear();
		// Reuse cart for an other order
		cart.clear();

		cart.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		cart.add(new OrderLine(new Item("imported bottle of perfume",
				(float) 47.50), 1));

		orderDetails.put("Order 2", cart);
		computeOrder.calculate(orderDetails);
		// Clear the map record for Order 2
		orderDetails.clear();
		// Reuse cart for an other order
		cart.clear();

		cart.add(new OrderLine(new Item("Imported bottle of perfume",
				(float) 27.99), 1));
		cart.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		cart.add(new OrderLine(new Item("packet of headache pills",
				(float) 9.75), 1));
		cart.add(new OrderLine(new Item("box of imported chocolates",
				(float) 11.25), 1));

		orderDetails.put("Order 3", cart);

		grandTotal = computeOrder.calculate(orderDetails);

		System.out.println("Sum of orders: " + Math.floor(grandTotal * 100)
				/ 100);
	}
}
