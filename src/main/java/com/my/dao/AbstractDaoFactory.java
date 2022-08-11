package com.my.dao;


public abstract class AbstractDaoFactory {
    private static AbstractDaoFactory instance;
    private static String daoFactoryFCN;

    public static synchronized AbstractDaoFactory getInstance() throws Exception {
        if (instance == null) {
            Class<?> clazz = Class.forName(AbstractDaoFactory.daoFactoryFCN);
            instance = (AbstractDaoFactory) clazz.getDeclaredConstructor().newInstance();
        }
        return instance;
    }

    protected AbstractDaoFactory() {

    }

    public static void setDaoFactoryFCN(String daoFactoryFCN) {
        instance = null;
        AbstractDaoFactory.daoFactoryFCN = daoFactoryFCN;
    }

    public abstract UserDao getUserDao();
}
