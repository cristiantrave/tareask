/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.EmailFolderDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.EmailFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailFolderServiceImpl implements com.cristian.tareask.service.EmailFolderService {

    @Autowired
    private EmailFolderDao emailFolderDao;

    @Transactional
    public void add(EmailFolder emailFolder) {      
        emailFolderDao.add(emailFolder);
    }

    @Transactional
    public void edit(EmailFolder emailFolder) {
        emailFolderDao.edit(emailFolder);
    }

    @Transactional
    public void delete(int EmailFolderId) {
        emailFolderDao.delete(EmailFolderId);
    }

    @Transactional
    public EmailFolder getEmailFolder(int EmailFolderId) {
        return emailFolderDao.getEmailFolder(EmailFolderId);
    }

    @Transactional
    public List getAllEmailFolders() {
        return emailFolderDao.getAllEmailFolders();
    }

    @Transactional
    public EmailFolder getEmailFolderByName(String nameFolder) {
        return emailFolderDao.getIdFolderByName(nameFolder);
    }

}
