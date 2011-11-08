/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

import javax.ejb.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import java.rmi.*;
import Model.Util.*;
import java.io.*;

/**
 *
 * @author sikorskyi
 */
public class ConnectionUtil {

    protected Connection getConnection(EntityContext context) throws SQLException, NamingException {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("Config.properties"));
            DataSource ds = (DataSource) context.lookup(prop.getProperty("JNDI_DATASOURCE_NAME"));
            Connection connection = ds.getConnection();
            return connection;
        } catch (IOException e) {
            throw new NamingException("Properties Exception: " + e.getMessage());
        }
    }

    /**
     * @throws SQLException
     */
    protected void close(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }
}
