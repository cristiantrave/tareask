/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.MessageReceptor;

public interface MessageReceptorService {
	public void add(MessageReceptor messageReceptor);
	public void edit(MessageReceptor messageReceptor);
	public void delete(int MessageReceptorId);
	public MessageReceptor getMessageReceptor(int MessageReceptorId);
	public List getAllMessageReceptors();
        public List getLastMailByUserReceptor(Integer idUser);
        public MessageReceptor getMessageReceptorByName(String MessageReceptorName);
        public List getAllMessageReceptorsByConversation(Integer idConversation);
        public List<Integer> getAllMessageReceptorsByConversationUnique(Integer idConversation);
        public List<Integer> getAllMessageReceptorsByMessageUnique(Integer idMessage);
}
