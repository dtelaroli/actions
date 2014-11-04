package br.com.caelum.vraptor.actions.api.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Action;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.proxy.JavassistProxifier;
import br.com.caelum.vraptor.proxy.MethodInvocation;
import br.com.caelum.vraptor.proxy.Proxifier;
import br.com.caelum.vraptor.proxy.SuperMethod;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Validator;

public abstract class AbstractMock implements Action {

	private final Proxifier proxifier;
	private final Result result;
	private final Db db;
	private String message;
	private Validator validator;
	private Map<Class<?>, Object> returns = new HashMap<>();

	public AbstractMock() {
		this(new MockResult());
	}
	
	public AbstractMock(Result result) {
		this(result, null, new MockValidator());
	}
	
	public AbstractMock(Result result, Db db, Validator validator) {
		this(new JavassistProxifier(), result, db, validator);
	}

	public AbstractMock(Proxifier proxifier, Result result, Db db, Validator validator) {
		this.proxifier = proxifier;
		this.result = result;
		this.db = db;
		this.validator = validator;
	}


	protected Proxifier getProxifier() {
		return proxifier;
	}
	
	protected <T> T proxy(Class<T> type) {
		return getProxifier().proxify(type, returnOnFinalMethods(type));
	}
	
	public Result result() {
		return result;
	}
	
	public Validator validator() {
		return validator;
	}
	
	public Db db() {
		return db;
	}
	
	public Action withMessage(String message) {
		this.message = message;
		result.include("message", message);
		return this;
	}

	public String message() {
		return message;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> MethodInvocation<T> returnOnFinalMethods(final Class<T> type) {
		return new MethodInvocation<T>() {
			@Override
			public Object intercept(T proxy, Method method, Object[] args, SuperMethod superMethod) {
				Class returnType = method.getReturnType();
				if (returnType == void.class || Modifier.isFinal(returnType.getModifiers())) {
					return null;
				}

				if (type.isAssignableFrom(returnType) || type.isAssignableFrom(Result.class) || type.isAssignableFrom(Validator.class)) {
					return proxy;
				}

				if (args.length > 0 && args[0].equals(type)) {
					return proxifier.proxify((Class) args[0], returnOnFirstInvocation());
				}
				
				if(List.class.isAssignableFrom(returnType)) {
					return new ArrayList<>();
				}
				
				return proxifier.proxify(returnType, returnOnFinalMethods(type));
			}
		};
	}
	
	private <T> MethodInvocation<T> returnOnFirstInvocation() {
		return new MethodInvocation<T>() {
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, SuperMethod superMethod) {
				return null;
			}
		};
	}

	public Map<Class<?>, Object> getReturnMap() {
		return returns;
	}
	
	public Object get(Class<?> type) {
		return returns.get(type);
	}
	
	public AbstractMock putReturn(Class<?> key, Object value) {
		returns.put(key, value);
		return this;
	}
}
