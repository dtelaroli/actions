package br.com.caelum.vraptor.plus.api.db;

import br.com.caelum.vraptor.plus.api.Database;

public interface DeleteDb extends Database {

	<T> Integer by(Class<T> type, Object id);
	
}
