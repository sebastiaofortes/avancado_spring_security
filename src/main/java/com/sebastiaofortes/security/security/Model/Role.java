package com.sebastiaofortes.security.security.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	@Id
	private String nomeRole;
	
	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	@ManyToMany(mappedBy = "roles",  cascade = CascadeType.ALL)
	private List<Usuarios> usuarios;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nomeRole;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	

       
}
