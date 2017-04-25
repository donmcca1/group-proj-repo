package com.ericsson.group.jaxrs;

import com.ericsson.group.entities.User;
import com.ericsson.group.services.UserServiceLocal;

import javax.ejb.EJB;
import javax.mail.event.MailEvent;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by d16125338 on 06/04/2017.
 */
@Path("/users")
public class UserCRUDService {


    @EJB
    private UserServiceLocal service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers(){
        return service.getAllUsers();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user){
        System.out.println(user.getUsername());
        service.addUser(user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") Integer id) {
        service.removeUser(id);
    }

    @GET
    @Path("/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean findUser(@PathParam("username") String username, @PathParam("password") String password){
        return service.findUser(username, password);
    }


}
