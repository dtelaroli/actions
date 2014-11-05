package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.delete;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.AbstractAction;
import br.com.caelum.vraptor.actions.api.action.DeleteAction;

public class DefaultDeleteAction extends AbstractAction implements DeleteAction {

	private static final String SUCCESS_MESSAGE = "success.delete";

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultDeleteAction() {
		this(null, null);
	}

	@Inject
	public DefaultDeleteAction(Result result, Db db) {
		super(result, db);
	}

	@Override
	public <T> DeleteAction by(Class<T> type, Object id) {
		execute(type, id);
		return this;
	}

	private <T> void execute(Class<T> type, Object id) {
		Integer dbObject = db().use(delete()).by(type, id);
		withDbObject(dbObject);
		if(message() == null) {
			withMessage(SUCCESS_MESSAGE);
		}
	}

}
