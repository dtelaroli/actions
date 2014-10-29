package br.com.caelum.vraptor.actions.api.test;

import static br.com.caelum.vraptor.actions.api.Databases.delete;
import static br.com.caelum.vraptor.actions.api.Databases.find;
import static br.com.caelum.vraptor.actions.api.Databases.persist;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.actions.api.Db;
import br.com.caelum.vraptor.actions.api.db.IModel;
import br.com.caelum.vraptor.actions.api.test.MockDatabase;

import com.avaje.ebean.Model;

public class MockDatabaseTest {

	Db mock;
	
	@Before
	public void setUp() throws Exception {
		mock = new MockDatabase();
	}

	@Test
	public void shouldNotThrowNullPointersOnFindDbAll() {
		mock.use(find()).all(Model.class);
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbPersist() {
		mock.use(persist()).save(new IModel() {
			@Override
			public Object getId() {
				return null;
			}});
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbInsert() {
		mock.use(persist()).insert(new MyModel());
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbUpdate() {
		mock.use(persist()).update(new MyModel());
	}
	
	@Test
	public void shouldNotThrowNullPointersOnFindDbDelete() {
		mock.use(delete()).by(Model.class, 1L);
	}

}
