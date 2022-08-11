package com.my.dao.mysql;

import com.my.dao.AbstractDaoFactory;
import com.my.dao.UserDao;

public class DaoFactoryMysql extends AbstractDaoFactory {
    private static UserDao userDao;
    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoMysql();
        }
        return userDao;
    }
}
