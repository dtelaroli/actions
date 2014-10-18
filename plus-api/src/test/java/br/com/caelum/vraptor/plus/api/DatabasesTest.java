package br.com.caelum.vraptor.plus.api;

import static br.com.caelum.vraptor.plus.api.Databases.listAll;
import static br.com.caelum.vraptor.plus.api.Databases.load;
import static br.com.caelum.vraptor.plus.api.Databases.paginate;
import static br.com.caelum.vraptor.plus.api.Databases.remove;
import static br.com.caelum.vraptor.plus.api.Databases.save;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.caelum.vraptor.plus.api.db.ListAllDb;
import br.com.caelum.vraptor.plus.api.db.LoadDb;
import br.com.caelum.vraptor.plus.api.db.PaginateDb;
import br.com.caelum.vraptor.plus.api.db.RemoveDb;
import br.com.caelum.vraptor.plus.api.db.SaveDb;

public class DatabasesTest {

	@Test
	public void shouldReturnListAllDb() {
		assertThat(listAll(), typeCompatibleWith(ListAllDb.class));
	}
	
	@Test
	public void shouldReturnViewDb() {
		assertThat(load(), typeCompatibleWith(LoadDb.class));
	}
	
	@Test
	public void shouldReturnSaveDb() {
		assertThat(save(), typeCompatibleWith(SaveDb.class));
	}
	
	@Test
	public void shouldReturnPaginateDb() {
		assertThat(paginate(), typeCompatibleWith(PaginateDb.class));
	}
	
	@Test
	public void shouldReturnRemoveDb() {
		assertThat(remove(), typeCompatibleWith(RemoveDb.class));
	}

}
