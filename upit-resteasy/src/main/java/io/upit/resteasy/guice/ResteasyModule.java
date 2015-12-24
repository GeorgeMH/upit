package io.upit.resteasy.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.upit.resteasy.resources.AuthSessionResource;
import io.upit.resteasy.resources.FileDownloadRequest;
import io.upit.resteasy.resources.PasteResource;
import io.upit.resteasy.resources.UploadedFileResource;
import io.upit.resteasy.resources.UserResource;

public class ResteasyModule implements Module {

    public void configure(final Binder binder) {
        binder.bind(AuthSessionResource.class);
        binder.bind(FileDownloadRequest.class);
        binder.bind(PasteResource.class);
        binder.bind(UploadedFileResource.class);
        binder.bind(UserResource.class);
    }

}
