/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.MessageReceptor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class MessageReceptorDaoImpl implements com.cristian.tareask.dao.MessageReceptorDao {

    Session s;

    @Override
    public void add(MessageReceptor messageReceptor) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.save(messageReceptor);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }

    }

    @Override
    public void edit(MessageReceptor messageReceptor) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(messageReceptor);
    }

    @Override
    public void delete(int MessageReceptorId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getMessageReceptor(MessageReceptorId));
    }

    @Override
    public MessageReceptor getMessageReceptor(int MessageReceptorId) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        return (MessageReceptor) s.get(MessageReceptor.class, MessageReceptorId);
    }

    @Override
    public List getAllMessageReceptors() {

        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from MessageReceptor").list();
    }

    @Override
    public MessageReceptor getMessageReceptorByName(String MessageReceptorName) {
        s = HibernateUtil.getSessionFactory().openSession();
        return (MessageReceptor) s.createCriteria(MessageReceptor.class)
                .add(Restrictions.like("name", MessageReceptorName))
                .uniqueResult();
    }

    @Override
    public List getLastMailByUserReceptor(Integer idUser) {
        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(MessageReceptor.class, "receptor");
        c.createAlias("receptor.message", "message");
        c.createAlias("message.conversation", "conversation");
        List mails = (List) c.list();
        return mails;
    }

    @Override
    public List<MessageReceptor> getAllMessageReceptorsByConversation(Integer idConversation) {
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(MessageReceptor.class)
                .add(Restrictions.eq("emailConversation.id", idConversation));
        return c.list();

    }

    @Override
    public List<Integer> getAllMessageReceptorsByConversationUnique(Integer idConversation) {
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(MessageReceptor.class)
                .add(Restrictions.eq("emailConversation.id", idConversation))
                .setProjection(Projections.distinct(Projections.property("user.id")));
        return c.list();
    }
    
     @Override
    public List<Integer> getAllMessageReceptorsByMessageUnique(Integer idMessage) {
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(MessageReceptor.class)
        .setProjection(Projections.distinct(Projections.property("user.id")))
                .add(Restrictions.eq("emailMessage.id", idMessage));
        return c.list();
    }
}
