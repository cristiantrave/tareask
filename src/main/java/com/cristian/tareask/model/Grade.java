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

/**
 * Grade generated by hbm2java
 */
@Entity
@Table(name="grade"
    ,catalog="tareask"
)
public class Grade  implements java.io.Serializable {


     private Integer id;
     private String grade;
     private Set<User> users = new HashSet<User>(0);

    public Grade() {
    }

	
    public Grade(String grade) {
        this.grade = grade;
    }
    public Grade(String grade, Set<User> users) {
       this.grade = grade;
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

    
    @Column(name="grade", nullable=false, length=50)
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="grade")
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }




}


