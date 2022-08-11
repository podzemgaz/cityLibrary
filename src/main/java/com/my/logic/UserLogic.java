package com.my.logic;

import com.my.dao.EntityTransaction;
import com.my.dao.UserDao;

import com.my.entity.User;

import com.my.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLogic extends Logic{
    private static final Logger LOG = LogManager.getLogger(UserLogic.class);
    public static User getUser(String login) {
        User user = null;
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = daoFactory.getUserDao();
        try {
            transaction.init(userDao);
            user = userDao.findUserByLogin(login);
            transaction.end();
        } catch (DaoException e) {
            LOG.error("transaction error");
        }
        return user;
    }
}
