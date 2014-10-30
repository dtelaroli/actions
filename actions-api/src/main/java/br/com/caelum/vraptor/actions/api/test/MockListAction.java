package br.com.caelum.vraptor.actions.api.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.ListAction;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockListAction extends AbstractMock implements ListAction {

	private Object obj;
	
	public MockListAction() {
		super();
	}

	public MockListAction(Result result, Db db, Validator validator) {
		super(result, db, validator);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> all(Class<T> type) {
		if(obj == null) {
			return Collections.emptyList();
		}
		else if(obj instanceof List) {
			return (List<T>) obj;
		}
		return (List<T>) Arrays.asList(obj);
	}

	@Override
	public MockListAction returning(Object obj) {
		this.obj = obj;
		return this;
	}
	
}
