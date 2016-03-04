package eu.bankersen.kevin.ql;

import java.math.BigDecimal;

public class test {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	BigDecimal x = new BigDecimal(new Double(2.5)).setScale(0, BigDecimal.ROUND_HALF_DOWN);
	
	
	BigDecimal y = new BigDecimal("10.0");
	
	
	System.out.println(y);
	

    }

}
