package br.com.caelum.vraptor.actions.api.test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.actions.api.db.pagination.Page;

public class MockPaginationActionTest {

	private MockPaginationAction mock;
	
	@Before
	public void setUp() throws Exception {
		mock = new MockPaginationAction();
	}

	@Test
	public void shouldReturnPageFromModel() {
		Page<MyModel> page = mock.returning(new MyModel(1L)).page(2).limit(10).paginate(MyModel.class);
		assertThat(page, notNullValue());
		assertThat(page.getNumber(), equalTo(2));
		assertThat(page.getLimit(), equalTo(10));
		assertThat(page.getList().get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnPageFromList() {
		Page<MyModel> page = mock.returning(Arrays.asList(new MyModel(1L))).page(2).limit(10).paginate(MyModel.class);
		assertThat(page, notNullValue());
		assertThat(page.getNumber(), equalTo(2));
		assertThat(page.getLimit(), equalTo(10));
		assertThat(page.getList().get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnPageFromPage() {
		Page<MyModel> page = mock.returning(new Page<>(new MyModel(1L))).page(2).limit(10).paginate(MyModel.class);
		assertThat(page, notNullValue());
		assertThat(page.getNumber(), equalTo(2));
		assertThat(page.getLimit(), equalTo(10));
		assertThat(page.getList().get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnEmpty() {
		Page<MyModel> page = mock.page(2).limit(10).paginate(MyModel.class);
		assertThat(page, notNullValue());
		assertThat(page.getList(), empty());
	}

}
