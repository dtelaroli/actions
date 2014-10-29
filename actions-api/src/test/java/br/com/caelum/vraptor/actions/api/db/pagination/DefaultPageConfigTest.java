package br.com.caelum.vraptor.actions.api.db.pagination;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.actions.api.db.pagination.DefaultPageConfig;

public class DefaultPageConfigTest {

	private DefaultPageConfig config;
	
	@Before
	public void setUp() throws Exception {
		config = new DefaultPageConfig();
	}

	@Test
	public void shouldBe20() {
		assertThat(config.getLimit(), equalTo(20));
	}

}
