package com.outfittery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.outfittery.entity.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // return "blocked".equalsIgnoreCase(getStatusId().getName());
    public User findByUsername(String username);

    public List<User> findByGroupId(int groupId);
}
