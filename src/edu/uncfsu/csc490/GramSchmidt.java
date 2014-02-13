package edu.uncfsu.csc490;

import java.util.Vector;

public class GramSchmidt {

	private static double aij = 0;
	
	private static boolean reducedBasis = true;
	
	@SuppressWarnings("rawtypes")
	public static Vector[] execute(Vector[] v) {
		Vector[] v1 = v.clone();
		
		int n = v[0].size();
		
		for (int j = 1; j <= n-1; j++) {
			v1[j] = v[j];
			
			for (int i = 0; i <= (j-1); i++) {
				aij = (dotProduct(v1[i], v[j])) / (Math.pow(magnitude(v1[i]), 2));
				
				if (reducedBasis && Math.abs(aij) > .5) {
					reducedBasis = false;
				}
				
				v1[j] = subtract(v1[j], (scalarMult(aij, v1[i])));
			}
		}

		return v1;
	}
	
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
	
	@SuppressWarnings({ "rawtypes" })
	public static void isReducedBasis(Vector[] v, Vector[] v1) {
		if (reducedBasis) {
			System.out.println("M is a reduced basis for step a.");
			
			int n = v1[0].size();
			
			for (int j = 0; j < n-2; j++) {
				double rhs = .75 * Math.pow(magnitude(v1[j]), 2);
				
				double ajj = (dotProduct(v1[j], v[j+1])) / (Math.pow(magnitude(v1[j]), 2));
				Vector x = scalarMult(ajj, v1[j]); 
				x = add(v1[j+1], x);
				double lhs = Math.pow(magnitude(x), 2);
				
				if (lhs >= rhs) {
					reducedBasis = true;
				}
				else {
					reducedBasis = false;
				}
				
			}
		}
		else {
			System.out.println("\nM is not a reduced basis.");
			return;
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Vector a = new Vector(3);
		Vector b = new Vector(3);
		Vector c = new Vector(3);
		
		/* output for the first part of this algorithm using this data is: 
		 * [1, 1, 1]
		 * [1/3, -2/3, 1/3]
		 * [1/2, 0, -1/2]
		*/
		
		a.add(1); a.add(1); a.add(1);
		b.add(1); b.add(0); b.add(1);
		c.add(1); c.add(1); c.add(0);
		
		Vector[] v = new Vector[3];
		Vector[] v1 = new Vector[3];
		
		v[0] = a;
		v[1] = b;
		v[2] = c;
		
		v1 = execute(v);
				
		for (int j = 0; j < v1.length; j++) {
			for (int i = 0; i < v1[j].size(); i++) {
				System.out.print(v1[j].get(i) + " ");
			}
			System.out.print("\n");
		}
		
		// v = M
		// v1 = M*
		isReducedBasis(v, v1);

		
		if (reducedBasis) {
			System.out.println("\nM is a reduced basis.");
		}
		
	}
}
 