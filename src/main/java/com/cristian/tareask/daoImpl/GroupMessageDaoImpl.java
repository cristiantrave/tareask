/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.GroupMessage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class GroupMessageDaoImpl implements com.cristian.tareask.dao.GroupMessageDao{
    
    Session s;
   
    @Override
    public void add(GroupMessage groupMessage) {
        
        s = HibernateUtil.getSessionFactory().openSession();
        s.save(groupMessage);
       
    }

    @Override
    public void edit(GroupMessage groupMessage) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(groupMessage);
    }

    @Override
    public void delete(int GroupMessageId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getGroupMessage(GroupMessageId));
    }

    @Override
    public GroupMessage getGroupMessage(int GroupMessageId) {
        
        s = HibernateUtil.getSessionFactory().getCurrentSession();
       return (GroupMessage)s.get(GroupMessage.class,GroupMessageId);
    }

    @Override
    public List getAllGroupMessages() {
        
        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from GroupMessage").list();
    }

    @Override
    public GroupMessage getGroupMessageByName(String GroupMessageName) {
        s = HibernateUtil.getSessionFactory().openSession();
        return (GroupMessage)s.createCriteria(GroupMessage.class)
                .add( Restrictions.like("name", GroupMessageName))
                .uniqueResult();
    }
    
    @Override
    public List<GroupMessage> getGroupMessagesByTask(int TaskId){
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(GroupMessage.class,"groupmessage")
                .createAlias("groupmessage.group", "group")
                .createAlias("group.tasks", "task")
                .add( Restrictions.like("task.id", TaskId))
                .addOrder(Order.desc("date"));
        return c.list();
    }
    
}
