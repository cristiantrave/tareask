package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.SessionUser;

public interface SessionUserService {
    public void add (SessionUser sessionUser);
    public void edit (SessionUser sessionUser);
    public void delete (int SessionUserId);
    public SessionUser getSessionUser (int SessionUserId);
    public List getAllSessionUser();
    public SessionUser getSessionUserByUser(int UserId);
}