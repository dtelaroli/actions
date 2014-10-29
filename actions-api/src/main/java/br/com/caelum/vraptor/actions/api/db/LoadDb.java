package br.com.caelum.vraptor.actions.api.db;

import br.com.caelum.vraptor.actions.api.Database;

public interface LoadDb extends Database {

	<T> T find(Class<T> type, long id);

}
