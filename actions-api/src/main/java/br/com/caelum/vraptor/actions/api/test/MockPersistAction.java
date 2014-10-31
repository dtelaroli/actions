package br.com.caelum.vraptor.actions.api.test;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.PersistAction;
import br.com.caelum.vraptor.actions.api.db.IModel;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockPersistAction extends AbstractMock implements PersistAction {

	private Object obj;
	private Object original;
	
	public MockPersistAction() {
		super();
	}

	public MockPersistAction(Result result, Db db, Validator validator) {
		super(result, db, validator);
	}

	@Override
	public MockPersistAction returning(Object obj) {
		this.obj = obj;
		return this;
	}

	@SuppressWarnings("unchecked")
	private <T> T returnObj() {
		if(obj == null) {
			return (T) original;
		}
		return (T) obj;
	}

	@Override
	public <T> PersistAction insert(T object) {
		this.original = object;
		return this;
	}

	@Override
	public <T> PersistAction update(T object) {
		original = object;
		return this;
	}

	@Override
	public PersistAction save(IModel object) {
		original = object;
		return this;
	}

	@Override
	public <T> T andReturn() {
		return returnObj();
	}

	@Override
	public <T> T andRedirectTo(Class<T> controller) {
		return result().redirectTo(controller);
	}

	@Override
	public <T> T redirectTo(T controller) {
		return result().redirectTo(controller);
	}
	
}
