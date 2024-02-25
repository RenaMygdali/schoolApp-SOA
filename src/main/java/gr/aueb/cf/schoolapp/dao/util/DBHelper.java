package gr.aueb.cf.schoolapp.dao.util;

import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class που εκκαθαρίζει τους πίνακες της ΒΔ από τα
 * dummy data και αρχικοποιεί τα sequences των auto-increment πεδίων
 * (του ID) στο 1.
 */
public class DBHelper {

    /**
     * No instances of this class should be available.
     */
    private DBHelper() {}

    public static void eraseData() throws SQLException {
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnection();
            connection.prepareStatement("SET @@foreign_key_checks = 0").executeUpdate();

            rs = connection.prepareStatement("SELECT TABLE_NAME FROM information_schema.tables " +
                    "WHERE TABLE_SCHEMA = 'SchoolApp'").executeQuery();

            List<String> tables = mapRSToList(rs);
            for (String table : tables) {
                connection.prepareStatement("DELETE FROM " + table).executeUpdate();
                connection.prepareStatement("ALTER TABLE " + table + " AUTO_INCREMENT = 1").executeUpdate();
            }
            connection.prepareStatement("SET @@foreign_key_checks = 1").executeUpdate();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Αντιγράφει τα δεδομένα του Result Set σε ένα List<String>.
     *
     * @param rs    the result set with the data to be mapped.
     * @return      the list with the mapped (copied) data.
     * @throws SQLException
     */
    private static List<String> mapRSToList(ResultSet rs) throws SQLException {
        List<String> tables = new ArrayList<>();

        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }
}
