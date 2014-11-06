package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Dbs.find;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.action.PaginationAction;
import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.api.db.order.Order;
import br.com.caelum.vraptor.actions.api.db.pagination.DefaultPageConfig;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;
import br.com.caelum.vraptor.util.test.MockResult;

public class DefaultPaginationActionTest {

	private PaginationAction act;
	@Mock private Db db;
	@Mock private FindDb findDb;
	@Spy private Result result = new MockResult();
	private DefaultPageConfig config;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(findDb.with(any(Order.class))).thenReturn(findDb);
		when(findDb.paginate(MyModel.class, 10, 10)).thenReturn(new Page<MyModel>(1, 10, 100, Arrays.asList(new MyModel())));
		when(findDb.paginate(MyModel.class, 1, 10)).thenReturn(new Page<MyModel>(1, 10, 100, Arrays.asList(new MyModel())));
		when(findDb.paginate(MyModel.class, 1, 20)).thenReturn(new Page<MyModel>(1, 20, 100, Arrays.asList(new MyModel())));
		when(db.use(find())).thenReturn(findDb);
		
		config = new DefaultPageConfig();
		
		act = new DefaultPaginationAction(result, db, config);
	}

	@Test
	public void shouldReturnListOfMyModel() {
		Page<MyModel> page = act.page(10).limit(10).paginate(MyModel.class);
		assertThat(page.getList(), not(empty()));
		assertThat(page.getList().get(0), instanceOf(MyModel.class));
	}
	
	@Test
	public void shouldSetDefaultLimitValue() {
		Page<MyModel> page = act.paginate(MyModel.class);
		assertThat(page.getLimit(), equalTo(20));
	}
	
	@Test
	public void shouldSetCustomLimitValue() {
		Page<MyModel> page = act.limit(10).paginate(MyModel.class);
		assertThat(page.getLimit(), equalTo(10));
	}
	
	@Test
	public void shouldSetOrder() {
		Order asc = Order.asc("foo");
		act.with(asc).page(10).limit(10).paginate(MyModel.class);
		verify(findDb).with(asc);
	}
	
}
