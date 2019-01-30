import java.util.Arrays;

public class TempMedian {

	public static void main(String[] args) {
		int[] temps = new int[args.length];
		temps[0] = Integer.parseInt(args[0]);
		
		for (int i=1; i<args.length; i++) {
			
			if (args[i].equals(".")) {
				temps[i] = temps[i-1];
			} 
			else if (args[i].equals("+")) {
				temps[i] = temps[i-1] + 1;
			}
			else {
				temps[i] = temps[i-1]- 1;
			}
		}
		System.out.println(Arrays.toString(temps));
		Arrays.sort(temps);
		double median;
		if (temps.length % 2 == 0) {
			median = (temps[temps.length/2 - 1] + temps[temps.length/2])/2.0;
		}
		else {
			median = temps[(temps.length + 1)/2 - 1];
		}
		
		
		System.out.println(Arrays.toString(temps));
		System.out.println(median);
	}

}
