package br.com.caelum.vraptor.actions.core.template;

import java.io.StringWriter;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

@ApplicationScoped
public class VelocityBuilder {

	private VelocityEngine engine;

	@PostConstruct
	public void setUp() {
		engine = delegate();
	}

	private VelocityEngine delegate() {
		VelocityEngine velocityEngine = new VelocityEngine();
		
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, Log4JLogChute.class.getName());
		velocityEngine.init();
		
		return velocityEngine;
	}

	public String generate(Map<String, Object> params, String name) {
		try {
			StringWriter writer = new StringWriter();
			mergeTemplate(name, params, writer);
			writer.close();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void mergeTemplate(String name, Map<String, Object> params,
			StringWriter writer) {
		Template template = engine.getTemplate(name);
		template.merge(new VelocityContext(params), writer);
	}
}