package br.com.caelum.vraptor.actions.api;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

public interface Act {

	<T extends Action> T as(Class<T> act);
	
	Result result();
	
	Validator validator();
	
}
