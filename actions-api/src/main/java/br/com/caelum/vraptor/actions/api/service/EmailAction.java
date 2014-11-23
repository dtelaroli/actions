package br.com.caelum.vraptor.actions.api.service;

import java.util.Map;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.action.Activity;

public interface EmailAction extends Activity {

	Result send() throws Exception;
	
	EmailAction render(String template);
	
	EmailAction to(String to);

	EmailAction subject(String subject);

	EmailAction param(String string, Object object);

	String getTemplate();

	Map<String, Object> getParams();

	String getTo();

	String getSubject();

}
