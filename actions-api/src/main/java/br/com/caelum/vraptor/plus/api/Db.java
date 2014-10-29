package br.com.caelum.vraptor.plus.api;

public interface Db {

	<T extends Database> T use(Class<T> db);
	
}
