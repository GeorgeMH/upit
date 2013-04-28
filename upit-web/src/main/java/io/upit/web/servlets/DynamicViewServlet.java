package io.upit.web.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DynamicViewServlet extends HttpServlet {
	private static final long serialVersionUID = -2945094338794751775L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Parse URL, load knockout template, output it.
	}

}
