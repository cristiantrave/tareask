/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.EmailMessageDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageServiceImpl implements com.cristian.tareask.service.EmailMessageService {

    @Autowired
    private EmailMessageDao emailMessageDao;

    @Transactional
    public void add(EmailMessage emailMessage) {      
        emailMessageDao.add(emailMessage);
    }

    @Transactional
    public void edit(EmailMessage emailMessage) {
        emailMessageDao.edit(emailMessage);
    }

    @Transactional
    public void delete(int EmailMessageId) {
        emailMessageDao.delete(EmailMessageId);
    }

    @Transactional
    public EmailMessage getEmailMessage(int EmailMessageId) {
        return emailMessageDao.getEmailMessage(EmailMessageId);
    }

    @Transactional
    public List getAllEmailMessages() {
        return emailMessageDao.getAllEmailMessages();
    }

    @Transactional
    public EmailMessage getLastEmailMessageByConversation(Integer conversation) {
        return emailMessageDao.getLastEmailMessageByConversation(conversation);
    }
    
    @Transactional
    public List<EmailMessage> getAllEmailMessagesByConversation(Integer conversation) {
        return emailMessageDao.getAllEmailMessagesByConversation(conversation);
    }
    
    @Transactional
    public EmailMessage getFirstEmailofConversation (Integer conversation){
        return emailMessageDao.getFirstEmailofConversation(conversation);
    }
    
    @Transactional
    public List<EmailMessage> getLastMessagesByReceptor(int UserId,int max){
        return emailMessageDao.getLastMessagesByReceptor(UserId, max);
    }

}
