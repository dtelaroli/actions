package br.com.caelum.vraptor.plus.api.db;

import java.util.List;

import br.com.caelum.vraptor.plus.api.Database;
import br.com.caelum.vraptor.plus.api.db.pagination.Page;

public interface FindDb extends Database {

	<T> List<T> all(Class<T> type);

	<T> Page<T> paginate(Class<T> type, int page, int limit);

	<T> T by(Class<T> type, Object id);

}
