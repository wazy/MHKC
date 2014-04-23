package edu.uncfsu.csc.MHKC.LatticeReduction;

import java.math.BigDecimal;
import java.util.Vector;

public class GramSchmidtTester {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BigDecimal e = new BigDecimal("1e-4");
		
		Vector<BigDecimal>[] vect = new Vector[3];
		
		Vector<BigDecimal> one = new Vector<BigDecimal>(3);
		Vector<BigDecimal> two = new Vector<BigDecimal>(3);
		Vector<BigDecimal> three = new Vector<BigDecimal>(3);
		
		one.add(new BigDecimal("1")); one.add(e); one.add(e);
		two.add(new BigDecimal("1")); two.add(e); two.add(new BigDecimal("0"));
		three.add(new BigDecimal("1")); three.add(new BigDecimal("0")); three.add(e);
		
		vect[0] = one;
		vect[1] = two;
		vect[2] = three;
		
		//vect = Utils.transpose(vect);
		
		Utils.print(GramSchmidt.process(vect));
		
	}

}
