/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.TaskDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements com.cristian.tareask.service.TaskService {

    @Autowired
    private TaskDao taskDao;

    @Transactional
    public void add(Task task) {
        taskDao.add(task);
    }

    @Transactional
    public void edit(Task task) {
        taskDao.edit(task);
    }

    @Transactional
    public void delete(int TaskId) {
        taskDao.delete(TaskId);
    }

    @Transactional
    public Task getTask(int TaskId) {
        return taskDao.getTask(TaskId);
    }

    @Transactional
    public List getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Transactional
    public Task getTaskByName(String TaskName) {
        return taskDao.getTaskByName(TaskName);
    }

    @Transactional
    public List getTasksByUserId(int UserId) {
        return taskDao.getTasksByUserId(UserId);
    }
    
    @Transactional
    public List getTasksGroupByUserId(int UserId){
        return taskDao.getTasksGroupByUserId(UserId);
    }

}
