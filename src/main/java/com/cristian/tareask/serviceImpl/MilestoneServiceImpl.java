/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.MilestoneDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.Milestone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilestoneServiceImpl implements com.cristian.tareask.service.MilestoneService {

    @Autowired
    private MilestoneDao milestoneDao;

    @Transactional
    public void add(Milestone milestone) {      
        milestoneDao.add(milestone);
    }

    @Transactional
    public void edit(Milestone milestone) {
        milestoneDao.edit(milestone);
    }

    @Transactional
    public void delete(int MilestoneId) {
        milestoneDao.delete(MilestoneId);
    }

    @Transactional
    public Milestone getMilestone(int MilestoneId) {
        return milestoneDao.getMilestone(MilestoneId);
    }

    @Transactional
    public List getAllMilestones() {
        return milestoneDao.getAllMilestones();
    }

    @Transactional
    public Milestone getMilestoneByName(String MilestoneName) {
        return milestoneDao.getMilestoneByName(MilestoneName);
    }
    
    @Transactional
    public List<Milestone> getMilestonesByTask(int TaskId){
        return milestoneDao.getMilestonesByTask(TaskId);
    }

}
