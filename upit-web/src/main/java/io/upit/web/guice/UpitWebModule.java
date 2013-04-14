package io.upit.web.guice;

import io.upit.web.servlets.DynamicViewServlet;
import io.upit.web.servlets.UpitApiServlet;

import javax.servlet.http.HttpServlet;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class UpitWebModule extends ServletModule {

	@Override
	protected void configureServlets() {
		bindServlet("/views/*", DynamicViewServlet.class);
		bindServlet("/api*", UpitApiServlet.class);
	}

	private void bindServlet(String path, Class<? extends HttpServlet> servletClass) {
		bind(servletClass).in(Singleton.class);
		serve(path).with(servletClass);
	}
	
}
