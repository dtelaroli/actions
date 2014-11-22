package br.com.caelum.vraptor.actions.core.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import models.MyModel;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.test.MyController;
import br.com.caelum.vraptor.actions.core.test.MockPersistAction;
import br.com.caelum.vraptor.util.test.MockResult;

public class MockPersistActionTest {

	private MockPersistAction mock;
	private MyModel model;
	private Result result;
	
	@Before
	public void setUp() throws Exception {
		result = spy(new MockResult());
		mock = new MockPersistAction(result, null, null);
		model = new MyModel(1L);
	}

	@Test
	public void shouldReturnFromModelSave() {
		MyModel myModel = new MyModel();
		mock.putReturn(model.getClass(), model);
		MyModel saved = mock.save(myModel).andReturn();
		assertThat(saved, notNullValue());
		assertThat(saved.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnFromModelInsert() {
		MyModel myModel = new MyModel();
		mock.putReturn(model.getClass(), model);
		MyModel saved = mock.insert(myModel).andReturn();
		assertThat(saved, notNullValue());
		assertThat(saved.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnFromModelUpdate() {
		MyModel myModel = new MyModel();
		mock.putReturn(model.getClass(), model);
		MyModel saved = mock.update(myModel).andReturn();
		assertThat(saved, notNullValue());
		assertThat(saved.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnEmpty() {
		MyModel myModel = new MyModel();
		MyModel model = mock.save(myModel).andReturn();
		assertThat(model, notNullValue());
		assertThat(model.getId(), nullValue());
	}
	
	@Test
	public void shouldRedirect() {
		mock.update(new MyModel()).redirectTo(MyController.class).index();
		verify(result).redirectTo(MyController.class);
	}
}
