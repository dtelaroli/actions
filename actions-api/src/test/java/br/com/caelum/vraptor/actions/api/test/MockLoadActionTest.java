package br.com.caelum.vraptor.actions.api.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

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
		MyModel by = mock.returning(model).by(MyModel.class, 1L);
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
