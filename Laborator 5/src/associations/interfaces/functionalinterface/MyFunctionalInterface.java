package associations.interfaces.functionalinterface;

@FunctionalInterface
public interface MyFunctionalInterface {
    void print();
//    void sum(int a, int b);
    default void m2() {

    }

    static void m3() {

    }
}
