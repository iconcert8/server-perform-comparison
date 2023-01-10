package com.gyeom.nettymonolithic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Mono;

@SpringBootTest
public class RepositoryTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Autowired
    private UserRepository userRepository;

    private StopWatch stopWatch;
    @BeforeEach
    void beforeEach(){
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    @AfterEach
    void afterEach(){
        stopWatch.stop();
        log.info("elapsed time: " + stopWatch.getTotalTimeMillis());
    }

    @Test
    void saveTest(){
        // given
        User user = new User();
        user.setUsername("kim");
        user.setPassword("1234");

        // when
        Mono<User> savedUser = userRepository.save(user);

        // then
        savedUser.doOnError((u)->Assert.isTrue(false, "fail save user"));
    }

    @Test
    void severalRequestTest(){
        // given
        User user = new User();
        user.setUsername("kim");
        user.setPassword("1234");

        // when
        for(int i = 0; i < 10; i++){
            Mono<User> savedUser = userRepository.save(user);
        }

        // then
        // show elapsed time log
    }
}
