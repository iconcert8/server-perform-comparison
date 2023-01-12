package com.gyeom.nettymonolithic;

// use h2 r2dbc
//import org.springframework.data.r2dbc.repository.R2dbcRepository;
//
//public interface UserRepository extends R2dbcRepository<User, Long>{
//
//}


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}