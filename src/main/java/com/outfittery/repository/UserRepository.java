package com.outfittery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.outfittery.entity.User;
import com.outfittery.entity.UserGroup;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

    public List<User> findByGroupId(UserGroup groupId);
}
