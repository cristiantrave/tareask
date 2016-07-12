package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.TaskReport;

public interface TaskReportDao {
    public void add (TaskReport taskReport);
    public void edit (TaskReport taskReport);
    public void delete (int TaskReportId);
    public TaskReport getTaskReport (int TaskReportId);
    public List getAllTaskReports();
    public TaskReport getTaskReportByName(String TaskReportName);
    public List<TaskReport> getReportsByTask(int TaskId);
}
