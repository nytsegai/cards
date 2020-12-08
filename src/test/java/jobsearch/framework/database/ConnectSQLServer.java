package jobsearch.framework.database;


import org.apache.commons.io.IOUtils;
import jobsearch.framework.logger.Logger;
import jobsearch.framework.testmanagement.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Updated Class to be used for managing connection to SQL Database;
 * <p>
 *
 * @author Iryna.Zhukava
 * Created  on 8/20/2018
 */
public class ConnectSQLServer {

    protected static final Logger logger = Logger.getInstance();

    private String dbServer;
    private String dbUser;
    private String dbPassword;
    private String databaseName;


    public ConnectSQLServer(String server, String dbName, String user, String password) {
        dbServer = server;
        databaseName = dbName;
        dbUser = user;
        dbPassword = password;

    }

    public ConnectSQLServer() {
//        dbServer = ApplicationConfig.DB_SERVER;
//        databaseName = ApplicationConfig.DB_NAME;
//        dbUser = ApplicationConfig.DB_USER;
//        dbPassword = ApplicationConfig.DB_PASSWORD;

    }

   public synchronized Connection connectToDatabase() throws ClassNotFoundException, SQLException {
        String dbURL = "jdbc:sqlserver://" + dbServer + ";allowMultiQueries=true";

        Connection connection = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

        return connection;
    }

    public String readRequestFromFile(String requestFileName) throws IOException {
        String sql = "";
        ClassLoader classLoader = ConnectSQLServer.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(requestFileName);
        sql = IOUtils.toString(new InputStreamReader(resourceAsStream, Constants.DEFAULT_ENCODING));
        sql = insertDatabaseName(sql);
        return sql;

    }

    private String insertDatabaseName(String sql) {
        String formattedStatement = String.format(sql, databaseName);
        return formattedStatement;
    }

    public void executeSimpleQuery(String sql) {
        try (Connection connection = connectToDatabase()) {


            if (connection != null) {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.execute();
            }

        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }


}

