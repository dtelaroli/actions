package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;

public abstract class AbstractAction implements Activity {

	private final Result result;
	private final Db db;
	private String message;

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
		return (T) dbObject();
	}

	protected Object dbObject() {
		throw new UnsupportedOperationException("Not implemented");
	}

	public <T> T andRedirectTo(Class<T> controller) {
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
	
}
