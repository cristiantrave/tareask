/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.SessionUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository
public class SessionUserDaoImpl implements com.cristian.tareask.dao.SessionUserDao{
    
    Session s;
   
    @Override
    public void add(SessionUser sessionUser) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(sessionUser);
        s.getTransaction().commit();
    }

    @Override
    public void edit(SessionUser sessionUser) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(sessionUser);
    }

    @Override
    public void delete(int SessionUserId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getSessionUser(SessionUserId));
    }

    @Override
    public SessionUser getSessionUser(int SessionUserId) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
       return (SessionUser)s.get(SessionUser.class,SessionUserId);
    }

    @Override
    public List getAllSessionUser() {
        
        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from SessionUser").list();
    }
    
    @Override
    public SessionUser getSessionUserByUser(int UserId) {
        
        s = HibernateUtil.getSessionFactory().openSession();
        SessionUser su = new SessionUser();
        s.beginTransaction();
        Criteria c = s.createCriteria(SessionUser.class)
                .addOrder(Order.desc("id"))
                .setMaxResults(1);
        return (SessionUser)c.uniqueResult();
    }
    
}
