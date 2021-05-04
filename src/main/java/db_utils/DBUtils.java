package db_utils;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Assert;
import utils.ConfigReader;

import java.sql.*;

public class DBUtils {
    private static Connection connection;
    private static Statement statement;
    private static QueryRunner queryRunner = new QueryRunner();
    private static final String JDBC_URL = buildUrl();


    public static String buildUrl(){
        String host = ConfigReader.getPropertiesValue("food.delivery.db.host");
        String port = ConfigReader.getPropertiesValue("food.delivery.db.port.number");
        String username = ConfigReader.getPropertiesValue("food.delivery.db.username");
        String password = ConfigReader.getPropertiesValue("food.delivery.db.password");
        return "jdbc:mysql://"+host+":"+port+"/{DB}?user="+username+"&password="+password;

    }
    public static void openDB() {
        open(ConfigReader.getPropertiesValue("food.delivery.db.table"));
    }

    public static void open(String database) {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(JDBC_URL.replace("{DB}", database));
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Can't establish connection to DB");
        }
    }

    public static boolean executeStatement(String sqlStatement, Object... params) {
        if (connection == null) openDB();
        try {
            if (params.length == 0) return statement.execute(sqlStatement);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertBean(String query, Object bean, String[] properties) {
        if (connection == null) openDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            queryRunner.fillStatementWithBean(preparedStatement, bean, properties);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean truncateTable(String tableName) {
        String sqlStatement = String.format("TRUNCATE Table %s;", tableName);
        return executeStatement(sqlStatement);
    }

    public static boolean deleteRecord(String table, String column, String value) {
        String statement = String.format("DELETE FROM %s WHERE %s = '%s'", table, column, value);
        return executeStatement(statement);
    }

    public static ResultSetHandler query(String query, Object... params) {
        return new ResultSetHandler(queryToRs(query, params));
    }

    public static ResultSet queryToRs(String query, Object... params) {
        if (connection == null) openDB();
        try {
            if (params.length == 0) statement.executeQuery(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Not Able to execute a query");
        }
        return null;
    }

    public static void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            connection = null;
            statement = null;
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Can't close connection to DB");
        }
    }
}