
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.EmailDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cristian.tareask.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
    
    @Autowired
    private EmailDao emailDao;

    @Transactional
    public void add(Email email) {
         emailDao.add(email);
    }

    @Transactional
    public void edit(Email email) {
         emailDao.edit(email);
    }

    @Transactional
    public Email getEmail(int EmailId) {
       return emailDao.getEmail(EmailId);
    }
    
    @Transactional
    public Email getEmailByUserId(Integer UserId) {
       return emailDao.getEmailByUserId(UserId);
    }
    
}
