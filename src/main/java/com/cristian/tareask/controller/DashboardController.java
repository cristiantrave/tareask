package com.cristian.tareask.controller;

import com.cristian.tareask.model.User;
import com.cristian.tareask.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    
    @Autowired
    UserService userService;

    @RequestMapping("/dashboard")
    public String loadDashboard(HttpSession session, Model m) {
        
        List<Object> dashboardModel = new ArrayList<Object>();
        User u = new User();
        
        if ((session.getAttribute("namesession")) != null) { 
            
            u = userService.getUserByName(session.getAttribute("namesession").toString());
            
            return "views/dashboard";
        } else {
            session.invalidate();
            return "redirect:index.html";
        }

    }

}