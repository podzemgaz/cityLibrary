package com.my.dao;

import com.my.entity.User;
import com.my.exception.DaoException;


public abstract class UserDao extends AbstractDao<User>{

    public abstract User findUserByLogin(String login) throws DaoException;

    public abstract boolean checkLogin(String login, String pass) throws DaoException;
}
