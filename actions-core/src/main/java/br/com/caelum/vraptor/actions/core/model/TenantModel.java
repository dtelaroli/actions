package br.com.caelum.vraptor.actions.core.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class TenantModel extends Model {

	private static final long serialVersionUID = -1438057629986553768L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Tenant tenant;

	public TenantModel() {
	}
	
	public TenantModel(Tenant tenant) {
		this.tenant = tenant;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
}
