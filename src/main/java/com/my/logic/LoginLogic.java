package com.my.logic;

import com.my.dao.EntityTransaction;
import com.my.dao.UserDao;

import com.my.entity.User;
import com.my.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginLogic extends Logic {
    protected static final Logger LOG = LogManager.getLogger(LoginLogic.class);
    public static User checkLoginPass(String enterLogin, String enterPass) {

        User user = null;
        boolean result;

        UserDao userDao = daoFactory.getUserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            result = userDao.checkLoginPass(enterLogin, enterPass);
            LOG.trace("Check login and password: " + result);
            if (result) {
                user = userDao.findUserByLogin(enterLogin);
            }
            transaction.end();
        } catch (DaoException e) {
            LOG.error("Transaction error");
        }

        LOG.trace("Obtained user: " + user);
        return user;
    }
}
