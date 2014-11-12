package br.com.caelum.vraptor.actions.core.model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import models.MyModel;

import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

public class ModelTest {

	private MyModel model;

	@Before
	public void setUp() throws Exception {
		model = new MyModel();
		model.setName("foo");
	}

	@Test
	public void shouldPersistModel() {
		Ebean.save(model);
		
		assertThat(model.getId(), notNullValue());
		assertThat(model.getId(), greaterThan(0L));
	}
	
	@Test
	public void shouldSetDatesOnPersist() {
		Ebean.save(model);
		
		assertThat(model.getId(), greaterThan(0L));
		assertThat(model.getCreatedAt(), notNullValue());
		assertThat(model.getUpdatedAt(), notNullValue());
		assertThat(model.getCreatedAt(), equalTo(model.getUpdatedAt()));
	}

	@Test
	public void shouldSetUpdatedAtOnUpdate() {
		Ebean.save(model);
		assertThat(model.getId(), greaterThan(0L));
		assertThat(model.getCreatedAt(), notNullValue());
		assertThat(model.getUpdatedAt(), notNullValue());
		assertThat(model.getCreatedAt(), equalTo(model.getUpdatedAt()));

		model.setName("bar");
		Ebean.update(model);
		assertThat(model.getCreatedAt(), lessThan(model.getUpdatedAt()));
	}
	
}
