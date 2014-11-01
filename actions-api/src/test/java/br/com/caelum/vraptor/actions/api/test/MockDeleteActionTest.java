package br.com.caelum.vraptor.actions.api.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class MockDeleteActionTest {

	private MockDeleteAction mock;
	private Result result;
	
	@Before
	public void setUp() throws Exception {
		result = spy(new MockResult());
		mock = new MockDeleteAction(result, null, null);
	}

	@Test
	public void shouldReturn1() {
		Integer andReturn = mock.returning(1).by(MyModel.class, 1L).andReturn();
		assertThat(andReturn, notNullValue());
		assertThat(andReturn, equalTo(1));
	}
	
	@Test
	public void shouldReturnEmpty() {
		Integer andReturn = mock.by(MyModel.class, 1L).andReturn();
		assertThat(andReturn, notNullValue());
		assertThat(andReturn, equalTo(0));
	}
	
	@Test
	public void shouldRedirect() {
		mock.by(MyModel.class, 1L).redirectTo(MyController.class).index();
		verify(result).redirectTo(MyController.class);
	}
}
