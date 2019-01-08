package com.outfittery.service.impl;

import com.outfittery.repository.UserRepository;
import com.outfittery.entity.User;
import com.outfittery.service.UserServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepository userDao;

    @Override
    public User findUserById(int id) {
        Optional<User> op = userDao.findById(id);
        return op.get();

    }

    @Override
    public List<User> getAll() {
        List<User> list = userDao.findAll();
        return list;
    }

    @Override
    public int add(User u) {
        userDao.save(u);
        return u.getId();

    }

    @Override
    public boolean update(User u) {
        userDao.save(u);
        return true;

    }

    @Override
    public int delete(int id) {
        userDao.deleteById(id);
        return id;

    }

    @Override
    public List<User> getAllByGroupId(int groupId) {
        return userDao.findByGroupId(groupId);
    }
 

}
