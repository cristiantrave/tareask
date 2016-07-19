/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.controller;


import static com.sun.deploy.uitoolkit.impl.fx.DeployPerfLogger.timestamp;
import com.cristian.tareask.dao.MessageReceptorDao;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import com.cristian.tareask.model.Email;
import com.cristian.tareask.model.EmailConversation;
import com.cristian.tareask.model.EmailFolder;
import com.cristian.tareask.model.EmailMessage;
import com.cristian.tareask.model.MessageReceptor;
import com.cristian.tareask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cristian.tareask.service.EmailConversationService;
import com.cristian.tareask.service.EmailFolderService;
import com.cristian.tareask.service.EmailMessageService;
import com.cristian.tareask.service.EmailService;
import com.cristian.tareask.service.MessageReceptorService;
import com.cristian.tareask.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class ProfileController {

    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    EmailMessageService emailMessageService;
    @Autowired
    EmailConversationService emailConversationService;
    @Autowired
    EmailFolderService emailFolderService;
    @Autowired
    MessageReceptorService messageReceptorService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model m, HttpSession session, @RequestParam(value = "inbox", required = false) String folder) {
        if ((session.getAttribute("namesession")) != null) {
            User u = new User();

            // User with data session
            u = userService.getUserByName(session.getAttribute("namesession").toString());

            // Cargar correo del usuario
            Email email = new Email();
            email = emailService.getEmailByUserId(u.getId());

            // Listar los correos últimos correos de cada conversacion
            List<EmailConversation> e = emailConversationService.getAllEmailConversations(u.getId());
            List<EmailFolder> f = emailFolderService.getAllEmailFolders();

            // Preparar listado de todos los usuarios como lista de contactos
            List<User> ulist = userService.getAllUsers();

            m.addAttribute("conversations", e);
            m.addAttribute("folders", f);
            m.addAttribute("user", u);
            m.addAttribute("listusers", ulist);

            return "views/profile";
        }

        return "redirect:index.html";
    }

    @RequestMapping(value = "/getconversation", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    String getConversation(@RequestParam Integer idConversation) throws JsonProcessingException {

        List<EmailMessage> mailsConversation = emailMessageService.getAllEmailMessagesByConversation(idConversation);
        List<MessageReceptor> receptors = messageReceptorService.getAllMessageReceptorsByConversation(idConversation);
        String receptorsString = "";
        for (int a = 0; a < receptors.size(); a++) {
            receptorsString += receptors.get(a).getUser().getName();
            if (a != (receptors.size() - 1)) {
                receptorsString += ",";
            }
        }

        int size = mailsConversation.size();
        String json = "[";

        for (int i = 0; i < size; i++) {

            json
                    += "{\n"
                    + "    \"name\": \"" + mailsConversation.get(i).getUser().getName() + "\",\n"
                    + "    \"subject\": \"" + mailsConversation.get(i).getSubject() + "\",\n"
                    + "    \"content\": \"" + mailsConversation.get(i).getContent() + "\",\n"
                    + "    \"date\": \"" + mailsConversation.get(i).getDate() + "\",\n"
                    + "    \"receptors\": \"" + receptorsString + "\"\n"
                    + "}\n";
            if (i == (size - 1)) {

            } else {
                json += ",";
            }
        }

        json += "]";

        return json;
    }

    @RequestMapping(value = "/addemail", method = RequestMethod.POST)
    public String addEmail(HttpSession session,
            @RequestParam(value = "receptors", defaultValue = "reply", required = false) String receptors,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "conversation", defaultValue = "0", required = false) int idConversation,
            final RedirectAttributes redirectAttrs) throws ParseException {

        // Identifico al usuario que manda el correo
        if ((session.getAttribute("namesession")) != null) {
            User u = new User();

            u = userService.getUserByName(session.getAttribute("namesession").toString());

            EmailMessage em = new EmailMessage();
            em.setUser(u);
            em.setSubject(subject);
            em.setContent(content);
            Date myDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
            String myDateString = sdf.format(myDate);
            em.setDate(sdf.parse(myDateString));
            if ("reply".equals(receptors)) {
                EmailConversation ec = new EmailConversation();
                ec = emailConversationService.getEmailConversation(idConversation);
                em.setEmailConversation(ec);
                ec.setEmailMessage(em);
                emailMessageService.add(em);
                emailConversationService.edit(ec);

                List<Integer> list = messageReceptorService.getAllMessageReceptorsByMessageUnique(emailMessageService.getFirstEmailofConversation(idConversation).getId());
                list.add(emailMessageService.getFirstEmailofConversation(idConversation).getUser().getId());
                //List<Integer> receptorslist = messageReceptorService.getAllMessageReceptorsByConversationUnique(idConversation);
                for (int i = 0; i < list.size(); i++) {
                    MessageReceptor mr = new MessageReceptor();
                    User uReceptor = new User();
                    uReceptor = userService.getUser(list.get(i));
                    if (uReceptor.getId() != u.getId()) {

                        mr.setUser(uReceptor);
                        mr.setEmailConversation(ec);
                        mr.setEmailMessage(em);
                        messageReceptorService.add(mr);
                    }

                }
            } else {
                em.setEmailConversation(null);
                emailMessageService.add(em);
                // Creo la conversación y le asigno el correo
                EmailConversation ec = new EmailConversation();
                ec.setEmailFolder(null);
                ec.setReaded(false);
                ec.setEmailMessage(em);
                emailConversationService.add(ec);

                // Ahora que tengo conversación, asigno a ese correo
                // la conversación
                em.setEmailConversation(ec);
                emailMessageService.edit(em);

                // El string con los receptores lo corto en cachos por las comas
                // y voy metiendolo en la lista de receptores con la iteración
                List<String> postReceptors = Arrays.asList(receptors.split("\\s*,\\s*"));
                for (int i = 0; i < postReceptors.size(); i++) {

                    User userTemp = new User();
                    if (postReceptors.get(i) != "") {

                        userTemp = userService.getUserByEmail(postReceptors.get(i)); // Cambiar por getuserbynickname
                        MessageReceptor mr = new MessageReceptor();
                        mr.setEmailMessage(em);
                        mr.setUser(userTemp);
                        mr.setEmailConversation(ec);
                        messageReceptorService.add(mr);

                    }

                }

            }
            redirectAttrs.addFlashAttribute("message", "Email sent ;)");
            return "redirect:profile.html";
        }
        //Falta meter el m.addatri con el mensaje de si o no
        return "redirect:index.html";

    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.POST)
    public String editProfile(HttpSession session,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "subname") String subname,
            @RequestParam(value = "birthdate") String birthdate,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "oldpassword") String oldpassword,
            @RequestParam(value = "newpassword") String newpassword,
            final RedirectAttributes redirectAttrs) throws ParseException {

        if ((session.getAttribute("namesession")) != null) {

            User u = new User();

            u = userService.getUserByName(session.getAttribute("namesession").toString());
            if (name == null
                    || subname == null
                    || birthdate == null
                    || phone == null) {
                redirectAttrs.addFlashAttribute("editprofile", "Ups!. Unable to to update information.");
                return "redirect:profile.html";
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(birthdate);
            u.setName(name);
            u.setSubname(subname);
            u.setBirthdate(date);
            int phonenumber = Integer.parseInt(phone);
            u.setPhone(phonenumber);

            if (!"".equals(oldpassword) & !"".equals(newpassword)) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedOldPassword = passwordEncoder.encode(oldpassword);
                System.out.println(hashedOldPassword);
                if (passwordEncoder.matches(oldpassword, u.getPassword())) {
                    String hashedNewPassword = passwordEncoder.encode(newpassword);
                    u.setPassword(hashedNewPassword);
                } else {
                    redirectAttrs.addFlashAttribute("editprofile", "You must enter your old password.");
                    return "redirect:profile.html";
                }

            }
            userService.edit(u);
            redirectAttrs.addFlashAttribute("editprofile", "Your profile has been updated!");
            session.setAttribute("namesession", u.getName());
            return "redirect:profile.html";
        }
        return "";
    }

}
