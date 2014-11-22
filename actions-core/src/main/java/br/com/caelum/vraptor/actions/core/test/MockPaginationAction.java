package br.com.caelum.vraptor.actions.core.test;

import java.util.Collections;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.PaginationAction;
import br.com.caelum.vraptor.actions.api.db.order.Order;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockPaginationAction extends AbstractMock implements PaginationAction {

	private int page = 1;
	private int limit = 20;
	
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
		Object object = get(type);
		if(object == null) {
			returning = new Page<T>(Collections.emptyList());
		}
		else if(object instanceof Page){
			returning = (Page<T>) object;
		}
		else {
			returning = new Page<T>(object);
		}
		
		returning.setNumber(page);
		returning.setLimit(limit);
		
		return returning;
	}

	@Override
	public PaginationAction with(Order... asc) {
		return this;
	}

}
