package com.outfittery.service;

import com.outfittery.entity.User;
import java.util.List;

public interface UserServiceInterface {

    public User findUserById(int id);

    public List<User> getAll();

    int add(User u);

    boolean update(User u);

    int delete(int id);
}
