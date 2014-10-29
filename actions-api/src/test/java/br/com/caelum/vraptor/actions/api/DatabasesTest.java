package br.com.caelum.vraptor.actions.api;

import static br.com.caelum.vraptor.actions.api.Databases.delete;
import static br.com.caelum.vraptor.actions.api.Databases.find;
import static br.com.caelum.vraptor.actions.api.Databases.persist;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.caelum.vraptor.actions.api.db.DeleteDb;
import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.api.db.PersistDb;

public class DatabasesTest {

	@Test
	public void shouldReturnListAllDb() {
		assertThat(find(), typeCompatibleWith(FindDb.class));
	}
	
	@Test
	public void shouldReturnSaveDb() {
		assertThat(persist(), typeCompatibleWith(PersistDb.class));
	}

	@Test
	public void shouldReturnRemoveDb() {
		assertThat(delete(), typeCompatibleWith(DeleteDb.class));
	}

}
