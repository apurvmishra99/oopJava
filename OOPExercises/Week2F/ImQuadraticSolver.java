public class ImQuadraticSolver {
	public static void main(String[] args) {
		double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double c = Double.parseDouble(args[2]);
        if (a == 0.0) {
            System.out.println(-c/b);
        }
        else if ((b*b - 4*a*c) >= 0) {
            System.out.println((-(b) + Math.sqrt(b*b - 4*a*c))/2*a);
            System.out.println((-(b) - Math.sqrt(b*b - 4*a*c))/2*a);
        }
        else {
			double real = -b/2*a;
			double im = Math.sqrt(Math.abs(b*b - 4*a*c))/2*a;
			System.out.println(real + " + " + im + "i");
			System.out.println(real + " - " + im + "i");

		};        
    }
}