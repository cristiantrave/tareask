/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.SessionUserDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionUserServiceImpl implements com.cristian.tareask.service.SessionUserService {

    @Autowired
    private SessionUserDao sessionUserDao;

    @Transactional
    public void add(SessionUser sessionUser) {      
        sessionUserDao.add(sessionUser);
    }

    @Transactional
    public void edit(SessionUser sessionUser) {
        sessionUserDao.edit(sessionUser);
    }

    @Transactional
    public void delete(int SessionUserId) {
        sessionUserDao.delete(SessionUserId);
    }

    @Transactional
    public SessionUser getSessionUser(int SessionUserId) {
        return sessionUserDao.getSessionUser(SessionUserId);
    }

    @Transactional
    public List getAllSessionUser() {
        return sessionUserDao.getAllSessionUser();
    }
    
    @Transactional
    public SessionUser getSessionUserByUser(int UserId){
        return sessionUserDao.getSessionUserByUser(UserId);
    }

}
