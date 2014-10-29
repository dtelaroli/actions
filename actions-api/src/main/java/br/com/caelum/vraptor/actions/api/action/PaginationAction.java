package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.actions.api.db.pagination.Page;

public interface PaginationAction extends Activity {

	PaginationAction page(int page);

	PaginationAction limit(int limit);

	<T> Page<T> paginate(Class<T> type);
	
}
