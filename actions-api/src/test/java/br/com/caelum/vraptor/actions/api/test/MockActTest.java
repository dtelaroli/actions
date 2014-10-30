package br.com.caelum.vraptor.actions.api.test;

import static br.com.caelum.vraptor.actions.api.Actions.delete;
import static br.com.caelum.vraptor.actions.api.Actions.list;
import static br.com.caelum.vraptor.actions.api.Actions.load;
import static br.com.caelum.vraptor.actions.api.Actions.pagination;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.actions.api.Act;
import br.com.caelum.vraptor.actions.api.test.MockAct;

import com.avaje.ebean.Model;

public class MockActTest {

	Act mock;
	
	@Before
	public void setUp() throws Exception {
		mock = new MockAct();
	}

	@Test
	public void shouldNotThrowNullPointersOnFindDbAll() {
		mock.as(list()).all(Model.class);
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbPersist() {
		mock.as(load()).by(Model.class, 1);
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbDelete() {
		mock.as(delete()).by(Model.class, 1L);
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbPaginate() {
		mock.as(pagination()).page(1).limit(10).paginate(Model.class);
	}

}
