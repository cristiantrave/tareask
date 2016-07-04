/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.UserDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements com.cristian.tareask.service.UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void add(User user) {      
        userDao.add(user);
    }

    @Transactional
    public void edit(User user) {
        userDao.edit(user);
    }

    @Transactional
    public void delete(int UserId) {
        userDao.delete(UserId);
    }

    @Transactional
    public User getUser(int UserId) {
        return userDao.getUser(UserId);
    }

    @Transactional
    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public User getUserByName(String UserName) {
        return userDao.getUserByName(UserName);
    }
    
    @Transactional
    public User getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

}
