package br.com.caelum.vraptor.actions.api;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.actions.api.db.DeleteDb;
import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.api.db.PersistDb;

@Vetoed
public class Dbs {

	public static Class<PersistDb> persist() {
		return PersistDb.class;
	}

	public static Class<FindDb> find() {
		return FindDb.class;
	}

	public static Class<DeleteDb> delete() {
		return DeleteDb.class;
	}

}
