package com.example.redis.demo.services;

import com.example.redis.demo.dto.CreateUserDto;
import com.example.redis.demo.dto.UpdateUserDto;
import com.example.redis.demo.model.User;
import com.example.redis.demo.repos.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    @CacheEvict(value = {"users", "user_id"}, allEntries = true) // Clear all users cache
    public User createUser(CreateUserDto createUserDto) {
        var user = userRepo.save(createUserDto.toEntity(createUserDto));
        return user;
    }


    @Cacheable(value = "users" , key = "#root.method.name", unless = "#result.size() == 0") // Cache all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Cacheable(value = "user_id" , key = "#root.method.name", unless = "#result == null") // Cache all users
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    @CacheEvict(value = "user_id", allEntries = true) // Clear the user by id
    public String  deleteUserById(Long id) {
        userRepo.deleteById(id);
        return "User deleted successfully";
    }




    @CachePut(cacheNames = "user_id", key = "'getUserById' + #dto.id", unless = "#result == null")
    public User updateUser(UpdateUserDto dto) {

        Optional<User> user = userRepo.findById(dto.getId());
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setName(dto.getName());
            user1.setEmail(dto.getEmail());
            return userRepo.save(user1);
        } else {
            return null;
        }
    }
}
