/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.Milestone;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class MilestoneDaoImpl implements com.cristian.tareask.dao.MilestoneDao{
    
    Session s;
   
    @Override
    public void add(Milestone milestone) {
        
        s = HibernateUtil.getSessionFactory().openSession();
        s.save(milestone);
       
    }

    @Override
    public void edit(Milestone milestone) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(milestone);
    }

    @Override
    public void delete(int MilestoneId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getMilestone(MilestoneId));
    }

    @Override
    public Milestone getMilestone(int MilestoneId) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
       return (Milestone)s.get(Milestone.class,MilestoneId);
    }

    @Override
    public List getAllMilestones() {
        
        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from Milestone").list();
    }

    @Override
    public Milestone getMilestoneByName(String MilestoneName) {
        s = HibernateUtil.getSessionFactory().openSession();
        return (Milestone)s.createCriteria(Milestone.class)
                .add( Restrictions.like("name", MilestoneName))
                .uniqueResult();
    }
    
    @Override
    public List<Milestone> getMilestonesByTask(int TaskId){
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(Milestone.class)
                .add( Restrictions.like("task.id", TaskId))
                .addOrder(Order.asc("order"));
        //s.getTransaction().commit();
        return c.list();
    }
    
}
