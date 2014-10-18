package br.com.caelum.vraptor.plus.db.ebean;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.plus.api.db.ListAllDb;
import br.com.flexait.caelum.vraptor.plus.dbunit.DbUnit;
import br.com.flexait.caelum.vraptor.plus.dbunit.DbUnitEbean;

public class DefaultListAllDbTest {

	private ListAllDb db;
	
	@Before
	public void setUp() throws Exception {
		DbUnit dbUnit = new DbUnitEbean();
		dbUnit.init(MyModel.class);
		db = new DefaultListAllDb();
	}
	
	@Test
	public void shouldReturnListOfMyModel() {
		List<MyModel> all = db.all(MyModel.class);
		assertThat(all, notNullValue());
		assertThat(all.get(0).getId(), equalTo(1L));
	}

}