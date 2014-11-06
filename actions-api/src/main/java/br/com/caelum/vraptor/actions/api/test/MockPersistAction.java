package br.com.caelum.vraptor.actions.api.test;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.PersistAction;
import br.com.caelum.vraptor.actions.api.db.IModel;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockPersistAction extends AbstractMock implements PersistAction {

	private Object original;
	
	public MockPersistAction() {
		super();
	}

	public MockPersistAction(Result result, Db db, Validator validator) {
		super(result, db, validator);
	}

	@SuppressWarnings("unchecked")
	private <T> T returnObj() {
		Object object = get(original.getClass());
		if(object == null) {
			return (T) original;
		}
		return (T) object;
	}

	@Override
	public <T> PersistAction insert(T object) {
		return execute(object);
	}

	private <T> PersistAction execute(T object) {
		original = object;
		withDbObject(returnObj());
		return this;
	}

	@Override
	public <T> PersistAction update(T object) {
		return execute(object);
	}

	@Override
	public PersistAction save(IModel object) {
		return execute(object);
	}

}
