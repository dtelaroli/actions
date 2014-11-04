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

	public MockListAction() {
		super();
	}

	public MockListAction(Result result, Db db, Validator validator) {
		super(result, db, validator);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> all(Class<T> type) {
		Object object = get(type);
		if(getReturnMap().isEmpty()) {
			return Collections.emptyList();
		}
		else if(object instanceof List) {
			return (List<T>) object;
		}
		return (List<T>) Arrays.asList(object);
	}

}
