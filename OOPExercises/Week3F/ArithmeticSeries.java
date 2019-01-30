
public class ArithmeticSeries {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int i = 0;
		int sum = 0;
		while (i <= num) {
			sum += i;
			i++;
		}
		System.out.println(sum);
	}

}
