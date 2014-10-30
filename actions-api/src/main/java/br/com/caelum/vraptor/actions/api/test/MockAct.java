package br.com.caelum.vraptor.actions.api.test;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Act;
import br.com.caelum.vraptor.actions.api.Action;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.PaginationAction;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockAct extends AbstractMock implements Act {
	
	private Object object;
	private MockPaginationAction mockPagination;
	
	public MockAct() {
		this(new MockResult());
	}
	
	public MockAct(Result result) {
		this(result, null, new MockValidator());
	}
	
	public MockAct(Result result, Db db, Validator validator) {
		super(result, db, validator);
		mockPagination = new MockPaginationAction(result, db, validator);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Action> T as(Class<T> act) {
		if(act.isAssignableFrom(PaginationAction.class)) {
			return (T) mockPagination.returning(object);
		}
		return getProxifier().proxify(act, returnOnFinalMethods(act));
	}

	public <T> MockAct returning(T object) {
		this.object = object;
		return this;
	}
	
}
