package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.Task;

public interface TaskDao {
    public void add (Task task);
    public void edit (Task task);
    public void delete (int TaskId);
    public Task getTask (int TaskId);
    public List getAllTasks();
    public Task getTaskByName(String TaskName);
    public List getTasksByUserId(int UserId);
    public List getTasksGroupByUserId(int UserId);
}
