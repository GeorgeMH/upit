package io.upit.jaxrs.guice;

import com.google.inject.AbstractModule;
import io.upit.jaxrs.resources.AuthSessionResource;
import io.upit.jaxrs.resources.FileDownloadRequest;
import io.upit.jaxrs.resources.PasteResource;
import io.upit.jaxrs.resources.UploadedFileResource;
import io.upit.jaxrs.resources.UserResource;

/**
 * Created by george on 10/19/15.
 */
public class ResteasyModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(AuthSessionResource.class);
        bind(FileDownloadRequest.class);
        bind(PasteResource.class);
        bind(UploadedFileResource.class);
        bind(UserResource.class);
    }
}
