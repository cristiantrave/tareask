package com.cristian.tareask.model;
// Generated 04-jul-2016 12:42:26 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Milestone generated by hbm2java
 */
@Entity
@Table(name="milestone"
    ,catalog="tareask"
)
public class Milestone  implements java.io.Serializable {


     private Integer id;
     private MilestoneStatus milestoneStatus;
     private Task task;
     private String title;
     private String description;
     private Integer order;

    public Milestone() {
    }

    public Milestone(MilestoneStatus milestoneStatus, Task task, String title, String description, int order) {
       this.milestoneStatus = milestoneStatus;
       this.task = task;
       this.title = title;
       this.description = description;
       this.order = order;
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
    @JoinColumn(name="status", nullable=false)
    public MilestoneStatus getMilestoneStatus() {
        return this.milestoneStatus;
    }
    
    public void setMilestoneStatus(MilestoneStatus milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="task", nullable=false)
    public Task getTask() {
        return this.task;
    }
    
    public void setTask(Task task) {
        this.task = task;
    }

    
    @Column(name="title", nullable=false, length=50)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="description", nullable=false, length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="order", nullable=false)
    public Integer getOrder() {
        return this.order;
    }
    
    public void setOrder(Integer order) {
        this.order = order;
    }




}


