/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.TaskReport;

public interface TaskReportService {
	public void add(TaskReport taskReport);
	public void edit(TaskReport taskReport);
	public void delete(int TaskReportId);
	public TaskReport getTaskReport(int TaskReportId);
	public List getAllTaskReports();
        public TaskReport getTaskReportByName(String TaskReportName);
        public List<TaskReport> getReportsByTask(int TaskId);
}
