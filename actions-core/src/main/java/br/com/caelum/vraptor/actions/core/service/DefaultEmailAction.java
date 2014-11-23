package br.com.caelum.vraptor.actions.core.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
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
	
	@NotNull @org.hibernate.validator.constraints.Email
	private String to;
	
	@NotBlank @org.hibernate.validator.constraints.Email
	private String from;
	
	@NotBlank @org.hibernate.validator.constraints.Email
	private String replyTo;
	
	@NotBlank
	private String subject;
	
	@NotBlank
	private String host;

	@NotNull
	private Integer port;

	@NotNull
	private Boolean tsl;

	private String user;

	private String password;

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
	}
	
	@PostConstruct
	public void setUp() {
		template = env.get("email.template", "templates/email.vm");
		host = env.get("email.host", "localhost");
		port = Integer.valueOf(env.get("email.port", "25"));
		from = env.get("email.from");
		replyTo = env.get("email.replyTo", from);
		tsl = Boolean.valueOf(env.get("email.tsl", "false"));
		user = env.get("email.user", null);
		password = env.get("email.password", null);
	}

	@Override
	public Result send() throws Exception {
		validator.validate(this);
		
		delegate().send();
		
		return result();
	}

	public Email delegate() throws EmailException {
		HtmlEmail email = new HtmlEmail();
		
		email.addTo(getTo())
			.addReplyTo(replyTo)
			.setFrom(from)
			.setSubject(getSubject())
			.setMsg(parseTemplate())
			.setStartTLSEnabled(tsl);
			
		email.setSmtpPort(port);
		email.setHostName(host);
		email.setAuthentication(user, password);
		
		return email;
	}

	private String parseTemplate() {
		return velocity.generate(getParams(), getTemplate());
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
