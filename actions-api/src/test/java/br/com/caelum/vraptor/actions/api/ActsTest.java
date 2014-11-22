package br.com.caelum.vraptor.actions.api;

import static br.com.caelum.vraptor.actions.api.Acts.delete;
import static br.com.caelum.vraptor.actions.api.Acts.email;
import static br.com.caelum.vraptor.actions.api.Acts.list;
import static br.com.caelum.vraptor.actions.api.Acts.load;
import static br.com.caelum.vraptor.actions.api.Acts.pagination;
import static br.com.caelum.vraptor.actions.api.Acts.persist;
import static br.com.caelum.vraptor.actions.api.Acts.session;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.caelum.vraptor.actions.api.action.DeleteAction;
import br.com.caelum.vraptor.actions.api.action.ListAction;
import br.com.caelum.vraptor.actions.api.action.LoadAction;
import br.com.caelum.vraptor.actions.api.action.PaginationAction;
import br.com.caelum.vraptor.actions.api.action.PersistAction;
import br.com.caelum.vraptor.actions.api.service.EmailAction;
import br.com.caelum.vraptor.actions.api.service.SessionAction;

public class ActsTest {

	@Test
	public void shouldReturnListAllAction() {
		assertThat(list(), typeCompatibleWith(ListAction.class));
	}
	
	@Test
	public void shouldReturnViewAction() {
		assertThat(load(), typeCompatibleWith(LoadAction.class));
	}
	
	@Test
	public void shouldReturnSaveAction() {
		assertThat(persist(), typeCompatibleWith(PersistAction.class));
	}
	
	@Test
	public void shouldReturnPaginateAction() {
		assertThat(pagination(), typeCompatibleWith(PaginationAction.class));
	}
	
	@Test
	public void shouldReturnRemoveAction() {
		assertThat(delete(), typeCompatibleWith(DeleteAction.class));
	}

	@Test
	public void shouldReturnSessionAction() {
		assertThat(session(), typeCompatibleWith(SessionAction.class));
	}
	
	@Test
	public void shouldReturnEmailAction() {
		assertThat(email(), typeCompatibleWith(EmailAction.class));
	}

}
