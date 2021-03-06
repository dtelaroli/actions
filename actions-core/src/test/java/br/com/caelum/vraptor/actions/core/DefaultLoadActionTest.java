package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.find;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import models.MyModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.LoadAction;
import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.core.DefaultLoadAction;

public class DefaultLoadActionTest {

	private LoadAction act;
	@Mock private Db db;
	@Mock private FindDb findDb;
	@Mock private Result result;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(findDb.by(MyModel.class, 1L)).thenReturn(new MyModel());
		when(db.use(find())).thenReturn(findDb);
		
		act = new DefaultLoadAction(result, db);
	}

	@Test
	public void shouldReturnListOfMyModel() {
		assertThat(act.by(MyModel.class, 1L), instanceOf(MyModel.class));
	}

}
