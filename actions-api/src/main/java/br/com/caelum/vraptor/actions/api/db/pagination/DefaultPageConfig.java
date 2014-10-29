package br.com.caelum.vraptor.actions.api.db.pagination;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultPageConfig {
	
	public int getLimit() {
		return 20;
	}

}
