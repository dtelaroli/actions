package br.com.caelum.vraptor.actions.api.db;

import java.util.List;

import br.com.caelum.vraptor.actions.api.Database;
import br.com.caelum.vraptor.actions.api.db.order.Order;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;

public interface FindDb extends Database {

	<T> List<T> all(Class<T> type);

	<T> Page<T> paginate(Class<T> type, int page, int limit);

	<T> T by(Class<T> type, Object id);

	FindDb with(Order... order);

}
