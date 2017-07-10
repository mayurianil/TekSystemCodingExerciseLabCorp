package com.testOrderSummary;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.Assert;

public class TestTotal {

	@SuppressWarnings("deprecation")
	@Test
	public void test1() throws Exception {
		Map<String, Order> orderDetails = new LinkedHashMap<String, Order>();// Used LinkedHashmap to maintain the insertion order

		Order cart = new Order();
		
		calculator computeOrder = new calculator();
        
		double grandTotal = 0;

		cart.add(new OrderLine(new Item("book", (float) 12.49), 1));
		cart.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		cart.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		
		orderDetails.put("Order 1", cart);
		double t1=computeOrder.calculate(orderDetails);
		System.out.println(t1);
		Assert.assertEquals(28.32, Math.floor(t1 * 100) / 100 );
}
	@Test
	public void test2() throws Exception {
		Map<String, Order> orderDetails = new LinkedHashMap<String, Order>();// Used LinkedHashmap to maintain the insertion order

		Order cart = new Order();
		
		calculator computeOrder = new calculator();
        
		double grandTotal = 0;

		cart.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		cart.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		
		orderDetails.put("Order 2", cart);
		double t2=computeOrder.calculate(orderDetails);
		System.out.println(t2);
		Assert.assertEquals(57.5, Math.floor(t2 * 100) / 100 );
}
	@Test
	public void test3() throws Exception {
		Map<String, Order> orderDetails = new LinkedHashMap<String, Order>();// Used LinkedHashmap to maintain the insertion order

		Order cart = new Order();
		
		calculator computeOrder = new calculator();
        
		double grandTotal = 0;

		cart.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		cart.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		cart.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		cart.add(new OrderLine(new Item("box of imported chocolates", (float) 11.25), 1));

		
		orderDetails.put("Order 3", cart);
		double t3=computeOrder.calculate(orderDetails);
		System.out.println(t3);
		Assert.assertEquals(67.97, Math.floor(t3 * 100) / 100 );
	
}
}

