package io.upit.jaxrs.guice.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.servlet.RequestScoped;
import io.upit.security.RequestSession;

@RequestScoped
public class RequestSessionProvider implements Provider<RequestSession> {

    private final RequestSession requestSession;

    @Inject
    public RequestSessionProvider(@Named("RequestSession") RequestSession requestSession) {
        this.requestSession = requestSession;
    }

    @Override
    public RequestSession get() {
        return requestSession;
    }

}
