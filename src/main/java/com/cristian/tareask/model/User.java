package com.cristian.tareask.model;
// Generated 04-jul-2016 12:42:26 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="tareask"
)
public class User  implements java.io.Serializable {


     private Integer id;
     private Department department;
     private Grade grade;
     private Role role;
     private UserStatus userStatus;
     private String name;
     private String subname;
     private String nickname;
     private String password;
     private String email;
     private int phone;
     private Date birthdate;
     private Date admitDate;
     private Set<SessionUser> sessionUsers = new HashSet<SessionUser>(0);
     private Set<GroupMessage> groupMessages = new HashSet<GroupMessage>(0);
     private Set<EmailMessage> emailMessages = new HashSet<EmailMessage>(0);
     private Set<GroupUsers> groupUserses = new HashSet<GroupUsers>(0);
     private Set<Task> tasks = new HashSet<Task>(0);
     private Set<EmailContact> emailContacts = new HashSet<EmailContact>(0);
     private Set<MessageReceptor> messageReceptors = new HashSet<MessageReceptor>(0);
     private Set<Email> emails = new HashSet<Email>(0);

    public User() {
    }

	
    public User(Department department, Grade grade, Role role, UserStatus userStatus, String name, String subname, String nickname, String password, String email, int phone, Date birthdate, Date admitDate) {
        this.department = department;
        this.grade = grade;
        this.role = role;
        this.userStatus = userStatus;
        this.name = name;
        this.subname = subname;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.admitDate = admitDate;
    }
    public User(Department department, Grade grade, Role role, UserStatus userStatus, String name, String subname, String nickname, String password, String email, int phone, Date birthdate, Date admitDate, Set<SessionUser> sessionUsers, Set<GroupMessage> groupMessages, Set<EmailMessage> emailMessages, Set<GroupUsers> groupUserses, Set<Task> tasks, Set<EmailContact> emailContacts, Set<MessageReceptor> messageReceptors, Set<Email> emails) {
       this.department = department;
       this.grade = grade;
       this.role = role;
       this.userStatus = userStatus;
       this.name = name;
       this.subname = subname;
       this.nickname = nickname;
       this.password = password;
       this.email = email;
       this.phone = phone;
       this.birthdate = birthdate;
       this.admitDate = admitDate;
       this.sessionUsers = sessionUsers;
       this.groupMessages = groupMessages;
       this.emailMessages = emailMessages;
       this.groupUserses = groupUserses;
       this.tasks = tasks;
       this.emailContacts = emailContacts;
       this.messageReceptors = messageReceptors;
       this.emails = emails;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="department", nullable=false)
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="grade", nullable=false)
    public Grade getGrade() {
        return this.grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="role", nullable=false)
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="status", nullable=false)
    public UserStatus getUserStatus() {
        return this.userStatus;
    }
    
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    
    @Column(name="name", nullable=false, length=20)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="subname", nullable=false, length=20)
    public String getSubname() {
        return this.subname;
    }
    
    public void setSubname(String subname) {
        this.subname = subname;
    }

    
    @Column(name="nickname", nullable=false, length=20)
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    
    @Column(name="password", nullable=false, length=100)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="email", nullable=false, length=50)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="phone", nullable=false)
    public int getPhone() {
        return this.phone;
    }
    
    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="birthdate", nullable=false, length=10)
    public Date getBirthdate() {
        return this.birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="admitDate", nullable=false, length=10)
    public Date getAdmitDate() {
        return this.admitDate;
    }
    
    public void setAdmitDate(Date admitDate) {
        this.admitDate = admitDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<SessionUser> getSessionUsers() {
        return this.sessionUsers;
    }
    
    public void setSessionUsers(Set<SessionUser> sessionUsers) {
        this.sessionUsers = sessionUsers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<GroupMessage> getGroupMessages() {
        return this.groupMessages;
    }
    
    public void setGroupMessages(Set<GroupMessage> groupMessages) {
        this.groupMessages = groupMessages;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<EmailMessage> getEmailMessages() {
        return this.emailMessages;
    }
    
    public void setEmailMessages(Set<EmailMessage> emailMessages) {
        this.emailMessages = emailMessages;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<GroupUsers> getGroupUserses() {
        return this.groupUserses;
    }
    
    public void setGroupUserses(Set<GroupUsers> groupUserses) {
        this.groupUserses = groupUserses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Task> getTasks() {
        return this.tasks;
    }
    
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<EmailContact> getEmailContacts() {
        return this.emailContacts;
    }
    
    public void setEmailContacts(Set<EmailContact> emailContacts) {
        this.emailContacts = emailContacts;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<MessageReceptor> getMessageReceptors() {
        return this.messageReceptors;
    }
    
    public void setMessageReceptors(Set<MessageReceptor> messageReceptors) {
        this.messageReceptors = messageReceptors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Email> getEmails() {
        return this.emails;
    }
    
    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }




}


