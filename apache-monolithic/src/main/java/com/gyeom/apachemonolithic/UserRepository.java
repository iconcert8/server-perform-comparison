package com.gyeom.apachemonolithic;

// use JPA
//public interface UserRepository extends JpaRepository<User, Long> {
//
//}

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class UserRepository {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final DataSource dataSource;
    public UserRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public User save(User user) {
        String query = String.format("INSERT INTO users(username, password) VALUES('%s', '%s')", user.getUsername(), user.getPassword());
        try(Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()) {
            int affectRows = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            Assert.isTrue(affectRows >= 1, "Fail insert user");
            try(ResultSet rs = stmt.getGeneratedKeys()){
                User savedUser = new User();
                Assert.isTrue(rs.next(), "Empty user id(primary key)");
                Assert.isTrue(rs.getLong(1) >= 1, "Wrong user id(primary key)");
                savedUser.setId(rs.getLong(1));
                savedUser.setUsername(user.getUsername());
                savedUser.setPassword(user.getPassword());
                return savedUser;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(Long id){
        String query = String.format("SELECT * FROM users WHERE id=%s", id);
        try(Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            if(!rs.next()) return Optional.empty();
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return Optional.of(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}