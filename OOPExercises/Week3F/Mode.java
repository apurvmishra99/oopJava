import java.util.Arrays;
public class Mode {

	public static void main(String[] args) {
		int[] dataset = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			dataset[i] = Integer.parseInt(args[i]);
		}
		int[] count = {0,0,0,0,0,0,0,0,0,0};
		for (int j =0; j < dataset.length ; j++) {
			count[dataset[j]] += 1;
		}
		for (int l = 0; l < count.length; l++) {
			temp = "."* 
			System.out.println("[0s: %s] %s", );
		}
		
		int max = count[0];
	    for (int k = 0; k < count.length; k++) {
	        if (count[k] > max) {
	            max = count[k];
	        }
	    }
	    System.out.println(max);

	}
}
