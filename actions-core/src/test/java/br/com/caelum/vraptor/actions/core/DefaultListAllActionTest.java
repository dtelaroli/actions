package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Databases.find;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.ListAllAction;
import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.core.DefaultListAllAction;

public class DefaultListAllActionTest {

	private ListAllAction act;
	@Mock private Db db;
	@Mock private FindDb listDb;
	@Mock private Result result;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(listDb.all(MyModel.class)).thenReturn(Arrays.asList(new MyModel()));
		when(db.use(find())).thenReturn(listDb);
		
		act = new DefaultListAllAction(result, db);
	}

	@Test
	public void shouldReturnListOfMyModel() {
		assertThat(act.all(MyModel.class).get(0), instanceOf(MyModel.class));
	}

}
