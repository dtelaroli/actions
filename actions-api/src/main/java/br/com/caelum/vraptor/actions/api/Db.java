package br.com.caelum.vraptor.actions.api;

public interface Db {

	<T extends Database> T use(Class<T> db);
	
}
