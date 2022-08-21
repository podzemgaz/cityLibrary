package com.my.logic;

import com.my.dao.EntityTransaction;
import com.my.dao.UserDao;
import com.my.entity.User;
import com.my.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterLogic extends Logic{
    private final static Logger LOG = LogManager.getLogger(RegisterLogic.class);

    public static boolean isLoginExist(String login) {

        boolean result = false;
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = daoFactory.getUserDao();


        try {
            transaction.init(userDao);
            result = userDao.checkLogin(login);
            transaction.end();
        } catch (DaoException e) {
            LOG.error("transaction error");
        }

        return result;
    }


    /*inserts user to database by login and password
     * and return its user from database
     * */
    public static User insertUser(String login, String pass) {
        User user = null;
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = daoFactory.getUserDao();
        try {
            transaction.init(userDao);
            result = userDao.insertUser(login, pass);
            if (result) {
                user = userDao.findUserByLogin(login);
            }
            transaction.end();
        } catch (DaoException e) {
            LOG.error("transaction error");
        }

        return user;
    }
}
