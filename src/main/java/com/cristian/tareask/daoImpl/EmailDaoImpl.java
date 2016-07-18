
package com.cristian.tareask.daoImpl;

import com.cristian.tareask.model.Email;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDaoImpl implements com.cristian.tareask.dao.EmailDao{
    
    Session s;

    @Override
    public void add(Email email) {
        s = HibernateUtil.getSessionFactory().openSession();
        s.save(email);
    }

    @Override
    public void edit(Email email) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(email);
    }

    @Override
    public Email getEmailByUserId(Integer UserId) {
        s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(Email.class);
        c.add(Restrictions.eq("user.id", UserId));
        c.setMaxResults(1);
        Email email = (Email)c.uniqueResult();
        
        return email;
    }

   @Override
    public Email getEmail(int EmailId) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
       return (Email)s.get(Email.class,EmailId);
    }

   
    
}
