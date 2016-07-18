/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.EmailConversation;

public interface EmailConversationService {
	public void add(EmailConversation emailConversation);
	public void edit(EmailConversation emailConversation);
	public void delete(int EmailConversationId);
	public EmailConversation getEmailConversation(int EmailConversationId);
	public List getAllEmailConversations(int idUser);
        public List getConversationByFolder(Integer idUser,String folder);
        public List getEmailConversationByFolderAndUpdate(Integer folder);
        public List getLastEmailConversation();
}
