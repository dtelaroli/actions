package br.com.caelum.vraptor.actions.core.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.action.AbstractAction;
import br.com.caelum.vraptor.actions.api.service.EmailAction;
import br.com.caelum.vraptor.actions.core.template.VelocityBuilder;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.Validator;

public class DefaultEmailAction extends AbstractAction implements EmailAction {

	private final Validator validator;

	private final VelocityBuilder velocity;
	
	private final HashMap<String, Object> params;
	
	private final Environment env;
	
	private String template;
	
	@NotNull @Email
	private String to;
	
	@NotBlank
	private String subject;

	/**
	 * @deprecated CDI eyes-only
	 */
	protected DefaultEmailAction() {
		this(null, null, null, null);
	}
	
	@Inject
	public DefaultEmailAction(Result result, Validator validator, Environment env, VelocityBuilder velocity) {
		super(result, null);
		this.validator = validator;
		this.env = env;
		this.velocity = velocity;
		params = new HashMap<>();
		template = "templates/email.vm";
	}

	@Override
	public Result send() {
		validator.validate(this).onErrorSendBadRequest();
		return result();
	}

	@Override
	public EmailAction render(String template) {
		this.template = template;
		return this;
	}

	@Override
	public EmailAction to(String to) {
		this.to = to;
		return this;
	}

	@Override
	public EmailAction subject(String subject) {
		this.subject = subject;
		return this;
	}

	@Override
	public EmailAction param(String key, Object value) {
		params.put(key, value);
		return this;
	}

	@Override
	public String getTemplate() {
		return template;
	}

	@Override
	public Map<String, Object> getParams() {
		return params;
	}

	@Override
	public String getTo() {
		return to;
	}

	@Override
	public String getSubject() {
		return subject;
	}

}
