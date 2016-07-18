/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.MessageReceptorDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.MessageReceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageReceptorServiceImpl implements com.cristian.tareask.service.MessageReceptorService {

    @Autowired
    private MessageReceptorDao messageReceptorDao;

    @Transactional
    public void add(MessageReceptor messageReceptor) {      
        messageReceptorDao.add(messageReceptor);
    }

    @Transactional
    public void edit(MessageReceptor messageReceptor) {
        messageReceptorDao.edit(messageReceptor);
    }

    @Transactional
    public void delete(int MessageReceptorId) {
        messageReceptorDao.delete(MessageReceptorId);
    }

    @Transactional
    public MessageReceptor getMessageReceptor(int MessageReceptorId) {
        return messageReceptorDao.getMessageReceptor(MessageReceptorId);
    }

    @Transactional
    public List getAllMessageReceptors() {
        return messageReceptorDao.getAllMessageReceptors();
    }
    @Transactional
    public List getLastMailByUserReceptor(Integer idUser){
        return messageReceptorDao.getLastMailByUserReceptor(idUser);
    }

    @Transactional
    public MessageReceptor getMessageReceptorByName(String MessageReceptorName) {
        return messageReceptorDao.getMessageReceptorByName(MessageReceptorName);
    }
    
    @Transactional
    public List getAllMessageReceptorsByConversation(Integer idConversation){
        return messageReceptorDao.getAllMessageReceptorsByConversation(idConversation);
    }
    
    @Transactional
    public List<Integer> getAllMessageReceptorsByConversationUnique(Integer idConversation){
        return messageReceptorDao.getAllMessageReceptorsByConversationUnique(idConversation);
    }
    
    @Transactional
    public List<Integer> getAllMessageReceptorsByMessageUnique(Integer idMessage){
        return messageReceptorDao.getAllMessageReceptorsByMessageUnique(idMessage);
    }

}
