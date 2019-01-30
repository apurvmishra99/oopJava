
public class MeanVariance {

	public static void main(String[] args) {
		double[] nums = new double[args.length];
		double sum = 0.0;
		for (int i =0; i < args.length; i++) {
			nums[i] = Double.parseDouble(args[i]);
			sum += nums[i];
		}

		double mean = sum / nums.length;
		
		double varianceTemp = 0.0;
		for (int i =0; i < nums.length; i++) {
			varianceTemp += Math.pow((nums[i] - mean), 2.0);
		}
		double variance = varianceTemp / nums.length ;
		System.out.println(mean);
		System.out.println(variance);
		
	}

}
