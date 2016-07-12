/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.cristian.tareask.model.GroupMessage;
import com.cristian.tareask.model.Incidence;
import com.cristian.tareask.model.Milestone;
import com.cristian.tareask.model.Task;
import com.cristian.tareask.model.TaskReport;
import com.cristian.tareask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.cristian.tareask.service.GroupMessageService;
import com.cristian.tareask.service.IncidenceService;
import com.cristian.tareask.service.MilestoneService;
import com.cristian.tareask.service.TaskReportService;
import com.cristian.tareask.service.TaskService;
import com.cristian.tareask.service.UserService;

@Controller
public class TaskController {

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    MilestoneService milestoneService;
    @Autowired
    TaskReportService taskReportService;
    @Autowired
    GroupMessageService groupMessageService;
    @Autowired
    IncidenceService incidenceService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String task(Model m, HttpSession session,
            @RequestParam(value = "view", required = false) String group) {

        List<Object> taskModel = new ArrayList<Object>();

        if ((session.getAttribute("namesession")) != null) {
            User u = new User();
            u = userService.getUserByName(session.getAttribute("namesession").toString());

            List<Task> t = new ArrayList<Task>();
            if (group != null) {
                t = taskService.getTasksGroupByUserId(u.getId());
            } else {
                t = taskService.getTasksByUserId(u.getId());
            }

            for (int i = 0; i < t.size(); i++) {

                LinkedHashMap<String, Object> taskArray = new LinkedHashMap<>();
                LinkedHashMap<String, String> task = new LinkedHashMap<>();

                List<Milestone> milestones = milestoneService.getMilestonesByTask(t.get(i).getId());

                int close = 0;

                List<Object> milestonesArray = new ArrayList<Object>();
                for (int a = 0; a < milestones.size(); a++) {
                    if ("close".equals(milestones.get(a).getMilestoneStatus().getStatus())) {
                        //percentage = ((done += 1)/milestones.size())*100;
                        close += 1;
                    }

                    LinkedHashMap<String, String> milestone = new LinkedHashMap<>();

                    // Build Milestone
                    milestone.put("id", milestones.get(a).getId().toString());
                    milestone.put("title", milestones.get(a).getTitle());
                    milestone.put("description", milestones.get(a).getDescription());
                    milestone.put("status", milestones.get(a).getMilestoneStatus().getStatus());
                    milestone.put("order", milestones.get(a).getOrder().toString());
                    milestonesArray.add(milestone);

                }                                

                List<TaskReport> reports = taskReportService.getReportsByTask(t.get(i).getId());
                List<Object> reportsArray = new ArrayList<Object>();
                for (int b = 0; b < reports.size(); b++) {

                    LinkedHashMap<String, String> report = new LinkedHashMap<>();

                    report.put("id", reports.get(b).getId().toString());
                    report.put("title", reports.get(b).getTitle());
                    report.put("description", reports.get(b).getDescription());
                    report.put("date", reports.get(b).getDate().toString());
                    reportsArray.add(report);
                }
                
                List<Object> messagesArray = new ArrayList<Object>();
                if (group != null) {

                    List<GroupMessage> messages = groupMessageService.getGroupMessagesByTask(t.get(i).getId());
                    
                    for (int c = 0; c < messages.size(); c++) {
                        
                        LinkedHashMap<String, String> message = new LinkedHashMap<>();
                        message.put("id", messages.get(c).getId().toString());
                        message.put("group", messages.get(c).getGroup().getId().toString());
                        message.put("user", messages.get(c).getUser().getName()+" "+messages.get(c).getUser().getSubname());
                        message.put("subject", messages.get(c).getSubject());
                        message.put("message", messages.get(c).getMessage());
                        message.put("date", messages.get(c).getDate().toString());
                        messagesArray.add(message);
                    }
                }
                
                List<Incidence> incidences = incidenceService.getIncidencesByTaskId(t.get(i).getId());
                List<Object> incidencesArray = new ArrayList<Object>();
                Integer incidenceTotal = 0;
                for (int d = 0; d < incidences.size(); d++) {
                    
                    LinkedHashMap<String, String> incidence = new LinkedHashMap<>();
                    incidence.put("id", incidences.get(d).getId().toString());
                    incidence.put("title", incidences.get(d).getTitle());
                    incidence.put("description", incidences.get(d).getDescription());
                    incidence.put("date", incidences.get(d).getDate().toString());
                    incidence.put("status", incidences.get(d).getIncidenceStatus().getStatus());
                    if ("open".equals(incidences.get(d).getIncidenceStatus().getStatus())){
                        incidenceTotal += 1;
                    }
                    incidencesArray.add(incidence);
                }

                    //Build Task
                    task.put("id", t.get(i).getId().toString());
                    task.put("title", t.get(i).getTitle());
                    task.put("description", t.get(i).getDescription());
                    task.put("dateCreate", t.get(i).getDateCreate().toString());
                    task.put("dateFinish", t.get(i).getDateFinish().toString());
                    task.put("status", t.get(i).getTaskStatus().getStatus());
                    if (t.get(i).getGroup() != null) {
                        task.put("group", t.get(i).getGroup().toString());
                    }
                    if (t.get(i).getUser() != null) {
                        task.put("userId", t.get(i).getUser().getId().toString());
                    }
                    task.put("priority", t.get(i).getTaskPriority().getPriority());

                    Date today = new Date();
                    Date dateTwo = t.get(i).getDateFinish();
                    long diff = today.getTime() - dateTwo.getTime();
                    long countDays = (diff / (1000 * 60 * 60 * 24));
                    task.put("countDays", Long.toString((Math.abs(countDays))));

                    double x = close;
                    double y = milestones.size();
                    double percentage = ((x / y) * 100);

                    task.put("completed", Integer.toString((int) percentage));
                    task.put("incidencetotal",incidenceTotal.toString());

                    taskArray.put("task", task);
                    taskArray.put("milestones", milestonesArray);
                    taskArray.put("reports", reportsArray);
                    taskArray.put("messages", messagesArray);
                    taskArray.put("incidences", incidencesArray);
                    taskModel.add(taskArray);
                }

                //m.addAttribute("taskslist", t);
                if (group != null) {
                    m.addAttribute("group", group);
                }
                m.addAttribute("taskmodel", taskModel);
                return "views/tasks";
            }

            return "redirect:index.html";
        }
 

        @RequestMapping(value = "/addreport", method = RequestMethod.POST)
        public String addReport(HttpSession session, @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "task", required = true) int taskId
    ) throws ParseException {
            if ((session.getAttribute("namesession")) != null) {

                User u = new User();
                u = userService.getUserByName(session.getAttribute("namesession").toString());
                TaskReport report = new TaskReport();
                Task task = new Task();
                task = taskService.getTask(taskId);
                if (task.getUser().getId() == u.getId()) {
                    report.setTask(task);
                    report.setTitle(title);
                    report.setDescription(description);
                    Date myDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
                    String myDateString = sdf.format(myDate);
                    report.setDate(sdf.parse(myDateString));
                    taskReportService.add(report);
                    return "redirect:tasks.html";
                } else {
                    // Atributte Flash advice
                    return "redirect:tasks.html";
                }

            }

            return "redirect:index.html";

        }


}
