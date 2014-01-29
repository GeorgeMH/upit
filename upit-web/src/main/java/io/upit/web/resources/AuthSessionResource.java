package io.upit.web.resources;

import io.upit.core.dal.dao.AuthSessionDAO;
import io.upit.core.dal.models.AuthSession;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/auth")
@Produces("application/json")
public class AuthSessionResource {
	private final static String AUTH_SESSION_COOKIE_NAME = "authSession";

	private final AuthSessionDAO authSessionDao;

	@Inject
	public AuthSessionResource(AuthSessionDAO authSessionDao) {
		this.authSessionDao = authSessionDao;
	}

	@POST
	@Path("login/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response login(String userName, String passWord, boolean rememberMe) {
		// TODO: Input Validation

		// TODO: Load a plugin to do authentication and associate it with a local user

		// Hash the password
		AuthSession session = new AuthSession();
		session.setSessionId(UUID.randomUUID().toString());
		session.setLastAccessed(new Date());
		// session.setUserId(userId);

		AuthSession createdSession = authSessionDao.create(session);
		return Response.ok(createdSession).cookie(new NewCookie(new Cookie(AUTH_SESSION_COOKIE_NAME, null))).build();
	}

	@POST
	@Path("validate/")
	@Transactional
	public AuthSession validateSession(AuthSession session){
		//First check to see if there is an active matching session for the given sessionId and authToken
		AuthSession realSession = authSessionDao.getActiveMatchingSession(session);
		if(null == realSession){
			return null;
		}

		realSession.setLastAccessed(new Date());
		authSessionDao.update(realSession);

		return realSession;
	}

}
