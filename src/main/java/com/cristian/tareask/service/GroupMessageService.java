/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.GroupMessage;

public interface GroupMessageService {
	public void add(GroupMessage groupMessage);
	public void edit(GroupMessage groupMessage);
	public void delete(int GroupMessageId);
	public GroupMessage getGroupMessage(int GroupMessageId);
	public List getAllGroupMessages();
        public GroupMessage getGroupMessageByName(String GroupMessageName);
        public List<GroupMessage> getGroupMessagesByTask(int TaskId);
}
