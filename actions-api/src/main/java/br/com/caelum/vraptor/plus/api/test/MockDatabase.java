package br.com.caelum.vraptor.plus.api.test;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.plus.api.Db;
import br.com.caelum.vraptor.plus.api.Database;

@Vetoed
public class MockDatabase extends AbstractMock implements Db {

	@Override
	public <T extends Database> T use(Class<T> db) {
		return getProxifier().proxify(db, returnOnFinalMethods(db));
	}
	
}
