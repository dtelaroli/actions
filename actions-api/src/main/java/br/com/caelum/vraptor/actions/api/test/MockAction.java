package br.com.caelum.vraptor.actions.api.test;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Act;
import br.com.caelum.vraptor.actions.api.Action;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockAction extends AbstractMock implements Act {

	@Override
	public <T extends Action> T as(Class<T> act) {
		return getProxifier().proxify(act, returnOnFinalMethods(act));
	}

	@Override
	public Result result() {
		return new MockSerializationResult();
	}

	@Override
	public Validator validator() {
		return new MockValidator();
	}
	
}
