package br.com.caelum.vraptor.actions.core.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.service.EmailAction;
import br.com.caelum.vraptor.actions.core.template.VelocityBuilder;
import br.com.caelum.vraptor.actions.core.test.MockBeanValidator;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validator;

public class DefaultEmailActionTest {

	private EmailAction act;
	private Result result;
	private VelocityBuilder velocity;
	@Mock private Environment env;
	private Validator validator;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		validator = new MockBeanValidator();
		
		act = new DefaultEmailAction(result, validator, env, velocity);
	}
	
	@Test
	public void shouldSetDefaultTemplateName() {
		assertThat(act.getTemplate(), equalTo("templates/email.vm"));
	}
	
	@Test
	public void shouldSetTemplateName() {
		assertThat(act.render("new").getTemplate(), equalTo("new"));
	}
	
	@Test
	public void shouldPutParam() {
		Map<String, Object> map = act.param("foo", "bar").getParams();
		assertThat(map.size(), equalTo(1));
	}
	
	@Test
	public void shouldSetTo() {
		assertThat(act.to("email@email.com").getTo(), equalTo("email@email.com"));
	}
	
	@Test
	public void shouldSetSubject() {
		assertThat(act.subject("subject").getSubject(), equalTo("subject"));
	}
	
	@Test(expected = ValidationException.class)
	public void shouldBeInvalidIfEmailInvalid() {
		act.send();
	}

}
