package com.ericsson.group.dao;

import com.ericsson.group.entities.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by d16125338 on 06/04/2017.
 */
@Stateless
@Local
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager em;


    public Collection<User> getAllUsers() {

        Query query = em.createQuery("from User");
        return (List<User>) query.getResultList();
    }


    public void addUser(User user) {
        em.persist(user);
    }


    public void deleteUser(Integer id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }


    public User findUser(String username, String password) {
        Query query = em.createQuery("select   c from User c where c.password = :password AND  c.username = :username ");
       /* return  (User)query.setParameter("username", username).setParameter("password", password).setMaxResults(1).getResultList().isEmpty();*/
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User)query.getSingleResult();
    }

    //to check is user exists
    public boolean checkUser(String username) {
        Query query = em.createQuery("from User c where c.username = :username");
        boolean status = query.setParameter("username", username).setMaxResults(1).getResultList().isEmpty();
        return status;
    }

   /* public User getUser(String username) {
        Query query = em.createQuery("select c from User c where c.username = :username");
        query.setParameter("username", username);
        return (User)query.getSingleResult();
    }*/
}
