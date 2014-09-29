package io.upit.jaxrs.resources;

import io.upit.dal.AuthSessionDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AuthSessionImpl;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/auth")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource {

    private final AuthSessionDAO authSessionDao;
    private final UserDAO userDao;

    @Inject
    public AuthSessionResource(AuthSessionDAO authSessionDAO, UserDAO userDao) {
        this.authSessionDao = authSessionDAO;
        this.userDao = userDao;
    }

    @POST
    @Path("login/")
    public AuthSession login(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        User user = userDao.getByUserNameOrEmail(userName);
        if (null == user) {
            return null;
        }

        Calendar currentCalendar = Calendar.getInstance();

        AuthSession authSession = new AuthSessionImpl();
        authSession.setSessionId(UUID.randomUUID().toString());
        authSession.setUserId(user.getId());
        authSession.setCreated(currentCalendar.getTime());
        authSession.setActive(true);

        currentCalendar.add(Calendar.YEAR, 1);

        //TODO: Make Expire Configurable
        authSession.setExpires(currentCalendar.getTime());

        authSessionDao.create(authSession);

        return authSession;
    }

    @POST
    @Path("validate/")
    public AuthSession validate(AuthSession session) {
        return authSessionDao.getById(session.getSessionId());
    }

    @DELETE
    @Path("end/")
    public void end(AuthSession session) {
        authSessionDao.delete(session);
    }

}
