package com.ericsson.group.services;

import com.ericsson.group.entities.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by d16125338 on 06/04/2017.
 */
@Local
public interface UserServiceLocal {
    public Collection<User> getAllUsers();
    public void addUser(User user);
    public void removeUser(Integer id);
    public boolean findUser(String username, String password);
}
