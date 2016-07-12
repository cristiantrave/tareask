package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.GroupMessage;

public interface GroupMessageDao {
    public void add (GroupMessage groupMessage);
    public void edit (GroupMessage groupMessage);
    public void delete (int GroupMessageId);
    public GroupMessage getGroupMessage (int GroupMessageId);
    public List getAllGroupMessages();
    public GroupMessage getGroupMessageByName(String GroupMessageName);
    public List<GroupMessage> getGroupMessagesByTask(int TaskId);
}
