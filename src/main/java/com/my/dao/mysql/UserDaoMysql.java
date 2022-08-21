package com.my.dao.mysql;

import com.my.dao.UserDao;
import com.my.entity.User;
import com.my.exception.DaoException;
import com.my.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMysql extends UserDao {
    protected static final Logger LOG = LogManager.getLogger(UserDaoMysql.class);
    public static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT id, login, role_id, status_id FROM users WHERE login = ?";
    public static final String SQL_CHECK_LOGIN_PASS = "SELECT EXISTS(SELECT * FROM users WHERE login = ? AND password = ?)";
    public static final String SQL_CHECK_LOGIN = "SELECT EXISTS(SELECT * FROM users WHERE login = ?)";
    public static final String SQL_INSERT_USER = "INSERT INTO users (login, password) VALUES (?, ?)";
    public static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_FIND_BY_ID = "SELECT id, login, role_id, status_id FROM users WHERE id = ?";
    public static final String SQL_UPDATE_STATUS = "UPDATE users SET status_id = ? WHERE id = ?";
    public static final String SQL_UPDATE_USER = "UPDATE users SET id = ?, login = ?, role_id = ?, status_id = ? WHERE id = ?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                users.add(extractUser(rs));
            }
        } catch (SQLException e) {
            String message = "Cant find all users";
            LOG.error(message);
            throw new DaoException(message, e);
        } finally {
            close(rs, stmt);
        }
        return users;
    }

    @Override
    public User findById(int id) throws DaoException {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_FIND_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            String message = "Cant find user by id";
            LOG.error(message);
            throw new DaoException(message, e);
        } finally {
            close(rs, stmt);
        }

        return user;
    }

    //TODO
    @Override
    public boolean update(User entity) throws DaoException {
        PreparedStatement stmt = null;
        boolean result = false;

        try {
            stmt = con.prepareStatement(SQL_UPDATE_USER);
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getLogin());
            stmt.setInt(3, entity.getRoleId());
            stmt.setInt(4, entity.getStatusId());
            stmt.setInt(5, entity.getId());

            int i = stmt.executeUpdate();
            if (i > 0) {
                result = true;
            }

        } catch (SQLException e) {
            String message = "Cant update user";
            LOG.error(message);
            throw new DaoException(message, e);
        } finally {
            close(stmt);
        }

        return result;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            stmt.setString(1, login);
            rs = stmt.executeQuery();

            if (rs.next()) {
              user = extractUser(rs);
            }

        } catch (SQLException e) {
            LOG.error(MessageManager.getProperty("message.err.cant_obtain_user_by_login"));
            throw new DaoException(MessageManager.getProperty("message.err.cant_obtain_user_by_login"), e);
        } finally {
            close(rs, stmt);
        }
        return user;
    }

    @Override
    public boolean checkLogin(String login) throws DaoException {

        boolean result = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_CHECK_LOGIN);
            stmt.setString(1, login);
            rs = stmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1) == 1;
            }
        } catch (SQLException e) {
            String message = "Cant check Login";
            LOG.error(message);
            throw new DaoException(message, e);
        } finally {
            close(rs, stmt);
        }

        LOG.trace("Check login (" + login + "): " + result);

        return result;
    }

    @Override
    public boolean checkLoginPass(String login, String pass) throws DaoException {
        boolean result = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_CHECK_LOGIN_PASS);
            stmt.setString(1, login);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1) == 1;

            }
        } catch (SQLException e) {
            LOG.error(MessageManager.getProperty("message.err.cant_check_log_pass"));
            throw new DaoException(MessageManager.getProperty("message.err.cant_check_log_pass"), e);
        } finally {
            close(rs, stmt);
        }

        return result;
    }

    @Override
    public boolean insertUser(String login, String pass) throws DaoException {
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = con.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, login);
            statement.setString(2, pass);
            if (statement.executeUpdate() > 0)
                result = true;
        } catch (SQLException e) {

            String message = MessageManager.getProperty("message.err.cant_isr_usr");
            throw new DaoException(message, e);
        } finally {
            close(statement);
        }

        return result;
    }

    @Override
    public boolean updateStatus(User user) throws DaoException {
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            stmt = con.prepareStatement(SQL_UPDATE_STATUS);
            stmt.setInt(1, user.getStatusId());
            stmt.setInt(2, user.getId());
            int i  = stmt.executeUpdate();
            if (i > 0) {
                result = true;
            }
        } catch (SQLException e) {
            String message = "Cant block/unblock user";
            LOG.error(message);
            throw new DaoException(message, e);
        } finally {
            close(stmt);
        }
        return result;
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setRoleId(rs.getInt("role_id"));
        user.setStatusId(rs.getInt("status_id"));
        return user;
    }
}
