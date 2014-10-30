package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.persist;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.AbstractAction;
import br.com.caelum.vraptor.actions.api.action.PersistAction;
import br.com.caelum.vraptor.actions.api.db.IModel;

public class DefaultPersistAction extends AbstractAction implements PersistAction {

	private Object objectDb;

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultPersistAction() {
		this(null, null);
	}

	@Inject
	public DefaultPersistAction(Result result, Db db) {
		super(result, db);
	}

	@Override
	public PersistAction save(IModel object) {
		objectDb = db().use(persist()).save(object);
		setMessage("success.save");
		return this;
	}

	private void setMessage(String message) {
		if(message() == null) {
			withMessage(message);
		}
	}

	@Override
	public <T> PersistAction insert(T object) {
		objectDb = db().use(persist()).insert(object);
		setMessage("success.insert");
		return this;
	}
	
	@Override
	public <T> PersistAction update(T object) {
		objectDb = db().use(persist()).update(object);
		setMessage("success.update");
		return this;
	}

	@Override
	protected Object dbObject() {
		return objectDb;
	}

}
