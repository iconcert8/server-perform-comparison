package com.gyeom.apachemonolithic;

import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
public class MongodbConfig {
    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    final String ip = "127.0.0.1";
    final int port = 27017;
    final String databaseName = "test1";
    final String username = "root";
    final String password = "root";

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        return new MongoTemplate(MongoClients.create(String.format("mongodb://%s:%s@%s:%d", username, password, ip, port)), databaseName);
    }
//
//    @Bean
//    public MongodConfig mongodConfig() throws UnknownHostException {
//        return MongodConfig
//                .builder()
//                .version(Version.Main.V4_4)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();
//    }
}
