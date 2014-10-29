package br.com.caelum.vraptor.actions.db.ebean;

import br.com.caelum.vraptor.actions.api.db.IModel;
import br.com.caelum.vraptor.actions.api.db.PersistDb;

import com.avaje.ebean.Ebean;

public class DefaultPersistDb implements PersistDb {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T save(IModel object) {
		if(object.getId() == null) {
			return (T) insert(object);
		}
		return (T) update(object);
	}
	
	@Override
	public <T> T insert(T object) {
		Ebean.insert(object);
		return object;
	}
	
	@Override
	public <T> T update(T object) {
		Ebean.update(object);
		return object;
	}

}
