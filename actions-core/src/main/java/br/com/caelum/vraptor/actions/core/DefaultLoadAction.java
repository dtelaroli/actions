package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.find;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.AbstractAction;
import br.com.caelum.vraptor.actions.api.action.LoadAction;

public class DefaultLoadAction extends AbstractAction implements LoadAction {

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultLoadAction() {
		this(null, null);
	}
	
	@Inject
	public DefaultLoadAction(Result result, Db db) {
		super(result, db);
	}

	@Override
	public <T> T by(Class<T> type, Object id) {
		return db().use(find()).by(type, id);
	}

}
