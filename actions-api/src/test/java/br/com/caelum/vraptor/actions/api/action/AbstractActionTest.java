package br.com.caelum.vraptor.actions.api.action;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import models.MyModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.test.MyController;
import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class AbstractActionTest {

	private AbstractAction act;
	@Spy private MockSerializationResult result = new MockSerializationResult();
	@Mock private Db db;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(result.redirectTo(MyController.class)).thenReturn(new MyController());
		
		act = new AbstractAction(result, db) {};		
	}

	@Test
	public void shouldReturnDbInstance() {
		assertThat(act.db(), instanceOf(Db.class));
	}
	
	@Test
	public void shouldReturnResultInstance() {
		assertThat(act.result(), instanceOf(Result.class));
	}
	
	@Test
	public void shouldReturnObject() {
		assertThat(act.withDbObject(new MyModel()).andReturn(), instanceOf(MyModel.class));
	}
	
	@Test
	public void shouldReturnControllerForRedirect() {
		assertThat(act.redirectTo(MyController.class), instanceOf(MyController.class));
		assertThat(act.redirectTo(new MyController()), instanceOf(MyController.class));
	}

	@Test
	public void shouldSetMessage() {
		assertThat(act.withMessage("foo").message(), equalTo("foo"));
		assertThat(result.included().get("message"), equalTo("foo"));
	}
	
	@Test
	public void shouldIncludeObject() {
		act.include("foo", "bar");
		assertThat(act.result().included().get("foo"), equalTo("bar"));
	}
	
	@Test
	public void shouldSerializeResult() throws Exception {
		act.withDbObject(new MyModel(1L)).json().serialize();
		assertThat(result.serializedResult(), containsString("{\"id\":1"));
	}
}
