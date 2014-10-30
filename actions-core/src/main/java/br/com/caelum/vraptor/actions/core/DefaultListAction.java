package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.find;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.AbstractAction;
import br.com.caelum.vraptor.actions.api.action.ListAction;

@RequestScoped
public class DefaultListAction extends AbstractAction implements ListAction {

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultListAction() {
		this(null, null);
	}

	@Inject
	public DefaultListAction(Result result, Db db) {
		super(result, db);
	}
	
	@Override
	public <T> List<T> all(Class<T> type) {
		return db().use(find()).all(type);
	}
	
}
