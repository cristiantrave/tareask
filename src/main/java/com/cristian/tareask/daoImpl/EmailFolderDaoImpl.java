/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.EmailFolder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EmailFolderDaoImpl implements com.cristian.tareask.dao.EmailFolderDao{
    
    Session s;
   
    @Override
    public void add(EmailFolder emailFolder) {
        
        s = HibernateUtil.getSessionFactory().openSession();
        s.save(emailFolder);
       
    }

    @Override
    public void edit(EmailFolder emailFolder) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(emailFolder);
    }

    @Override
    public void delete(int EmailFolderId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getEmailFolder(EmailFolderId));
    }

    @Override
    public EmailFolder getEmailFolder(int EmailFolderId) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
       return (EmailFolder)s.get(EmailFolder.class,EmailFolderId);
    }

    @Override
    public List getAllEmailFolders() {
        
        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from EmailFolder").list();
    }

    @Override
    public EmailFolder getIdFolderByName(String nameFolder) {
         s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(EmailFolder.class);
        c.add(Restrictions.eq("folder", nameFolder));
        c.setMaxResults(1);
        EmailFolder emailFolder = (EmailFolder)c.uniqueResult();
        
        return emailFolder;
    }
    
}
