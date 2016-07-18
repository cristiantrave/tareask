/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.EmailConversation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EmailConversationDaoImpl implements com.cristian.tareask.dao.EmailConversationDao {

    Session s;

    @Override
    public void add(EmailConversation emailConversation) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.save(emailConversation);
            s.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
            s.getTransaction().rollback();
        }

    }

    @Override
    public void edit(EmailConversation emailConversation) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.update(emailConversation);
            s.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    @Override
    public void delete(int EmailConversationId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getEmailConversation(EmailConversationId));
    }

    @Override
    public EmailConversation getEmailConversation(int EmailConversationId) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        EmailConversation ec = new EmailConversation();
        try {
            s.beginTransaction();
            ec = (EmailConversation) s.get(EmailConversation.class, EmailConversationId);
            s.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
            s.getTransaction().rollback();
        }

        return ec;
    }

    @Override
    public List getAllEmailConversations(int idUser) {
        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(EmailConversation.class).createAlias("emailMessage", "message")
                .createAlias("message.messageReceptors", "receptor")
                .createAlias("receptor.user", "user")
                .createAlias("message.user", "userid")
                //parte experimental
                .add(Restrictions.or(
                                Restrictions.eq("userid.id", idUser),
                                Restrictions.eq("user.id", idUser)))
                //.add(Restrictions.eq("userid.id", 2))
                //.add(Restrictions.eq("user.id",2))
                // fin parte experimental
                .addOrder(Order.desc("message.date"))
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return c.list();
    }

    @Override
    public List getConversationByFolder(Integer idUser, String folder) {

        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(EmailConversation.class);
        c.add(Restrictions.eq("emailConversation.emailMessage.receptor", 1));
        c.add(Restrictions.eq("conversation.folder.name", "inbox"));
        c.addOrder(Order.desc("last"));
        List mails = (List) c.list();
        return mails;
    }

    @Override
    public List getEmailConversationByFolderAndUpdate(Integer folder) {
        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(EmailConversation.class);
        c.add(Restrictions.eq("emailFolder.id", 1));
        c.addOrder(Order.desc("date"));
        List mails = (List) c.list();
        return mails;
    }

    @Override
    public List getLastEmailConversation() {
        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(EmailConversation.class, "conversation");
        c.createAlias("conversation.emailMessage", "lastEmail");
        c.addOrder(Order.desc("lastEmail.date"));
        List mails = (List) c.list();
        return mails;
    }

}
