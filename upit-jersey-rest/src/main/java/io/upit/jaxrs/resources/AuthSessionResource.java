package io.upit.jaxrs.resources;

import com.google.inject.persist.Transactional;
import io.upit.dal.AuthSessionDAO;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.jpa.models.JpaAuthSession;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AuthSessionImpl;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.jaxrs.exceptions.ResourceException;

@Path("/authSession")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource extends AbstractResource<AuthSession, String> {

    private final AuthSessionDAO authSessionDao;
    private final UserDAO userDao;
    private final AuthenticationMetaDataDAO authenticationMetaDataDAO;

    @Inject
    public AuthSessionResource(AuthSessionDAO authSessionDAO, UserDAO userDao, AuthenticationMetaDataDAO authenticationMetaDataDAO) {
        super(AuthSession.class, authSessionDAO);
        this.authSessionDao = authSessionDAO;
        this.userDao = userDao;
        this.authenticationMetaDataDAO = authenticationMetaDataDAO;
    }

    @POST
    @Path("login/")
    @Transactional
    public AuthSession login(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        User user = userDao.getByUserNameOrEmail(userName);
        if (null == user) {
            throw new ResourceException("Invalid username or password");
        }

        // TODO: authenticate? The authentication meta data to use should probalby be specified in the AuthenticationRequest object passed to this method
        List<AuthenticationMetaData> authenticationMetaDatas = authenticationMetaDataDAO.getByUserId(user.getId());
        for(AuthenticationMetaData authenticationMetaData : authenticationMetaDatas){

        }

        Calendar currentCalendar = Calendar.getInstance();

        AuthSession authSession = new JpaAuthSession();
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
        AuthSession ret = authSessionDao.getById(sessionId);
        return null != ret && ret.isActive() ? ret : null;
    }

    @DELETE
    @Path("end/${sessionId}")
    public AuthSession end(AuthSession session) {
        AuthSession deletedSession = authSessionDao.delete(session);
        deletedSession.setActive(false);
        update(deletedSession);
        return deletedSession;
    }

}
