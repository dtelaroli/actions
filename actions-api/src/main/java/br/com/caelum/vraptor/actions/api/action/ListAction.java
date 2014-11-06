package br.com.caelum.vraptor.actions.api.action;

import java.util.List;

import br.com.caelum.vraptor.actions.api.db.order.Order;

public interface ListAction extends Activity {

	<T> List<T> all(Class<T> type);

	ListAction with(Order... asc);

}
