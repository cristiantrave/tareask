/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.TaskReportDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.TaskReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskReportServiceImpl implements com.cristian.tareask.service.TaskReportService {

    @Autowired
    private TaskReportDao taskReportDao;

    @Transactional
    public void add(TaskReport taskReport) {      
        taskReportDao.add(taskReport);
    }

    @Transactional
    public void edit(TaskReport taskReport) {
        taskReportDao.edit(taskReport);
    }

    @Transactional
    public void delete(int TaskReportId) {
        taskReportDao.delete(TaskReportId);
    }

    @Transactional
    public TaskReport getTaskReport(int TaskReportId) {
        return taskReportDao.getTaskReport(TaskReportId);
    }

    @Transactional
    public List getAllTaskReports() {
        return taskReportDao.getAllTaskReports();
    }

    @Transactional
    public TaskReport getTaskReportByName(String TaskReportName) {
        return taskReportDao.getTaskReportByName(TaskReportName);
    }
    
    @Transactional
    public List<TaskReport> getReportsByTask(int TaskId){
        return taskReportDao.getReportsByTask(TaskId);
    }

}
