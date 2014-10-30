package br.com.caelum.vraptor.actions.api.db.pagination;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Vetoed;

@Vetoed
public class Page<T> {

	private int number = 1;
	private int limit = 20;
	private List<T> list;
	private int total;

	public Page() {
	}
	
	@SuppressWarnings("unchecked")
	public Page(Object object) {
		if(object instanceof List) {
			this.list = (List<T>) object;
		}
		else {
			this.list = (List<T>) Arrays.asList(object);
		}
		if(list != null && !list.isEmpty()) {
			total = list.size();
		}
	}
	
	public Page(int pageNumber, int limit, List<T> list) {
		this.list = list;
		this.number = pageNumber;
		this.limit = limit;
	}
	
	public Page(int pageNumber, int limit, int total, List<T> list) {
		this(pageNumber, limit, list);
		this.total = total;
	}

	public int getNumber() {
		return number;
	}

	public int getLimit() {
		return limit;
	}

	public List<T> getList() {
		return list;
	}
	
	public void setNumber(int pageNumber) {
		this.number = pageNumber;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getFirst() {
		return 1;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean hasPrev() {
		return number > 1;
	}

	public boolean hasNext() {
		return number * limit < total;
	}

	public int getNext() {
		if(!hasNext()) {
			return number;
		}
		return number + 1;
	}

	public int getPrev() {
		if(!hasPrev()) {
			return number;
		}
		return number - 1;
	}

	public int getPageSize() {
		return list.size();
	}

	public int getLast() {
		int last = (int) Math.ceil((double)total / limit);
		return last == 0 ? 1 : last;
	}
	
}
