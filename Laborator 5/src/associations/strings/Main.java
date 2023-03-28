package associations.strings;

public class Main {
    public static void main(String[] args) {
//        String => imutabil
        String s1 = "my string";
        s1.concat("test");
        System.out.println(s1);

//        StringBuffer
//        StringBuilder

        StringBuilder sb1 = new StringBuilder("my string");
        sb1.append("test");
        System.out.println(sb1);

        StringBuffer sb2 = new StringBuffer("my string");
        sb2.append("test");
        System.out.println(sb2);
    }
}
