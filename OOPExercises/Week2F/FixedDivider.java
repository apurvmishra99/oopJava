public class FixedDivider {
    public static void main(String[] args) {
        double num = Double.parseDouble(args[0]);
        double den = Double.parseDouble(args[1]);
        if (den == 0) {
            System.out.println("Cannot divide by zero!");
        }
        else System.out.println(num/den);
    }
}