package br.com.caelum.vraptor.actions.api.action;

public interface DeleteAction extends Activity {

	<T> DeleteAction by(Class<T> type, Object id);

	<T> T andReturn();

	<T> T redirectTo(T controller);

	<T> T redirectTo(Class<T> controller);
}
