/**
 * args value will be provided via container environment values.
 */

public class MultiplyNumbersOnContainers {

    public static void main(String[] args){

        Integer a = Integer.parseInt(args[0]);
        Integer b = Integer.parseInt(args[1]);
        System.out.println("Multiplying Number1:"+a+", Number2:"+b);
        System.out.println("Result:"+(a * b));
    }
}
