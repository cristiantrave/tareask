/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.Milestone;

public interface MilestoneService {
	public void add(Milestone milestone);
	public void edit(Milestone milestone);
	public void delete(int MilestoneId);
	public Milestone getMilestone(int MilestoneId);
	public List getAllMilestones();
        public Milestone getMilestoneByName(String MilestoneName);
        public List<Milestone> getMilestonesByTask(int TaskId);
}
