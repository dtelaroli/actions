package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Acts.delete;
import static br.com.caelum.vraptor.actions.api.Acts.list;
import static br.com.caelum.vraptor.actions.api.Acts.load;
import static br.com.caelum.vraptor.actions.api.Acts.persist;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Act;
import br.com.caelum.vraptor.actions.api.Action;
import br.com.caelum.vraptor.actions.api.action.DeleteAction;
import br.com.caelum.vraptor.actions.api.action.PersistAction;
import br.com.caelum.vraptor.actions.api.db.IModel;
import br.com.caelum.vraptor.ioc.Container;
import br.com.caelum.vraptor.validator.Validator;

public class DefaultAct implements Act {

	private final Container container;
	private final Result result;
	private final Validator validator;

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultAct() {
		this(null);
	}
	
	@Inject
	public DefaultAct(Container container) {
		this.container = container;
		result = container.instanceFor(Result.class);
		validator = container.instanceFor(Validator.class);
	}

	@Override
	public <T extends Action> T as(Class<T> act) {
		return container.instanceFor(act);
	}

	@Override
	public Result result() {
		return result;
	}

	@Override
	public Validator validator() {
		return validator;
	}

	@Override
	public <T> List<T> listAll(Class<T> type) {
		return as(list()).all(type);
	}

	@Override
	public <T> T loadBy(Class<T> type, Object id) {
		return as(load()).by(type, id);
	}

	@Override
	public <T> DeleteAction deleteBy(Class<T> type, Object id) {
		return as(delete()).by(type, id);
	}

	@Override
	public <T> PersistAction save(IModel object) {
		return as(persist()).save(object);
	}

	@Override
	public <T> PersistAction insert(T object) {
		return as(persist()).insert(object);
	}

	@Override
	public <T> PersistAction update(T object) {
		return as(persist()).update(object);
	}

}
