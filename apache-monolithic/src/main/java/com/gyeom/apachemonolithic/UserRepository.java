package com.gyeom.apachemonolithic;

// use JPA
//public interface UserRepository extends JpaRepository<User, Long> {
//
//}

// use jdbc
//@Repository
//public class UserRepository {
//
//    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
//
//    private final DataSource dataSource;
//    public UserRepository(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//
//    public User save(User user) {
//        String query = String.format("INSERT INTO users(username, password) VALUES('%s', '%s')", user.getUsername(), user.getPassword());
//        try(Connection conn = dataSource.getConnection();
//            Statement stmt = conn.createStatement()) {
//            int affectRows = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
//            Assert.isTrue(affectRows >= 1, "Fail insert user");
//            try(ResultSet rs = stmt.getGeneratedKeys()){
//                User savedUser = new User();
//                Assert.isTrue(rs.next(), "Empty user id(primary key)");
//                Assert.isTrue(rs.getLong(1) >= 1, "Wrong user id(primary key)");
//                savedUser.setId(rs.getLong(1));
//                savedUser.setUsername(user.getUsername());
//                savedUser.setPassword(user.getPassword());
//                return savedUser;
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Optional<User> findById(Long id){
//        String query = String.format("SELECT * FROM users WHERE id=%s", id);
//        try(Connection conn = dataSource.getConnection();
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query)) {
//            if(!rs.next()) return Optional.empty();
//            User user = new User();
//            user.setId(rs.getLong("id"));
//            user.setUsername(rs.getString("username"));
//            user.setPassword(rs.getString("password"));
//            return Optional.of(user);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    final MongoTemplate mongoTemplate;

    public UserRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public User save(User user){
        return mongoTemplate.save(user);
    }

    public Optional<User> findById(String id){
        User user = mongoTemplate.findById(id, User.class);
        if(user == null)
            return Optional.empty();

        return Optional.of(user);
    }

}