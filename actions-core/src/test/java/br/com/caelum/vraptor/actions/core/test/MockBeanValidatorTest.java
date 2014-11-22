package br.com.caelum.vraptor.actions.core.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;

public class MockBeanValidatorTest {

	private MockBeanValidator validator;
	
	@Before
	public void setUp() throws Exception {
		validator = new MockBeanValidator();
	}

	class Foo {
		@NotNull String bar;
	}
	
	@Test
	public void shouldValidateWithBeansValidator() {
		Foo foo = new Foo();
		
		validator.validate(foo);
		assertThat(validator.hasErrors(), equalTo(true));
	}
	
	@Test
	public void shouldBeValid() {
		Foo foo = new Foo();
		foo.bar = "text";
		
		validator.validate(foo);
		assertThat(validator.hasErrors(), equalTo(false));
	}

}
