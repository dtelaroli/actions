package br.com.caelum.vraptor.actions.api.db;

import javax.inject.Inject;

import br.com.caelum.vraptor.actions.api.Database;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.ioc.Container;

public class DefaultDataDb implements Db {

	private final Container container;

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultDataDb() {
		this(null);
	}
	
	@Inject
	public DefaultDataDb(Container container) {
		this.container = container;
	}

	@Override
	public <T extends Database> T use(Class<T> db) {
		return container.instanceFor(db);
	}

}
