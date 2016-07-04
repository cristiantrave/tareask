/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
public class MainController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWorld(Model m) {

        String name = "Cristian Trave";
        m.addAttribute("name", name);

        return "base/index";
    }
}