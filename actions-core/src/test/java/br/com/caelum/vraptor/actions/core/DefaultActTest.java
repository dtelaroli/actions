package br.com.caelum.vraptor.actions.core;

import static br.com.caelum.vraptor.actions.api.Acts.delete;
import static br.com.caelum.vraptor.actions.api.Acts.list;
import static br.com.caelum.vraptor.actions.api.Acts.load;
import static br.com.caelum.vraptor.actions.api.Acts.pagination;
import static br.com.caelum.vraptor.actions.api.Acts.persist;
import static br.com.caelum.vraptor.view.Results.referer;
import static br.com.caelum.vraptor.view.Results.status;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import models.MyModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Act;
import br.com.caelum.vraptor.actions.api.Action;
import br.com.caelum.vraptor.actions.api.action.ListAction;
import br.com.caelum.vraptor.actions.api.db.order.Order;
import br.com.caelum.vraptor.actions.core.test.MockDeleteAction;
import br.com.caelum.vraptor.actions.core.test.MockListAction;
import br.com.caelum.vraptor.actions.core.test.MockLoadAction;
import br.com.caelum.vraptor.actions.core.test.MockPaginationAction;
import br.com.caelum.vraptor.actions.core.test.MockPersistAction;
import br.com.caelum.vraptor.ioc.Container;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Status;

public class DefaultActTest {

	private Act act;
	@Mock private Container container;
	@Mock private Status status;
	private MockValidator validator;
	private MockResult result;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(container.instanceFor(list())).thenReturn(new ListAction() {
			@Override public Action withMessage(String message) { return null; }
			@Override public String message() { return null; }
			@Override public <T> List<T> all(Class<T> type) { return null; }
			@Override public ListAction with(Order... order) { return null; }
		});
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		
		when(result.on(Exception.class)).thenReturn(result);
		when(result.use(status())).thenReturn(status);
		
		when(container.instanceFor(Result.class)).thenReturn(result);
		when(container.instanceFor(Validator.class)).thenReturn(validator);
		when(container.instanceFor(list())).thenReturn(new MockListAction());
		when(container.instanceFor(load())).thenReturn(new MockLoadAction());
		when(container.instanceFor(persist())).thenReturn(new MockPersistAction());
		when(container.instanceFor(delete())).thenReturn(new MockDeleteAction());
		when(container.instanceFor(pagination())).thenReturn(new MockPaginationAction());
		
		act = spy(new DefaultAct(container));
	}

	@Test
	public void shouldReturnListAllActionInstance() {
		assertThat(act.as(list()), instanceOf(ListAction.class));
	}
	
	@Test
	public void shouldReturnResultInstance() {
		assertThat(act.result(), instanceOf(Result.class));
	}
	
	@Test
	public void shouldExecuteListAll() {
		act.listAll(MyModel.class);
		
		verify(act).as(list());
	}
	
	@Test
	public void shouldExecuteLoadBy() {
		act.loadBy(MyModel.class, 1L);
		
		verify(act).as(load());
	}
	
	@Test
	public void shouldExecutePersistSave() {
		act.save(new MyModel());
		
		verify(act).as(persist());
	}

	@Test
	public void shouldExecutePersistInsert() {
		act.insert(new MyModel());
		
		verify(act).as(persist());
	}
	
	@Test
	public void shouldExecutePersistUpdate() {
		act.update(new MyModel());
		
		verify(act).as(persist());
	}
	
	@Test
	public void shouldExecuteDeleteBy() {
		act.deleteBy(MyModel.class, 1L);
		
		verify(act).as(delete());
	}
	
	@Test
	public void shouldExecuteValidationErrorBadRequest() {
		act.onErrorSendBadRequest();
		
		verify(validator).onErrorSendBadRequest();
		verify(result).on(Exception.class);
	}
	
	@Test
	public void shouldExecuteValidationErrorRedirect() {
		act.onErrorRedirectToReferer();
		
		verify(validator).onErrorUse(referer());
		verify(result).on(Exception.class);
	}
}
