/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.IncidenceDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.Incidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenceServiceImpl implements com.cristian.tareask.service.IncidenceService {

    @Autowired
    private IncidenceDao incidenceDao;

    @Transactional
    public void add(Incidence incidence) {      
        incidenceDao.add(incidence);
    }

    @Transactional
    public void edit(Incidence incidence) {
        incidenceDao.edit(incidence);
    }

    @Transactional
    public void delete(int IncidenceId) {
        incidenceDao.delete(IncidenceId);
    }

    @Transactional
    public Incidence getIncidence(int IncidenceId) {
        return incidenceDao.getIncidence(IncidenceId);
    }

    @Transactional
    public List getAllIncidences() {
        return incidenceDao.getAllIncidences();
    }
    
    @Transactional
    public List<Incidence> getIncidencesByTaskId(int TaskId){
       return incidenceDao.getIncidencesByTaskId(TaskId);
    }

}