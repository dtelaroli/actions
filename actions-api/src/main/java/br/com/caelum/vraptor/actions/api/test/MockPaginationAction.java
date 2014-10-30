package br.com.caelum.vraptor.actions.api.test;

import java.util.Collections;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.PaginationAction;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockPaginationAction extends AbstractMock implements PaginationAction {

	private int page = 1;
	private int limit = 20;
	private Object obj;
	
	public MockPaginationAction() {
		super();
	}

	public MockPaginationAction(Result result, Db db, Validator validator) {
		super(result, db, validator);
	}

	@Override
	public PaginationAction page(int page) {
		this.page = page;
		return this;
	}

	@Override
	public PaginationAction limit(int limit) {
		this.limit = limit;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Page<T> paginate(Class<T> type) {
		Page<T> returning = null;
		if(obj == null) {
			returning = new Page<T>(Collections.emptyList());
		}
		else if(obj instanceof Page){
			returning = (Page<T>) obj;
		}
		else {
			returning = new Page<T>(obj);
		}
		
		returning.setNumber(page);
		returning.setLimit(limit);
		
		return returning;
	}

	@Override
	public MockPaginationAction returning(Object obj) {
		this.obj = obj;
		return this;
	}
	
}
