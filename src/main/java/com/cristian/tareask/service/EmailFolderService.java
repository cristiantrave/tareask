/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.EmailFolder;

public interface EmailFolderService {
	public void add(EmailFolder emailFolder);
	public void edit(EmailFolder emailFolder);
	public void delete(int EmailFolderId);
	public EmailFolder getEmailFolder(int EmailFolderId);
	public List getAllEmailFolders();
        public EmailFolder getEmailFolderByName(String EmailFolderName);
}
