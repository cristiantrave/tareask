/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.TaskReport;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TaskReportDaoImpl implements com.cristian.tareask.dao.TaskReportDao{
    
    Session s;
   
    @Override
    public void add(TaskReport taskReport) {
        
       s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.save(taskReport);
            s.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
            s.getTransaction().rollback();
        }
       
    }

    @Override
    public void edit(TaskReport taskReport) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(taskReport);
    }

    @Override
    public void delete(int TaskReportId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getTaskReport(TaskReportId));
    }

    @Override
    public TaskReport getTaskReport(int TaskReportId) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
       return (TaskReport)s.get(TaskReport.class,TaskReportId);
    }

    @Override
    public List getAllTaskReports() {
        
        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from TaskReport").list();
    }

    @Override
    public TaskReport getTaskReportByName(String TaskReportName) {
        s = HibernateUtil.getSessionFactory().openSession();
        return (TaskReport)s.createCriteria(TaskReport.class)
                .add( Restrictions.like("name", TaskReportName))
                .uniqueResult();
    }
    
    @Override
    public List<TaskReport> getReportsByTask(int TaskId){
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(TaskReport.class)
                .add( Restrictions.like("task.id", TaskId))
                .addOrder(Order.desc("date"));
        return c.list();
    }
    
}
