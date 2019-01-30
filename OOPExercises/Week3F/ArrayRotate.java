public class ArrayRotate {

	public static void main(String[] args) {
		
		int[] nums = new int[args.length];
		int[] copy = new int[args.length];
		
		for (int i =0; i < args.length; i++) {
			nums[i] = Integer.parseInt(args[i]);
		}
		
		for (int j =0; j < nums.length - 1; j++) {
			copy[j] = nums[j+1];
		}
		copy[nums.length - 1] = nums[0];
		for (int k =0; k < copy.length; k++) {
			System.out.print(copy[k] + " ");
		}
		System.out.println("");
	}

}
