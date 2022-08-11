package com.my.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private final static Logger LOG = LogManager.getLogger(ConnectionPool.class);

    private static DataSource ds;

    public static Connection getConnection() throws SQLException {
        if (ds == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext  = (Context)initContext.lookup("java:/comp/env");
                ds = (DataSource)envContext.lookup("jdbc/citylib");
            } catch (NamingException e) {
                LOG.error("cannot create datasource", e);
            }
        }
        return ds.getConnection();
    }
}
