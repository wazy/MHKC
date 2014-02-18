package edu.uncfsu.csc490;

import java.util.Vector;

public class VectorOp {
	
	@SuppressWarnings("rawtypes")
	public static double dotProduct(Vector a, Vector b) {
		  if(a.size() != b.size()) {
			  System.out.println("Invalid dimensions - trying to find dot product");
			  System.exit(1);
		  }

		  double sum = 0;
		  for(int i = 0; i < a.size(); i++)
			  sum += Double.parseDouble(a.elementAt(i).toString()) * 
			  				Double.parseDouble(b.elementAt(i).toString()); 
		  
		  return sum;
		}
	
	@SuppressWarnings("rawtypes")
    public static double magnitude(Vector a) {
        return Math.sqrt(dotProduct(a, a));
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public static Vector subtract(Vector a, Vector b) {
		Vector c = (Vector)a.clone();
		for (int i = 0; i < a.size(); i++) {
			double value = Double.parseDouble(a.elementAt(i).toString()) - 
							Double.parseDouble(b.elementAt(i).toString()); 
			c.set(i, value);
		}
		return c;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public static Vector scalarMult(double scalar, Vector a) {
        Vector c = (Vector) a.clone();
		for (int i = 0; i < a.size(); i++) {
        	double value = scalar * Double.parseDouble(a.get(i).toString());
        	c.set(i, value);
        }
            
        return c;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    public static Vector add(Vector a, Vector b) {
		Vector c = (Vector)a.clone();
		for (int i = 0; i < a.size(); i++) {
			double value = Double.parseDouble(a.elementAt(i).toString()) + 
							Double.parseDouble(b.elementAt(i).toString()); 
			c.set(i, value);
		}
		return c;
    }
}
