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
 * GroupStatus generated by hbm2java
 */
@Entity
@Table(name="group_status"
    ,catalog="tareask"
)
public class GroupStatus  implements java.io.Serializable {


     private Integer id;
     private String status;
     private Set<Group> groups = new HashSet<Group>(0);

    public GroupStatus() {
    }

	
    public GroupStatus(String status) {
        this.status = status;
    }
    public GroupStatus(String status, Set<Group> groups) {
       this.status = status;
       this.groups = groups;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="status", nullable=false, length=20)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="groupStatus")
    public Set<Group> getGroups() {
        return this.groups;
    }
    
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }




}


