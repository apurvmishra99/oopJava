public class LargestDouble {
    public static void main(String[] args) {
        double num_1 = Double.parseDouble(args[0]);
        double num_2 = Double.parseDouble(args[1]);
        System.out.println(Math.max(num_1, num_2));

        if (num_1 > num_2) {
            System.out.println(num_1);
        }
        else if (num_1 < num_2) {
            System.out.println(num_2);
        }
        else System.out.println("They are equal.");
    }
}