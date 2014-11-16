package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.Result;

public interface SessionAction extends Activity {

	Result login(String username, String password, boolean remember);

	Result logout();

}
