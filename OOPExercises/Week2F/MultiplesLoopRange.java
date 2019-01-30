public class MultiplesLoopRange {
	public static void main(String[] args) {
		int startNumber = Integer.parseInt(args[0]);
		int stopNumber = Integer.parseInt(args[1]);
		int multipleOf = Integer.parseInt(args[2]);

		if (startNumber > stopNumber) {
            for (int i=startNumber; i >= stopNumber; i--) {
				if (i%multipleOf == 0) {
					System.out.print(i*i + " ");
				}
			}
			System.out.println("\n");
        }
        else {
            for (int i = startNumber; i <= stopNumber; i++) {
				if (i%multipleOf == 0) {
					System.out.print(i*i + " ");
				}				
            }
            System.out.println("\n");
        }
	}
}