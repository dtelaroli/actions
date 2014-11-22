package br.com.caelum.vraptor.actions.core.test;

import static br.com.caelum.vraptor.actions.api.Acts.delete;
import static br.com.caelum.vraptor.actions.api.Acts.list;
import static br.com.caelum.vraptor.actions.api.Acts.load;
import static br.com.caelum.vraptor.actions.api.Acts.pagination;
import static br.com.caelum.vraptor.actions.api.Acts.persist;
import static br.com.caelum.vraptor.view.Results.referer;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import models.MyModel;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Validator;

public class MockActTest {

	MockAct mock;
	private Result result;
	private Validator validator;
	
	@Before
	public void setUp() throws Exception {
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		mock = new MockAct(result, validator);
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

	@Test
	public void shouldReturnInstanceOfListFromList() {
		mock.returning(new MyModel(1L));
		List<MyModel> list = mock.as(list()).all(MyModel.class);
		assertThat(list.get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnInstanceOfModel() {
		mock.returning(new MyModel(1L));
		MyModel model = mock.as(load()).by(MyModel.class, 1L);
		assertThat(model.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnInstanceOfModelOnSave() {
		mock.returning(new MyModel(1L));
		MyModel model = mock.as(persist()).save(new MyModel()).andReturn();
		assertThat(model.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturn1OnDelete() {
		mock.returning(1);
		Integer i = mock.as(delete()).by(MyModel.class, 1L).andReturn();
		assertThat(i, equalTo(1));
	}
	
	@Test
	public void shouldRedirectAfterDelete() {
		mock.as(delete()).by(MyModel.class, 1L).redirectTo(MyController.class).index();
		verify(result).redirectTo(MyController.class);
	}
	
	@Test
	public void shouldExecuteValidationErrorBadRequest() {
		mock.onErrorSendBadRequest();
		
		verify(validator).onErrorSendBadRequest();
		verify(result).on(Exception.class);
	}
	
	@Test
	public void shouldExecuteValidationErrorRedirect() {
		mock.onErrorRedirectToReferer();
		
		verify(validator).onErrorUse(referer());
		verify(result).on(Exception.class);
	}
}
