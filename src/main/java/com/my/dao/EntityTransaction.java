package com.my.dao;

import com.my.exception.DaoException;
import com.my.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private static final Logger LOG = LogManager.getLogger(EntityTransaction.class);
    private Connection con;
    public void init(AbstractDao<?> dao) throws DaoException {

        if (con == null) {
            try {
                con = ConnectionPool.getConnection();
            } catch (SQLException e) {
                String message = MessageManager.getProperty("message.err.cant_cr_con");
                LOG.error(message);
                throw new DaoException(message, e);
            }
        }
        LOG.debug("Connection: " + con);
        dao.setConnection(con);
    }
    public void end() throws DaoException {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                String message = MessageManager.getProperty("message.err.cant_cl_con");
                LOG.error(message);
                throw new DaoException(message, e);
            }
        }
    }
}
