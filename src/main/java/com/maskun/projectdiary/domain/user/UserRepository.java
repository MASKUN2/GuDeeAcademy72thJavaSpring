package com.maskun.projectdiary.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query("select u from User u where u.userId = :userId and u.userPw = password(:userPw)")
    Optional<User> findUserByUserIdAndUserPw(@Param("userId") String userId,@Param("userPw") String userPw);
}
