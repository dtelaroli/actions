package br.com.caelum.vraptor.actions.db.ebean;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import models.MyModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.actions.api.db.FindDb;
import br.com.caelum.vraptor.actions.api.db.order.Order;
import br.com.caelum.vraptor.actions.api.db.pagination.Page;
import br.com.flexait.dbunit.ebean.DbUnitEbean;

public class DefaultFindDbTest {

	private FindDb db;

	@BeforeClass
	public static void beforeClass() throws Exception {
		new DbUnitEbean().init(MyModel.class);
	}
	
	@Before
	public void setUp() throws Exception {
		db = new DefaultFindDb();
	}
	
	@Test
	public void shouldReturnListOfMyModel() {
		List<MyModel> all = db.all(MyModel.class);
		assertThat(all, notNullValue());
		assertThat(all.get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnMyModel() {
		MyModel model = db.by(MyModel.class, 1L);
		assertThat(model.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnListPaginateOfMyModelPage1() {
		Page<MyModel> page = db.paginate(MyModel.class, 1, 2);
		assertThat(page.getList(), not(empty()));
		assertThat(page.getList().get(1).getId(), equalTo(2L));
		assertThat(page.getPageSize(), equalTo(2));
	}
	
	@Test
	public void shouldReturnListPaginateOfMyModelPage2() {
		Page<MyModel> page = db.paginate(MyModel.class, 2, 2);
		assertThat(page.getList(), not(empty()));
		assertThat(page.getList().get(0).getId(), equalTo(3L));
		assertThat(page.getPageSize(), equalTo(1));
	}
	
	@Test
	public void shouldReturnOrderedListOfMyModel() {
		List<MyModel> all = db.with(Order.desc("name")).all(MyModel.class);
		assertThat(all, notNullValue());
		assertThat(all.get(0).getName(), equalTo("Name 3"));
	}
	
	@Test
	public void shouldReturnOrderedListPaginateOfMyModelPage2() {
		Page<MyModel> page = db.with(Order.desc("name")).paginate(MyModel.class, 2, 2);
		assertThat(page.getList(), not(empty()));
		assertThat(page.getList().get(0).getId(), equalTo(1L));
		assertThat(page.getPageSize(), equalTo(1));
	}
}
