package com.vasilii.notificationhub.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.h2.tools.Server;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class H2ServerConfig implements BeanFactoryPostProcessor {

    private Server server;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory){
        try {
            server = Server.createTcpServer(
                    "-tcp",
                    "-tcpPort", "9092",
                    "-tcpAllowOthers",
                    "-ifNotExists"
            ).start();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to start H2 TCP server", e);
        }
    }
}
