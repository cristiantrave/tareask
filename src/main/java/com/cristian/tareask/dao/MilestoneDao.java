package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.Milestone;

public interface MilestoneDao {
    public void add (Milestone milestone);
    public void edit (Milestone milestone);
    public void delete (int MilestoneId);
    public Milestone getMilestone (int MilestoneId);
    public List getAllMilestones();
    public Milestone getMilestoneByName(String MilestoneName);
    public List<Milestone> getMilestonesByTask(int TaskId);
}
