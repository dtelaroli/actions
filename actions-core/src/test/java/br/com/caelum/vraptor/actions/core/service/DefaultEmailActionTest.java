package br.com.caelum.vraptor.actions.core.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.apache.commons.mail.Email;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.core.template.VelocityBuilder;
import br.com.caelum.vraptor.actions.core.test.MockBeanValidator;
import br.com.caelum.vraptor.environment.DefaultEnvironment;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.environment.EnvironmentType;
import br.com.caelum.vraptor.validator.Validator;

public class DefaultEmailActionTest {

	private DefaultEmailAction act;
	private Result result;
	private VelocityBuilder velocity;
	@Mock private Environment env;
	private Validator validator;
	@Mock private Email email;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		validator = new MockBeanValidator();
		env = new DefaultEnvironment(EnvironmentType.TEST);
		velocity = new VelocityBuilder();
		velocity.setUp();
		
		act = spy(
			(DefaultEmailAction) new DefaultEmailAction(result, validator, env, velocity)
				.to("email@email.com").subject("subject")
			);
		
		act.setUp();
		
		when(act.delegate()).thenReturn(email);
		when(email.send()).thenReturn("");
	}
	
	@After
	public void tearDown() {
		act = null;
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
	
	@Test
	public void shouldBeInvalidIfEmailInvalid() throws Exception {
		act.to(null).send();
		assertThat(validator.hasErrors(), equalTo(true));
	}
	
	@Test
	public void shouldBeValid() throws Exception {
		act.send();
		assertThat(validator.hasErrors(), equalTo(false));		
	}

}
