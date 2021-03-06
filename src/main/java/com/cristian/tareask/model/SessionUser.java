package com.cristian.tareask.model;
// Generated 04-jul-2016 12:42:26 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SessionUser generated by hbm2java
 */
@Entity
@Table(name="session_user"
    ,catalog="tareask"
)
public class SessionUser  implements java.io.Serializable {


     private Integer id;
     private User user;
     private Date last;
     private Date current;

    public SessionUser() {
    }

    public SessionUser(User user, Date last, Date current) {
       this.user = user;
       this.last = last;
       this.current = current;
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
    @JoinColumn(name="user", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last", nullable=false, length=19)
    public Date getLast() {
        return this.last;
    }
    
    public void setLast(Date last) {
        this.last = last;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="current", nullable=false, length=19)
    public Date getCurrent() {
        return this.current;
    }
    
    public void setCurrent(Date current) {
        this.current = current;
    }




}


