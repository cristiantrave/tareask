package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.EmailMessage;

public interface EmailMessageDao {
    public void add (EmailMessage emailMessage);
    public void edit (EmailMessage emailMessage);
    public void delete (int EmailMessageId);
    public EmailMessage getEmailMessage (int EmailMessageId);
    public List getAllEmailMessages();
    public EmailMessage getLastEmailMessageByConversation(Integer conversation);
    public List<EmailMessage> getAllEmailMessagesByConversation(Integer conversation);
    public EmailMessage getFirstEmailofConversation (Integer conversation);
    public List<EmailMessage> getLastMessagesByReceptor(int UserId,int max);
}
