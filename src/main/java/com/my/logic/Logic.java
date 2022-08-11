package com.my.logic;

import com.my.dao.AbstractDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Logic {
    protected static final Logger LOG = LogManager.getLogger(Logic.class);
    protected static AbstractDaoFactory daoFactory;
    static {
        try {
            daoFactory = AbstractDaoFactory.getInstance();
        } catch (Exception e) {
            LOG.error("cannot create daoFactory");
            throw new RuntimeException(e);
        }
    }
}
