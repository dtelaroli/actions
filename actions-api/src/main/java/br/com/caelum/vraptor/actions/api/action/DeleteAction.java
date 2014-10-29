package br.com.caelum.vraptor.actions.api.action;

public interface DeleteAction extends Activity {

	<T> DeleteAction by(Class<T> type, Object id);

	<T> T andReturn();

	<T> T andRedirectTo(Class<T> controller);

}
