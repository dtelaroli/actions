package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.view.Results;

public abstract class AbstractAction implements Activity {

	private String message;
	private Object dbObject;
	private final Result result;
	private final Db db;

	public AbstractAction(Result result, Db db) {
		this.result = result;
		this.db = db;
	}

	@SuppressWarnings("unchecked")
	public <T> T andReturn() {
		return (T) dbObject;
	}
	
	public Db db() {
		return db;
	}
	
	public Result result() {
		return result;
	}

	protected AbstractAction withDbObject(Object object) {
		this.dbObject = object;
		return this;
	}

	public <T> T redirectTo(Class<T> controller) {
		return result.redirectTo(controller);
	}
	
	public <T> T redirectTo(T controller) {
		return result.redirectTo(controller);
	}
	
	@Override
	public AbstractAction withMessage(String message) {
		this.message = message;
		result.include("message", message);
		return this;
	}

	@Override
	public String message() {
		return message;
	}

	public Serializer json() {
		return result.use(Results.json()).from(dbObject);
	}

	public Serializer jsonWithoutRoot() {
		return result.use(Results.json()).withoutRoot().from(dbObject);
	}
	
}
