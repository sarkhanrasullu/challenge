package com.outfittery.service;

import com.outfittery.entity.User;
import java.util.List;

public interface UserServiceInterface {

    public User findUserById(int id);

    public List<User> getAll();

    public List<User> getAllByGroupId(int groupId);

    int add(User u);

    boolean update(User u);

    int delete(int id);
}
