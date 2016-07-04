package com.cristian.tareask.model;
// Generated 04-jul-2016 12:42:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserStatus generated by hbm2java
 */
@Entity
@Table(name="user_status"
    ,catalog="tareask"
    , uniqueConstraints = @UniqueConstraint(columnNames="status") 
)
public class UserStatus  implements java.io.Serializable {


     private Integer id;
     private String status;
     private Set<User> users = new HashSet<User>(0);

    public UserStatus() {
    }

	
    public UserStatus(String status) {
        this.status = status;
    }
    public UserStatus(String status, Set<User> users) {
       this.status = status;
       this.users = users;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="status", unique=true, nullable=false, length=20)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userStatus")
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }




}


