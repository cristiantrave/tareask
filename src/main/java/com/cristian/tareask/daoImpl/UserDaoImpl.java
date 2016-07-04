/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements com.cristian.tareask.dao.UserDao {

    Session s;

    @Override
    public void add(User user) {

        s = HibernateUtil.getSessionFactory().openSession();
        s.save(user);

    }

    @Override
    public void edit(User user) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(user);
        s.getTransaction().commit();
    }

    @Override
    public void delete(int UserId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getUser(UserId));
    }

    @Override
    public User getUser(int UserId) {

        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        return (User) s.get(User.class, UserId);
    }

    @Override
    public List getAllUsers() {

        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from User").list();
    }

    @Override
    public User getUserByName(String UserName) {
        User u = new User();
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        u = (User) s.createCriteria(User.class)
                .add(Restrictions.like("name", UserName))
                .uniqueResult();
        s.getTransaction().commit();
        return u;
    }

    @Override
    public User getUserByEmail(String email) {
        User u = new User();
        s = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            s.beginTransaction();
            u = (User) s.createCriteria(User.class)
                    .add(Restrictions.like("email", email))
                    .uniqueResult();
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
        return u;
    }

}
