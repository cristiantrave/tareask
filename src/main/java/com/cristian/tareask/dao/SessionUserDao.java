package com.cristian.tareask.dao;

import java.util.List;
import com.cristian.tareask.model.SessionUser;

public interface SessionUserDao {
    public void add (SessionUser sessionUser);
    public void edit (SessionUser sessionUser);
    public void delete (int SessionId);
    public SessionUser getSessionUser (int SessionUserId);
    public List getAllSessionUser();
    public SessionUser getSessionUserByUser(int UserId);
}