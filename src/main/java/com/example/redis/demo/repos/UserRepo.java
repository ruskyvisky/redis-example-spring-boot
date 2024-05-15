package com.example.redis.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.redis.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
