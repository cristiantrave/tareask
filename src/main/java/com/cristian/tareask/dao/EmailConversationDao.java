package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.EmailConversation;

public interface EmailConversationDao {
    public void add (EmailConversation emailConversation);
    public void edit (EmailConversation emailConversation);
    public void delete (int EmailConversationId);
    public EmailConversation getEmailConversation (int EmailConversationId);
    public List getAllEmailConversations(int idUser);
    public List getConversationByFolder(Integer idUser,String folder);
    public List getEmailConversationByFolderAndUpdate(Integer folder);
    public List getLastEmailConversation();
}
