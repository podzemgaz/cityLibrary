package com.my.logic;

import com.my.dao.EntityTransaction;
import com.my.dao.UserDao;

import com.my.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginLogic extends Logic {
    protected static final Logger LOG = LogManager.getLogger(LoginLogic.class);
    public static boolean checkLogin(String enterLogin, String enterPass) {
        boolean result = false;

        UserDao userDao = daoFactory.getUserDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            result = userDao.checkLogin(enterLogin, enterPass);
            transaction.end();
        } catch (DaoException e) {
            LOG.error("Transaction error");
        }
        LOG.trace("Check login and password: " + result);
        return result;
    }
}
