package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.caelum.vraptor.actions.api.db.IModel;

@Entity
public class MyModel implements IModel {

	private String name;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public MyModel() {
	}
	
	public MyModel(long id) {
		setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
