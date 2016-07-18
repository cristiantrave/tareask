/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.EmailMessage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EmailMessageDaoImpl implements com.cristian.tareask.dao.EmailMessageDao {

    Session s;

    @Override
    public void add(EmailMessage emailMessage) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.save(emailMessage);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    @Override
    public void edit(EmailMessage emailMessage) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.update(emailMessage);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    @Override
    public void delete(int EmailMessageId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getEmailMessage(EmailMessageId));
    }

    @Override
    public EmailMessage getEmailMessage(int EmailMessageId) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        return (EmailMessage) s.get(EmailMessage.class, EmailMessageId);
    }

    @Override
    public List getAllEmailMessages() {

        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from EmailMessage").list();
    }

    @Override
    public EmailMessage getLastEmailMessageByConversation(Integer conversation) {
        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(EmailMessage.class);
        //c.add(Restrictions.eq("emailConversation.id", conversation));
        c.addOrder(Order.desc("date"));
        c.setMaxResults(1);
        EmailMessage email = (EmailMessage) c.uniqueResult();
        return email;
    }
    /*
     @Override
     public EmailMessage getAllConversationsLastEmail() {
     s = HibernateUtil.getSessionFactory().openSession();
     Criteria c = s.createCriteria(EmailMessage.class,"message");
     c.createAlias("message.conversation", "conversation"); 
     c.createAlias("conversation.receptor", "conversation");
     c.add(Restrictions.eq("emailConversation.id", conversation));
     c.addOrder(Order.desc("date"));
     c.setMaxResults(1);
     EmailMessage email = (EmailMessage) c.uniqueResult();
     return email;
     }
     */

    @Override
    public List<EmailMessage> getAllEmailMessagesByConversation(Integer conversation) {
        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(EmailMessage.class);
        c.add(Restrictions.eq("emailConversation.id", conversation));
        c.addOrder(Order.desc("date"));
        List mailsConversation = c.list();
        return mailsConversation;
    }
    
    @Override
    public EmailMessage getFirstEmailofConversation (Integer conversation){
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(EmailMessage.class)
        .add(Restrictions.eq("emailConversation.id", conversation))
        .addOrder(Order.asc("id"))
        .setMaxResults(1);
        return (EmailMessage)c.uniqueResult();
    }
    
    @Override
    public List<EmailMessage> getLastMessagesByReceptor(int UserId,int max){
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction(); 
        Criteria c = s.createCriteria(EmailMessage.class,"message")
                .createAlias("message.messageReceptors", "receptors")
                .createAlias("receptors.user", "user")
                .add(Restrictions.eq("user.id", UserId))
                .addOrder(Order.desc("id"))
                .setMaxResults(max);
        List lastMails = c.list();
        return lastMails;
    }

}
