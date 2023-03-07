package singleton;

public class Main {
    public static void main(String[] args) {
        var singletonService1 = SingletonService.getInstance();
        var singletonService2 = SingletonService.getInstance();

        System.out.println(singletonService1.hashCode());
        System.out.println(singletonService2.hashCode());
    }
}
