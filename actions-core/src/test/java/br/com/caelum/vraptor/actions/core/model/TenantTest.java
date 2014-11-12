package br.com.caelum.vraptor.actions.core.model;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

public class TenantTest {

	private Tenant model;

	@Before
	public void setUp() throws Exception {
		model = new Tenant();
		model.setName("name");
	}

	@Test
	public void shouldPersistModel() {
		Ebean.save(model);
		
		assertThat(model.getId(), greaterThan(0L));
	}
	
}
