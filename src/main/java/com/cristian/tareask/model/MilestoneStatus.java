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
 * MilestoneStatus generated by hbm2java
 */
@Entity
@Table(name="milestone_status"
    ,catalog="tareask"
)
public class MilestoneStatus  implements java.io.Serializable {


     private Integer id;
     private String status;
     private Set<Milestone> milestones = new HashSet<Milestone>(0);

    public MilestoneStatus() {
    }

	
    public MilestoneStatus(String status) {
        this.status = status;
    }
    public MilestoneStatus(String status, Set<Milestone> milestones) {
       this.status = status;
       this.milestones = milestones;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="milestoneStatus")
    public Set<Milestone> getMilestones() {
        return this.milestones;
    }
    
    public void setMilestones(Set<Milestone> milestones) {
        this.milestones = milestones;
    }




}


