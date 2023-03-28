package associations.interfaces.implementations;

import associations.interfaces.contracts.DbConnection;

public class MySqlImp implements DbConnection {
    @Override
    public void connect() {
        System.out.println("connected with mysql");
    }
}
