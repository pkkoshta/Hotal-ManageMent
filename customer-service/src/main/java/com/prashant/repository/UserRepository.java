package com.prashant.repository;

import com.prashant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

//    @Query("SELECT u FROM User u WHERE u.password = ?1")
//    User findByPassword(String password);

}