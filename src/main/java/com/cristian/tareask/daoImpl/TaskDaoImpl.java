/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDaoImpl implements com.cristian.tareask.dao.TaskDao {

    Session s;

    @Override
    public void add(Task task) {

        s = HibernateUtil.getSessionFactory().openSession();
        s.save(task);

    }

    @Override
    public void edit(Task task) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(task);
    }

    @Override
    public void delete(int TaskId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getTask(TaskId));
    }

    @Override
    public Task getTask(int TaskId) {

        Task t = new Task();
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        t = (Task) s.get(Task.class, TaskId);
        s.getTransaction().commit();
        return t;
    }

    @Override
    public List getAllTasks() {

        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from Task").list();
    }

    @Override
    public Task getTaskByName(String TaskName) {
        s = HibernateUtil.getSessionFactory().openSession();
        return (Task) s.createCriteria(Task.class)
                .add(Restrictions.like("name", TaskName))
                .uniqueResult();
    }

    @Override
    public List getTasksByUserId(int UserId) {
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(Task.class,"task")
                .createAlias("task.user", "user")
                .add(Restrictions.like("user.id", UserId));
        List l = c.list();
        //s.getTransaction().commit();
        return l;
    }
    
    @Override
    public List getTasksGroupByUserId(int UserId) {
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(Task.class,"task")
                .createAlias("task.group", "group")
                .createAlias("group.groupUserses", "groupuser")
                .createAlias("groupuser.user", "user")
                .add(Restrictions.like("user.id", UserId));
        List l = c.list();
        return l;
    }

}
