package br.com.caelum.vraptor.actions.core.test;

import java.util.Set;

import javax.enterprise.inject.Vetoed;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Validator;

@Vetoed
public class MockBeanValidator extends MockValidator {
	
	@Override
	public Validator validate(Object object, Class<?>... groups) {
		Set<ConstraintViolation<Object>> errors = getValidator().validate(object, groups);
		addAll(errors);
		return this;
	}

	private javax.validation.Validator getValidator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
	
}