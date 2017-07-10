package com.testOrderSummary;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;






import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/*import com.orderCode.Calculator;
import com.orderCode.Item;
import com.orderCode.Order;
import com.orderCode.OrderLine;
*/
public class TestOrderCode {
	
	@Before
    public void setUp() throws Exception {
        
        
    }

	
	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {
		Map<String, Order> orderDetails = new LinkedHashMap<String, Order>();// Used LinkedHashmap to maintain the insertion order

		Order cart = new Order();
		
		calculator computeOrder = new calculator();
        
		double grandTotal = 0;

		cart.add(new OrderLine(new Item("book", (float) 12.49), 1));
		cart.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		cart.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		
		orderDetails.put("Order 1", cart);		
		
		
		orderDetails.clear();
		// Reuse cart for an other order
		cart.clear();

		
		cart.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		cart.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		
		orderDetails.put("Order 2", cart);
		
		computeOrder.calculate(orderDetails);
		
		orderDetails.clear();
		// Reuse cart for an other order
		cart.clear();
		
		

		
		cart.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		cart.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		cart.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		cart.add(new OrderLine(new Item("box of imported chocolates", (float) 11.25), 1));

		
		orderDetails.put("Order 3", cart);
		 grandTotal  = computeOrder.calculate(orderDetails);
		grandTotal=Math.floor(grandTotal * 100) / 100;
		System.out.println(grandTotal);
		Assert.assertEquals(153.8, grandTotal);
		
	}

}
