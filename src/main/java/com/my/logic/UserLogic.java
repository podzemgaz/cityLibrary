package com.my.logic;

import com.my.dao.EntityTransaction;
import com.my.dao.UserDao;

import com.my.entity.Role;
import com.my.entity.User;

import com.my.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserLogic extends Logic{
    private static final Logger LOG = LogManager.getLogger(UserLogic.class);
    //to delete
    public static boolean moder(int id) {
        boolean result = false;
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = daoFactory.getUserDao();
        try {
            transaction.init(userDao);
            User user = userDao.findById(id);
            int roleId = user.getRoleId();
            if (roleId == Role.CLIENT.ordinal()) {
                roleId = Role.MODER.ordinal();
            } else {
                roleId = Role.CLIENT.ordinal();
            }
            user.setRoleId(roleId);
            result = userDao.update(user);
            transaction.end();
        } catch (DaoException e) {
            LOG.error("transaction error");
        }
        return result;
    }

    public static boolean block(int id) {
        boolean result = false;
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = daoFactory.getUserDao();
        try {
            transaction.init(userDao);
            User user = userDao.findById(id);
            int statusId = user.getStatusId();
            if (statusId == 0) {
                statusId = 1;
            } else {
                statusId = 0;
            }
            user.setStatusId(statusId);
            result = userDao.update(user);
            transaction.end();
        } catch (DaoException e) {
            LOG.error("transaction error");
        }
        return result;
    }

    public static List<User> getUsers() {
        List<User> users = null;
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = daoFactory.getUserDao();
        try {
            transaction.init(userDao);
            users = userDao.findAll();
            transaction.end();
        } catch (DaoException e) {
            LOG.error("transaction error");
        }

        return users;
    }

}
