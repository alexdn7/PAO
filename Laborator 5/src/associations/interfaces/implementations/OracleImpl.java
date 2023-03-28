package associations.interfaces.implementations;

import associations.interfaces.contracts.DbConnection;

public class OracleImpl implements DbConnection {
    @Override
    public void connect() {
        System.out.println("connected with oracle");
    }
}
