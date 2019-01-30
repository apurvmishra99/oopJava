public class EvalBool {
    public static void main(String[] args) {
        boolean B1 = Boolean.parseBoolean(args[0]);
        boolean B2 = Boolean.parseBoolean(args[1]);
        boolean phi = (!(B1 && B2 ) && (B1 || B2 )) || ((B1 && B2) || !(B1 || B2));
        System.out.println("B1 : " + B1);
        System.out.println("B2 : " + B2);
        System.out.println("phi : " + phi);
    }
}