package br.com.caelum.vraptor.actions.api.test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MockListActionTest {

	private MockListAction mock;
	private MyModel model;
	
	@Before
	public void setUp() throws Exception {
		mock = new MockListAction();
		model = new MyModel(1L);
	}

	@Test
	public void shouldReturnFromModel() {
		List<MyModel> list = mock.returning(model).all(MyModel.class);
		assertThat(list.get(0), notNullValue());
		assertThat(list.get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnFromList() {
		List<MyModel> list = mock.returning(Arrays.asList(model)).all(MyModel.class);
		assertThat(list.get(0), notNullValue());
		assertThat(list.get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnEmpty() {
		List<MyModel> list = mock.all(MyModel.class);
		assertThat(list, notNullValue());
		assertThat(list, empty());
	}
}
