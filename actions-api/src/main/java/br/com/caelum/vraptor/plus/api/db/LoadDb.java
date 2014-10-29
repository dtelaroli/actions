package br.com.caelum.vraptor.plus.api.db;

import br.com.caelum.vraptor.plus.api.Database;

public interface LoadDb extends Database {

	<T> T find(Class<T> type, long id);

}
