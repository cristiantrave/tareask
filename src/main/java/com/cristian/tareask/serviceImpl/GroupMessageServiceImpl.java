/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.serviceImpl;

import com.cristian.tareask.dao.GroupMessageDao;
import java.util.List;
import javax.transaction.Transactional;
import com.cristian.tareask.model.GroupMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMessageServiceImpl implements com.cristian.tareask.service.GroupMessageService {

    @Autowired
    private GroupMessageDao groupMessageDao;

    @Transactional
    public void add(GroupMessage groupMessage) {      
        groupMessageDao.add(groupMessage);
    }

    @Transactional
    public void edit(GroupMessage groupMessage) {
        groupMessageDao.edit(groupMessage);
    }

    @Transactional
    public void delete(int GroupMessageId) {
        groupMessageDao.delete(GroupMessageId);
    }

    @Transactional
    public GroupMessage getGroupMessage(int GroupMessageId) {
        return groupMessageDao.getGroupMessage(GroupMessageId);
    }

    @Transactional
    public List getAllGroupMessages() {
        return groupMessageDao.getAllGroupMessages();
    }

    @Transactional
    public GroupMessage getGroupMessageByName(String GroupMessageName) {
        return groupMessageDao.getGroupMessageByName(GroupMessageName);
    }
    
    @Transactional
    public List<GroupMessage> getGroupMessagesByTask(int TaskId){
        return groupMessageDao.getGroupMessagesByTask(TaskId);
    }

}
