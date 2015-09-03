package br.com.caelum.vraptor.actions.db.ebean;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import models.MyModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.actions.api.db.PersistDb;
import br.com.flexait.dbunit.ebean.DbUnitEbean;

import com.avaje.ebean.Ebean;

public class DefaultPersistDbTest {

	private PersistDb db;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		new DbUnitEbean().init(MyModel.class);
	}
	
	@Before
	public void setUp() throws Exception {
		db = new DefaultPersistDb();
	}
	
	@Test
	public void shouldSaveNewMyModel() {
		MyModel model = new MyModel();
		model.setName("Foo");
		model = db.save(model);
		assertThat(model.getId(), equalTo(4L));
	}

	@Test
	public void shouldUpdateMyModel() {
		MyModel model = new MyModel(1L);
		model.setName("Bla");
		db.save(model);
		
		model = Ebean.find(MyModel.class, 1L);
		assertThat(model.getName(), equalTo("Bla"));
	}
	
	@Test
	public void shouldSaveNewMyModel2() {
		MyModel model = new MyModel();
		model.setName("Foo");
		model = db.insert(model);
		assertThat(model.getId(), equalTo(5L));
	}

	@Test
	public void shouldUpdateMyModel2() {
		MyModel model = new MyModel(1L);
		model.setName("Bla");
		db.update(model);
		
		model = Ebean.find(MyModel.class, 1L);
		assertThat(model.getName(), equalTo("Bla"));
	}
}
