package br.com.caelum.vraptor.plus.db.ebean;

import java.util.List;

import org.jboss.weld.exceptions.UnsupportedOperationException;

import br.com.caelum.vraptor.plus.api.db.FindDb;
import br.com.caelum.vraptor.plus.api.db.pagination.Page;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;

public class DefaultFindDb implements FindDb {

	public DefaultFindDb() {
	}
	
	@Override
	public <T> List<T> all(Class<T> type) {
		return Ebean.find(type).findList();
	}
	
	@Override
	public <T> Page<T> paginate(Class<T> type, int page, int limit) {
		PagedList<T> pagedList = Ebean.find(type).findPagedList(page - 1, limit);
		
		pageNotFound(page, limit, pagedList);
		
		return new Page<T>(page, limit, pagedList.getTotalRowCount(), pagedList.getList());
	}

	private <T> void pageNotFound(int page, int limit, PagedList<T> pagedList) {
		if(page > 1 && page * limit > pagedList.getTotalRowCount()) {
			throw new UnsupportedOperationException("Page not found");
		}
	}

	@Override
	public <T> T by(Class<T> type, Object id) {
		return Ebean.find(type, id);
	}

}
