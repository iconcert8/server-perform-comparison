package com.gyeom.nettymonolithic;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@Configuration
public class MongodbConfig extends AbstractReactiveMongoConfiguration {
    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    final String ip = "127.0.0.1";
    final int port = 27017;
    final String databaseName = "testNetty";
    final String username = "root";
    final String password = "root";

//    @Bean
//    public MongoClient mongoClient() throws IOException {
//        return MongoClients.create(String.format("mongodb://%s:%d", ip, port));
//    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(String.format("mongodb://%s:%s@%s:%d", username, password, ip, port));
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

//    @Bean
//    public MongodConfig mongodConfig() throws IOException {
//        return MongodConfig
//                .builder()
//                .version(Version.Main.V4_4)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();
//    }

//    @EventListener(ContextClosedEvent.class)
//    public void stop(){
//        log.info("------------------stop");
//    }
}
