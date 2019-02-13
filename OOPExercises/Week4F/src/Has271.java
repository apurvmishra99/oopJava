public class Has271 {

    public static boolean has271(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {
            if ((nums[i] == nums[i+1] - 5) && (nums[i] == nums[i+2] - 1))
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[] numbers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            numbers[i] = Integer.parseInt(args[i]);
        }
        System.out.println(has271(numbers));
    }
}