package com.gyeom.apachemonolithic;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

@DataJpaTest
public class JpaTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveTest(){
        // given
        User user = new User();
        user.setUsername("kim");
        user.setPassword("1234");

        // when
        User savedUser = userRepository.save(user);

        // then
        Assert.isTrue(user.getUsername().equals(savedUser.getUsername()), "name is saved");
        Assert.isTrue(user.getUsername().equals(savedUser.getUsername()), "password is saved");

        log.info(String.valueOf(savedUser.getId()));
        log.info(savedUser.getUsername());
        log.info(savedUser.getPassword());
    }
}
