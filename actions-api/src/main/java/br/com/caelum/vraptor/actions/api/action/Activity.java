package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.actions.api.Action;

public interface Activity extends Action {

	Action withMessage(String message);

	String message();
	
}
