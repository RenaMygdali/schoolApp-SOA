package gr.aueb.cf.schoolapp.service.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private final static BasicDataSource ds = new BasicDataSource();
    private static Connection conn;

    /**
     * No instances of this class should be available so private constructor.
     */
    private DBUtil() {}


    // static block to config ds properties.
    static {
        ds.setUrl("jdbc:mysql://localhost:3306/SchoolApp?serverTimezone=UTC");
        ds.setUsername("schoolAppUser");

        // Κρατάμε έξω από την εφαρμογή τα password των DBs. Τα εισάγουμε στις μεταβλητές
        // περιβάλλοντος και τραβάμε από εκεί.
        ds.setPassword(System.getenv("schoolAppUserPass"));
        ds.setInitialSize(8);
        ds.setMaxTotal(32);
        ds.setMinIdle(8);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }


    public static Connection getConnection() throws SQLException {
        conn = ds.getConnection();
        return conn;
    }

    // Σε περίπτωση που δεν θέλουμε auto-close (δηλ, try-with-resources).

    public static void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
