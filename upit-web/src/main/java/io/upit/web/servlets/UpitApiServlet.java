package io.upit.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpitApiServlet extends HttpServlet {
	private static final long serialVersionUID = -3200459145653183948L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		
		try (PrintWriter out = resp.getWriter()) {
			out.println("API: " + req.getParameterMap());
			out.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

	}

}
