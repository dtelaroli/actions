package br.com.caelum.vraptor.actions.db.ebean;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.api.db.order.Order;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.Query;
import com.google.common.base.Joiner;

public class DefaultFindDb implements FindDb {

	private Order[] orders;

	public DefaultFindDb() {
	}
	
	@Override
	public <T> List<T> all(Class<T> type) {
		Query<T> find = parseQuery(type);
		return find.findList();
	}

	private <T> Query<T> parseQuery(Class<T> type) {
		Query<T> find = Ebean.find(type);
		
		List<String> orderList = new ArrayList<>();
		if(orders != null) {
			for (Order order : orders) {
				orderList.add(order.toString());
			}
			
			find.order(Joiner.on(", ").join(orderList));
		}
		return find;
	}
	
	@Override
	public <T> Page<T> paginate(Class<T> type, int page, int limit) {
		PagedList<T> pagedList = parseQuery(type).findPagedList(page - 1, limit);
		return new Page<T>(page, limit, pagedList.getTotalRowCount(), pagedList.getList());
	}

	@Override
	public <T> T by(Class<T> type, Object id) {
		return Ebean.find(type, id);
	}

	@Override
	public FindDb with(Order... order) {
		this.orders = order;
		return this;
	}

}
