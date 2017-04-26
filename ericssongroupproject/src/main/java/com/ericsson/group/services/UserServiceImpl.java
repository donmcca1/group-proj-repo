package com.ericsson.group.services;

import com.ericsson.group.dao.UserDAO;
import com.ericsson.group.entities.User;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Collection;

/**
 * Created by d16125338 on 06/04/2017.
 */
@Stateless
@Local
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserServiceImpl implements UserServiceLocal {

    @EJB
    private UserDAO dao;

    @Resource
	private SessionContext context;

    public Collection<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void addUser(User user) {
        if(!dao.getAllUsers().contains(user)) {
            dao.addUser(user);
        }
        else{
            context.setRollbackOnly();
        }
    }

    public void removeUser(Integer id) {
        dao.deleteUser(id);
    }

    public User findUser(String username, String password) {

        return dao.findUser(username,password);

    }
    public boolean checkUser(String username){
        return dao.checkUser(username);
    }


   /* public User getUser(String username) {
        return dao.getUser(username);
    }*/
}
