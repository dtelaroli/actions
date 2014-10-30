package br.com.caelum.vraptor.actions.api.test;

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
	private Page<?> pageObject;
	
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
		if(pageObject == null) {
			pageObject = (Page<?>) getProxifier().proxify(type, returnOnFinalMethods(type));
		}
		
		pageObject.setNumber(page);
		pageObject.setLimit(limit);
		
		return (Page<T>) pageObject;
	}
	
	@SuppressWarnings("unchecked")
	public <T> PaginationAction returning(Object object) {
		if(object instanceof Page){
			this.pageObject = (Page<T>) object;
		}
		else {
			this.pageObject = new Page<T>(object);
		}
		return this;
	}
	
}
