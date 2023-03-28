package associations.interfaces;

import associations.interfaces.functionalinterface.MyFunctionalInterface;
import associations.interfaces.implementations.MySqlImp;
import associations.interfaces.implementations.OracleImpl;

import java.sql.SQLOutput;

public class Main {
    MySqlImp mySql = new MySqlImp();
    OracleImpl oracleImpl = new OracleImpl();
//    mySql.connect();
//    oracleImpl.connect();

//    MyFunctionalInterface m1 = () -> System.out.println("test");
//    m1.print();

//    MyFunctionalInterface m2 = (n1, n2) -> System.out.println(n1 + n2);
//    m2.sum(2, 3);
}
