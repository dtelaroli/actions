package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Databases.find;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.ListAllAction;

@RequestScoped
public class DefaultListAllAction extends AbstractAction implements ListAllAction {

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultListAllAction() {
		this(null, null);
	}

	@Inject
	public DefaultListAllAction(Result result, Db db) {
		super(result, db);
	}
	
	@Override
	public <T> List<T> all(Class<T> type) {
		return db().use(find()).all(type);
	}
	
}
