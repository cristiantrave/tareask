package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.EmailFolder;

public interface EmailFolderDao {
    public void add (EmailFolder emailFolder);
    public void edit (EmailFolder emailFolder);
    public void delete (int EmailFolderId);
    public EmailFolder getEmailFolder (int EmailFolderId);
    public List getAllEmailFolders();
    public EmailFolder getIdFolderByName(String nameFolder);
}
