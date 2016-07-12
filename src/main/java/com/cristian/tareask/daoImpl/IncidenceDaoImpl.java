/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.daoImpl;

import java.util.List;
import com.cristian.tareask.model.Incidence;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class IncidenceDaoImpl implements com.cristian.tareask.dao.IncidenceDao {

    Session s;

    @Override
    public void add(Incidence incidence) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(incidence);
        s.getTransaction().commit();
    }

    @Override
    public void edit(Incidence incidence) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.update(incidence);
    }

    @Override
    public void delete(int IncidenceId) {
        s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.delete(getIncidence(IncidenceId));
    }

    @Override
    public Incidence getIncidence(int IncidenceId) {

        s = HibernateUtil.getSessionFactory().getCurrentSession();
        return (Incidence) s.get(Incidence.class, IncidenceId);
    }

    @Override
    public List getAllIncidences() {

        s = HibernateUtil.getSessionFactory().openSession();
        return s.createQuery("from Incidence").list();
    }

    @Override
    public List<Incidence> getIncidencesByTaskId(int TaskId) {

        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Criteria c = s.createCriteria(Incidence.class)
                .add(Restrictions.like("task.id", TaskId))
                .addOrder(Order.desc("date"));
        //s.getTransaction().commit();
        return c.list();
    }

}
