package br.com.caelum.vraptor.actions.api.test;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.LoadAction;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockLoadAction extends AbstractMock implements LoadAction {

	public MockLoadAction() {
		super();
	}

	public MockLoadAction(Result result, Db db, Validator validator) {
		super(result, db, validator);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T by(Class<T> type, Object id) {
		Object object = get(type);
		if(object == null) {
			return proxy(type);
		}
		return (T) object;
	}
	
}
