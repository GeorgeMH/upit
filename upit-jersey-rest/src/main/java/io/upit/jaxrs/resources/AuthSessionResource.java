package io.upit.jaxrs.resources;

import com.google.inject.Provider;
import io.upit.dal.AuthSessionDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AuthSessionImpl;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource extends AbstractResource<AuthSession, String> {

    private final AuthSessionDAO authSessionDao;
    private final UserDAO userDao;

    @Inject
    public AuthSessionResource(AuthSessionDAO authSessionDAO, UserDAO userDao) {
        super(AuthSession.class, authSessionDAO);
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
        authSession.setId(UUID.randomUUID().toString());
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
    @Path("validate/${sessionId}")
    public AuthSession validate(@PathParam("sessionId") String sessionId) {
        return authSessionDao.getById(sessionId);
    }

    @DELETE
    @Path("end/")
    public void end(AuthSession session) {
        authSessionDao.delete(session);
    }

}
