package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.serialization.Serializer;

public interface DeleteAction extends Activity {

	<T> DeleteAction by(Class<T> type, Object id);

	<T> T andReturn();

	<T> T redirectTo(T controller);

	<T> T redirectTo(Class<T> controller);

	Serializer json();

	Serializer jsonWithoutRoot();
}
