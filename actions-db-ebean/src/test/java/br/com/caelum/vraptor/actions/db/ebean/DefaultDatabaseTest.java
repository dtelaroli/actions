package br.com.caelum.vraptor.actions.db.ebean;

import static br.com.caelum.vraptor.actions.api.Databases.find;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.db.ebean.DefaultDataBase;
import br.com.caelum.vraptor.actions.db.ebean.DefaultFindDb;
import br.com.caelum.vraptor.ioc.Container;

public class DefaultDatabaseTest {

	private Db dbs;
	@Mock private Container container;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(container.instanceFor(find())).thenReturn(new DefaultFindDb());
		
		dbs = new DefaultDataBase(container);
	}

	@Test
	public <T> void shouldReturnListAllActionInstance() {
		assertThat(dbs.use(find()), instanceOf(FindDb.class));
	}

}
