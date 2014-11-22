package br.com.caelum.vraptor.actions.core.template;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.actions.core.template.VelocityBuilder;

import com.google.common.collect.Maps;

public class VelocityBuilderTest {

	private VelocityBuilder builder;

	@Before
	public void setUp() throws Exception {
		builder = new VelocityBuilder();
		builder.setUp();
	}

	@Test
	public void shouldWriteTemplate() throws NoSuchMethodException,
			SecurityException {
		final Map<String, Object> params = Maps.newHashMap();
		params.put("parameterKey", "parameterValue");
		String generate = builder.generate(params, "/test.vm");
		assertThat(generate, equalTo("parameterValue"));
	}

}
