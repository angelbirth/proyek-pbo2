package pbo;

//import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.OperationNotSupportedException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Connection c = null;
        try {
            c = new Connection(Connection.MYSQL, "localhost/pbo", "pbo", "pbo");
            c.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        } finally {
            if (c.getConnection() != null)
                System.out.println("Berhasil");
            else
                System.out.println("gagal");
        }
    }
}
