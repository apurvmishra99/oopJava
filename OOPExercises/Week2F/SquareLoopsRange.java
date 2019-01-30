public class SquareLoopsRange {
    public static void main(String[] args) {
        int startNumber = Integer.parseInt(args[0]);
        int stopNumber = Integer.parseInt(args[1]);
        if (startNumber > stopNumber) {
            System.out.println("Start-limit greater han stop-limit.");
        }
        else {
            for (int i = startNumber; i <= stopNumber; i++) {
                System.out.print(i*i + " ");
            }
            System.out.println("\n");
        }
    }
}