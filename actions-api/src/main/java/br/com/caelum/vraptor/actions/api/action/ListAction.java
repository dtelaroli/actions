package br.com.caelum.vraptor.actions.api.action;

import java.util.List;

public interface ListAction extends Activity {

	<T> List<T> all(Class<T> type);
	
}
