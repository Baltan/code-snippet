package jdbc_test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author Baltan
 * @date 2021/1/22 09:31
 */
public class Test1 {
    public static void main(String[] args) {
        Connection newConnection = null;
        Connection oldConnection = null;
        String database = "database";
        String username = "username";
        String password = "password";

        try {
            newConnection = createConnection("jdbc:mysql://127.0.0.1:3306/" + database, username, password);
            List<String> tables1 = getAllTables(newConnection);

            for (String tableName : tables1) {
                getCount(tableName, newConnection);
            }

            System.out.println("---------------------------------------------------------");

            oldConnection = createConnection("jdbc:mysql://127.0.0.1:3306/" + database, username, password);
            List<String> table2 = getAllTables(oldConnection);

            for (String tableName : table2) {
                getCount(tableName, oldConnection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyConnection(newConnection);
            destroyConnection(oldConnection);
        }
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private static List<String> getAllTables(Connection connection) throws SQLException {
        List<String> tableNames = new ArrayList<>();

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

        while (resultSet.next()) {
            System.out.printf("表名：%s，表类型：%s，表备注：%s，数据库：%s",
                    resultSet.getString("TABLE_NAME"),
                    resultSet.getString("TABLE_TYPE"),
                    resultSet.getString("REMARKS"),
                    resultSet.getString("TABLE_CAT")
            );
            System.out.println();
            tableNames.add(resultSet.getString("TABLE_NAME"));
        }
        System.out.println("---------------------------------------------------------------------");
        return tableNames;
    }

    private static void getCount(String tableName, Connection connection) throws SQLException {
        String sql = "select * from " + tableName;

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.printf("表%s共有%d个字段，%d条记录", tableName, columnCount, rowCount);
        System.out.println();
    }

    private static void destroyConnection(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
