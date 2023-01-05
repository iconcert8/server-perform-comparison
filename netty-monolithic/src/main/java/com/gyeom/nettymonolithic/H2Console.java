package com.gyeom.nettymonolithic;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Configuration
public class H2Console {
    private int port = 8012;

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private Server h2Server;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException {
        log.info("H2 Console at port {}", port);
        this.h2Server = Server.createWebServer("-webPort", String.valueOf(port), "-tcpAllowOthers");
        this.h2Server.start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("Stopping H2 console at port {}", port);
        this.h2Server.stop();
    }
}
