public class NoTriples {
    public static boolean noTriples(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i-1] == nums[i] && nums[i] == nums[i+1])
                return false;   
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums= new int[args.length];
        for (int i = 0; i < args.length; i++) {
            nums[i] = Integer.parseInt(args[i]);
        }
        System.out.println(noTriples(nums));
    }
}