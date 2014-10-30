package br.com.caelum.vraptor.actions.api.db;

import static br.com.caelum.vraptor.actions.api.Dbs.find;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;
import br.com.caelum.vraptor.ioc.Container;

public class DefaultDatabaseTest {

	private Db dbs;
	@Mock private Container container;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(container.instanceFor(find())).thenReturn(new FindDb() {
			@Override
			public <T> Page<T> paginate(Class<T> type, int page, int limit) {
				return null;
			}
			@Override
			public <T> T by(Class<T> type, Object id) {
				return null;
			}
			
			@Override
			public <T> List<T> all(Class<T> type) {
				return null;
			}
		});
		
		dbs = new DefaultDataBase(container);
	}

	@Test
	public <T> void shouldReturnListAllActionInstance() {
		assertThat(dbs.use(find()), instanceOf(FindDb.class));
	}

}
