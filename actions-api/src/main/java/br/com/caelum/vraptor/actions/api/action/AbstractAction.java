package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.view.Results;

public abstract class AbstractAction implements Activity {

	private final Result result;
	private final Db db;
	private String message;
	private Object dbObject;

	public AbstractAction(Result result, Db db) {
		this.result = result;
		this.db = db;
	}

	@Override
	public Db db() {
		return db;
	}

	@Override
	public Result result() {
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> T andReturn() {
		return (T) dbObject;
	}

	protected AbstractAction withDbObject(Object object) {
		this.dbObject = object;
		return this;
	}

	public <T> T redirectTo(Class<T> controller) {
		return result().redirectTo(controller);
	}
	
	public <T> T redirectTo(T controller) {
		return result().redirectTo(controller);
	}
	
	@Override
	public AbstractAction withMessage(String message) {
		this.message = message;
		result().include("message", message);
		return this;
	}

	@Override
	public String message() {
		return message;
	}

	@Override
	public AbstractAction include(String key, Object object) {
		result.include(key, object);
		return this;
	}
	
	public Serializer json() {
		return result().use(Results.json()).from(dbObject);
	}

	public Serializer jsonWithoutRoot() {
		return result().use(Results.json()).withoutRoot().from(dbObject);
	}
}
