package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.persist;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.AbstractAction;
import br.com.caelum.vraptor.actions.api.action.PersistAction;
import br.com.caelum.vraptor.actions.api.db.IModel;

public class DefaultPersistAction extends AbstractAction implements PersistAction {

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
		Object dbObject = db().use(persist()).save(object);
		withDbObject(dbObject);
		
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
		T dbObject = db().use(persist()).insert(object);
		withDbObject(dbObject);
		
		setMessage("success.insert");
		return this;
	}
	
	@Override
	public <T> PersistAction update(T object) {
		T dbObject = db().use(persist()).update(object);
		withDbObject(dbObject);
		
		setMessage("success.update");
		return this;
	}

}
