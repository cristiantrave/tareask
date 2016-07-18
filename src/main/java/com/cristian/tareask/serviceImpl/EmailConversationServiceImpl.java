/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.EmailConversationDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.EmailConversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailConversationServiceImpl implements com.cristian.tareask.service.EmailConversationService {

    @Autowired
    private EmailConversationDao emailConversationDao;

    @Transactional
    public void add(EmailConversation emailConversation) {      
        emailConversationDao.add(emailConversation);
    }

    @Transactional
    public void edit(EmailConversation emailConversation) {
        emailConversationDao.edit(emailConversation);
    }

    @Transactional
    public void delete(int EmailConversationId) {
        emailConversationDao.delete(EmailConversationId);
    }

    @Transactional
    public EmailConversation getEmailConversation(int EmailConversationId) {
        return emailConversationDao.getEmailConversation(EmailConversationId);
    }

    @Transactional
    public List getAllEmailConversations(int idUser) {
        return emailConversationDao.getAllEmailConversations(idUser);
    }
    
    @Transactional
    public List getConversationByFolder(Integer idUser,String folder){
        return emailConversationDao.getConversationByFolder(idUser,folder);
    }

    @Transactional
    public List getEmailConversationByFolderAndUpdate(Integer folder) {
        return emailConversationDao.getEmailConversationByFolderAndUpdate(folder);
    }

    @Transactional
    public List getLastEmailConversation(){
        return emailConversationDao.getLastEmailConversation();
    }

}
