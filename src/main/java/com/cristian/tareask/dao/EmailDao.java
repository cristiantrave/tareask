package com.cristian.tareask.dao;

import com.cristian.tareask.model.Email;

public interface EmailDao {
    public void add (Email email);
    public void edit (Email email);
    public Email getEmail (int EmailId);
    public Email getEmailByUserId(Integer UserId);
}

