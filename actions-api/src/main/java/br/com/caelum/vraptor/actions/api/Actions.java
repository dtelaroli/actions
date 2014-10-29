package br.com.caelum.vraptor.actions.api;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.actions.api.action.DeleteAction;
import br.com.caelum.vraptor.actions.api.action.ListAllAction;
import br.com.caelum.vraptor.actions.api.action.LoadAction;
import br.com.caelum.vraptor.actions.api.action.PaginationAction;
import br.com.caelum.vraptor.actions.api.action.PersistAction;

@Vetoed
public class Actions {

	public static Class<ListAllAction> list() {
		return ListAllAction.class;
	}

	public static Class<LoadAction> load() {
		return LoadAction.class;
	}
	
	public static Class<PersistAction> persist() {
		return PersistAction.class;
	}

	public static Class<PaginationAction> pagination() {
		return PaginationAction.class;
	}

	public static Class<DeleteAction> delete() {
		return DeleteAction.class;
	}

}
