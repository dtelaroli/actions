package br.com.caelum.vraptor.actions.core.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import models.MyModel;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.actions.core.test.MockLoadAction;

public class MockLoadActionTest {

	private MockLoadAction mock;
	private MyModel model;
	
	@Before
	public void setUp() throws Exception {
		mock = new MockLoadAction();
		model = new MyModel(1L);
	}

	@Test
	public void shouldReturnFromModel() {
		mock.putReturn(model.getClass(), model);
		MyModel by = mock.by(MyModel.class, 1L);
		assertThat(by, notNullValue());
		assertThat(by.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnEmpty() {
		MyModel by = mock.by(MyModel.class, 1L);
		assertThat(by, notNullValue());
		assertThat(by.getId(), nullValue());
	}
}
