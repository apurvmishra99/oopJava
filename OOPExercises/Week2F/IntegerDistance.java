public class IntegerDistance {
    public static void main(String[] args) {
	   int num_1 = Integer.parseInt(args[0]);
	   int num_2 = Integer.parseInt(args[1]);
       System.out.println(Math.abs(num_1 - num_2));
       System.out.println(Math.max(num_1, num_2) - Math.min(num_1, num_2));
    }
}