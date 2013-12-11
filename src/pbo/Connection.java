package pbo;

//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.OperationNotSupportedException;

public class Connection {

    private java.sql.Connection conn = null;
    public static final String ORACLE = "jdbc:oracle://";
    public static final String MYSQL = "jdbc:mysql://";
    private String url, user, pass, type;

    public Connection() {
        url = null;
        user = null;
        pass = null;
        type = MYSQL;
    }

    public Connection(String serverType, String url, String user,
            String pass) throws OperationNotSupportedException {
        if (serverType != ORACLE && serverType != MYSQL)
            throw new IllegalArgumentException("Tipe server tidak dikenal");
        if (serverType == ORACLE)
            throw new OperationNotSupportedException(
                    "Sementara oracle tidak tersedia");
        this.url = url;
        this.user = user;
        this.pass = pass;
        type = serverType;
    }

    public void connect(String serverType, String url, String user,
            String password) throws SQLException,
            OperationNotSupportedException {
        if (serverType != ORACLE && serverType != MYSQL)
            throw new IllegalArgumentException("Tipe server tidak dikenal");
        if (serverType == ORACLE)
            throw new OperationNotSupportedException(
                    "Sementara oracle tidak tersedia");
        else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err
                        .println("Driver MySQL tidak tersedia, sudahkah diinstal?");
            }
            conn = DriverManager
                    .getConnection(serverType + url, user, password);
        }
    }

    public void connect() throws OperationNotSupportedException, SQLException {
        if (url != null && user != null && pass != null)
            connect(type, url, user, pass);
        else
            throw new IllegalStateException("Belum ada parameter koneksi");
    }

    public java.sql.Connection getConnection() {
        return conn;
    }

}
