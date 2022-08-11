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
import java.util.List;

public class UserDaoMysql extends UserDao {
    protected static final Logger LOG = LogManager.getLogger(UserDaoMysql.class);
    public static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT id, login, role_id FROM users WHERE login = ?";
    public static final String SQL_CHECK_LOGIN = "SELECT EXISTS(SELECT * FROM users WHERE login = ? AND password = ?)";

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(User entity) {
        return false;
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
    public boolean checkLogin(String login, String pass) throws DaoException {
        boolean result = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_CHECK_LOGIN);
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

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setRoleId(rs.getInt("role_id"));

        return user;
    }
}
