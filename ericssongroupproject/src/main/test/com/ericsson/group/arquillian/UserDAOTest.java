package com.ericsson.group.arquillian;

import com.ericsson.group.dao.BaseDAO;
import com.ericsson.group.dao.JPABaseDAO;
import com.ericsson.group.dao.UserDAO;
import com.ericsson.group.dao.UserDAOImpl;
import com.ericsson.group.entities.BaseData;
import com.ericsson.group.entities.User;
import com.ericsson.group.services.BaseService;
import com.ericsson.group.services.BaseServiceJPA;
import com.ericsson.group.services.UserServiceImpl;
import com.ericsson.group.services.UserServiceLocal;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mypc1 on 27/04/2017.
 */
@RunWith(Arquillian.class)
public class UserDAOTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(UserServiceImpl.class, UserServiceLocal.class, UserDAO.class, UserDAOImpl.class)
                .addPackage(User.class.getPackage())
                .addAsResource("resources-wildfly/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    UserDAO userDAO;

    User user = new User("test user2");


    @Test
    public final void test_Get_User() throws Exception {
        List<User> userBase = (List<User>) userDAO.getAllUsers();
        Assert.assertEquals("test user", userBase.get(0).getUsername());
    }

    @Test
    public final void test_Login_User() throws Exception {
        user.setPassword("test_password");
        user.setRole("System Administrator");
        User u = userDAO.findUser("test user", user.getPassword());
        Assert.assertEquals("test user", u.getUsername());
    }

    @Test
    public final void test_check_user() throws Exception {
        boolean testuser = userDAO.checkUser("test user");
        Assert.assertFalse(testuser); // returns false if user exists

        boolean testFalseUser = userDAO.checkUser("not in database");
        Assert.assertTrue(testFalseUser); // returns true not in db
    }

    // this test will fail if add user test hasn't been run prior to this test
    @Test
    public final void test_add_delete_user() throws Exception {
        user.setPassword("test_password");
        user.setRole("System Administrator");
        userDAO.addUser(user);

        User u = userDAO.findUser(user.getUsername(), "test_password");

        userDAO.deleteUser(u.getId());
    }
}
