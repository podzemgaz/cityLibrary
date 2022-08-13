package com.my.logic;

import com.my.dao.EntityTransaction;
import com.my.dao.UserDao;
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
}
