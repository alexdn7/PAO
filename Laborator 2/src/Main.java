import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        /*
         * primitive types:
         * -int
         * -short
         * -long
         * -byte
         * -double
         * -boolean
         * -float
         * -char
         */

        int intValue = 3;
        System.out.println(intValue);

        short shortValue = 4;
        System.out.println(shortValue);

        long longValue = 5;
        System.out.println(longValue);

        byte byteValue = 4;
        System.out.println(byteValue);

        double doubleValue = 4.5;
        System.out.println(doubleValue);

        float floatValue =  5.5f;
        System.out.println(floatValue);

        boolean booleanValue = false;
        System.out.println(booleanValue);

        char charValue = 'A';
        System.out.println(charValue);

        /*
         *  Wrapper classes
         * int -> Integer
         * short -> Short
         * long -> Long
         * byte -> Byte
         * double -> Double
         * float -> Float
         * boolean -> Boolean
         * char -> Character
         */

        Integer integerValue =  5;
        Integer integerValue2 = 6;
        int result = integerValue.compareTo(integerValue2);
        System.out.println(result);

/*     - Transformarea din primitive in wrapper class = boxing
       - Transformarea din wrapper class in clasa primitiva = unboxing */

//        int a = 5;
//        Integer b = a;
//
//        Integer c = 6;
//        int d = c;
//
//        char c  = 'a';
//
//        while(c <= 'z') {
//            System.out.print(c);
//            c++;
//        }
//
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIntroduceti prima valoare");
        int firstValue = scanner.nextInt();
        System.out.println("Introduceti a doua valoare");
        int secondValue = scanner.nextInt();
        System.out.println("Introduceti denumirea operatiei");
        String operation = scanner.next();

        switch (operation) {
            case "div" -> System.out.println((firstValue / secondValue));
            case "mul" -> System.out.println((firstValue * secondValue));
            case "add" -> System.out.println((firstValue + secondValue));
            case "minus" -> System.out.println((firstValue - secondValue));
            default -> System.out.println("Operatie incorecta");
        }
    }
}