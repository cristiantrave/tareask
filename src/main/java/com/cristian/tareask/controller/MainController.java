
package  com.cristian.tareask.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import com.cristian.tareask.model.SessionUser;
import com.cristian.tareask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cristian.tareask.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cristian.tareask.service.SessionUserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    SessionUserService sessionUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String first(HttpSession session) {

        // Aquí la idea es hacer una landing que exploque qué 
        // es y tenga el cajetín de login
        if ((session.getAttribute("namesession")) != null) {
            return "redirect:dashboard.html";
        }

        return "redirect:index.html";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model m, @ModelAttribute("u") @Validated User user, HttpSession session) {

        if ((session.getAttribute("namesession")) != null) {
            return "redirect:dashboard.html";
        }

        return "views/index";
    }

    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    public String LoginCheck(HttpSession session,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) throws ParseException {

        User u = new User();
        u = userService.getUserByEmail(email);
        if ((u == null)) {
            
            session.invalidate();
            return "redirect:index.html";
            
        } else {
            
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, u.getPassword())) {
                session.setAttribute("namesession", u.getName());
                SessionUser sessionCheck = sessionUserService.getSessionUserByUser(u.getId());

                SessionUser su = new SessionUser();
                su.setUser(u);
                Date myDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
                String myDateString = sdf.format(myDate);
                su.setCurrent(sdf.parse(myDateString));
                
                if (sessionCheck != null) {
                    su.setLast(sessionCheck.getCurrent());
                } else {
                    su.setLast(su.getCurrent());
                }
                sessionUserService.add(su);

                return "redirect:dashboard.html";
            } else {
                
                session.invalidate();
                return "redirect:index.html";
            }
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:index.html";

    }
}