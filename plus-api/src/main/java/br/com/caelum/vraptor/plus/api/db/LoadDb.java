package br.com.caelum.vraptor.plus.api.db;

import br.com.caelum.vraptor.plus.api.Db;

public interface LoadDb extends Db {

	<T> T get(Class<T> type, long id);

}
