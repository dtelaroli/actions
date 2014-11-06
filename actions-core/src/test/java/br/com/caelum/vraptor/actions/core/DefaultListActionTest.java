package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.find;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.ListAction;
import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.api.db.order.Order;

public class DefaultListActionTest {

	private ListAction act;
	@Mock private Db db;
	@Mock private FindDb listDb;
	@Mock private Result result;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(listDb.with(any())).thenReturn(listDb);
		when(listDb.all(MyModel.class)).thenReturn(Arrays.asList(new MyModel()));
		when(db.use(find())).thenReturn(listDb);
		
		act = new DefaultListAction(result, db);
	}

	@Test
	public void shouldReturnListOfMyModel() {
		assertThat(act.all(MyModel.class).get(0), instanceOf(MyModel.class));
	}
	
	@Test
	public void shouldReturnListOfMyModelOrdered() {
		Order asc = Order.asc("foo");
		assertThat(act.with(asc).all(MyModel.class).get(0), instanceOf(MyModel.class));
		verify(listDb).with(asc);
	}

}
