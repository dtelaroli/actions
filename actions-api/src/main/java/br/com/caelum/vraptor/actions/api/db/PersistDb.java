package br.com.caelum.vraptor.actions.api.db;

import br.com.caelum.vraptor.actions.api.Database;

public interface PersistDb extends Database {

	<T> T save(IModel object);

	<T> T insert(T object);

	<T> T update(T object);

}
