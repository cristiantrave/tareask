/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.Task;

public interface TaskService {
	public void add(Task task);
	public void edit(Task task);
	public void delete(int TaskId);
	public Task getTask(int TaskId);
	public List getAllTasks();
        public Task getTaskByName(String TaskName);
        public List getTasksByUserId(int UserId);
        public List getTasksGroupByUserId(int UserId);
}
