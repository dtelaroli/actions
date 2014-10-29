package br.com.caelum.vraptor.actions.api.db;

import br.com.caelum.vraptor.actions.api.Database;

public interface DeleteDb extends Database {

	<T> Integer by(Class<T> type, Object id);
	
}
