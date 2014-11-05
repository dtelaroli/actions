package br.com.caelum.vraptor.actions.api;

import java.util.List;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.action.DeleteAction;
import br.com.caelum.vraptor.actions.api.action.PersistAction;
import br.com.caelum.vraptor.actions.api.db.IModel;
import br.com.caelum.vraptor.validator.Validator;

public interface Act {

	<T extends Action> T as(Class<T> act);
	
	Result result();
	
	Validator validator();

	<T> List<T> listAll(Class<T> type);
	
	<T> T loadBy(Class<T> type, Object id);
	
	<T> DeleteAction deleteBy(Class<T> type, Object id);
	
	<T> PersistAction save(IModel object);
	
	<T> PersistAction insert(T object);
	
	<T> PersistAction update(T object);
	
}
