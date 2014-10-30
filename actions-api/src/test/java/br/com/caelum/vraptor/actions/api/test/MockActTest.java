package br.com.caelum.vraptor.actions.api.test;

import static br.com.caelum.vraptor.actions.api.Acts.delete;
import static br.com.caelum.vraptor.actions.api.Acts.list;
import static br.com.caelum.vraptor.actions.api.Acts.load;
import static br.com.caelum.vraptor.actions.api.Acts.pagination;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;
import br.com.caelum.vraptor.validator.Validator;

public class MockActTest {

	MockAct mock;
	
	@Before
	public void setUp() throws Exception {
		mock = new MockAct();
	}

	@Test
	public void shouldNotThrowNullPointersOnFindDbAll() {
		mock.as(list()).all(MyModel.class);
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbPersist() {
		mock.as(load()).by(MyModel.class, 1);
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbDelete() {
		mock.as(delete()).by(MyModel.class, 1L);
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbPaginate() {
		mock.as(pagination()).page(1).limit(10).paginate(MyModel.class);
	}
	
	@Test
	public void shouldReturnInstanceOfPageFromModel() {
		mock.returning(new MyModel(1L));
		Page<MyModel> page = mock.as(pagination()).page(1).limit(10).paginate(MyModel.class);
		assertThat(page.getList().get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnInstanceOfPageFromList() {
		mock.returning(Arrays.asList(new MyModel(1L)));
		Page<MyModel> page = mock.as(pagination()).page(1).limit(10).paginate(MyModel.class);
		assertThat(page.getList().get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnInstanceOfPageFromPage() {
		mock.returning(new Page<>(new MyModel(1L)));
		Page<MyModel> page = mock.as(pagination()).page(1).limit(10).paginate(MyModel.class);
		assertThat(page.getList().get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnMockResultInstance() {
		assertThat(mock.result(), instanceOf(Result.class));
	}
	
	@Test
	public void shouldReturnMockValidatorInstance() {
		assertThat(mock.validator(), instanceOf(Validator.class));
	}

}
