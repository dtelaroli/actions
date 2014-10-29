package br.com.caelum.vraptor.actions.api.action;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.actions.api.Action;
import br.com.caelum.vraptor.actions.api.Db;

public interface Activity extends Action {

	Action withMessage(String message);

	Db db();

	Result result();

	String message();

}
