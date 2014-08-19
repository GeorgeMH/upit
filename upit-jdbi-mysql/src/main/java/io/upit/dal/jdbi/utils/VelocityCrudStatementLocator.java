package io.upit.dal.jdbi.utils;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.StatementLocator;

public class VelocityCrudStatementLocator implements StatementLocator {

	private final StatementLocator statementLocator;

	public VelocityCrudStatementLocator(StatementLocator inner) {
		statementLocator = inner;
	}

	@Override
	public String locate(String name, StatementContext ctx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
