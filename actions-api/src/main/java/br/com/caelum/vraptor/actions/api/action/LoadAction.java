package br.com.caelum.vraptor.actions.api.action;

public interface LoadAction extends Activity {

	<T> T by(Class<T> type, Object id);
	
}
