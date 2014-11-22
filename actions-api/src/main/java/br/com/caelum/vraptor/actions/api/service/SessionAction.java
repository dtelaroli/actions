package br.com.caelum.vraptor.actions.api.service;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.action.Activity;

public interface SessionAction extends Activity {

	Result login(String username, String password, boolean remember);

	Result logout();

}
