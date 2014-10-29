package br.com.caelum.vraptor.actions.db.ebean;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import models.MyModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.actions.api.db.DeleteDb;
import br.com.caelum.vraptor.actions.db.ebean.DefaultDeleteDb;
import br.com.caelum.vraptor.dbunit.ebean.DbUnitEbean;

import com.avaje.ebean.Ebean;

public class DefaultDeleteDbTest {

	private DeleteDb db;

	@BeforeClass
	public static void beforeClass() throws Exception {
		new DbUnitEbean().init(MyModel.class);
	}
	
	@Before
	public void setUp() {
		db = new DefaultDeleteDb();
	}
	
	@Test
	public void shouldRemoveMyModel() {
		int by = db.by(MyModel.class, 1L);
		assertThat(by, equalTo(1));
		assertThat(Ebean.find(MyModel.class, 1L), nullValue());
	}

}
