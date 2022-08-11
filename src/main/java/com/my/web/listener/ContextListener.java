package com.my.web.listener;

import com.my.dao.AbstractDaoFactory;
import com.my.util.ConfigManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOG = LogManager.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Servlet context initialization starts");
        AbstractDaoFactory.setDaoFactoryFCN(ConfigManager.getProperty("var.daoFactoryFCN"));
    }
}
