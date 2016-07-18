
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.Email;

public interface EmailService {
	public void add(Email email);
	public void edit(Email email);
	public Email getEmail(int EmailId);
        public Email getEmailByUserId(Integer UserId);
}
