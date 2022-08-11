package com.my.dao;

import com.my.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    protected final static Logger LOG = LogManager.getLogger(AbstractDao.class);
    protected Connection con;

    public abstract boolean create(T entity);
    public abstract List<T> findAll();
    public abstract T findById(int id);
    public abstract T update(T entity);
    public abstract boolean delete(T entity);

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOG.error("cannot close statement", e);
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error("cannot close result set", e);
            }
        }
    }

    public void close(ResultSet rs, Statement stmt) {
        close(rs);
        close(stmt);
    }

    public void setConnection(Connection con) {
        this.con = con;
    }
}
