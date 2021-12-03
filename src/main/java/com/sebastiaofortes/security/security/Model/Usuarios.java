package com.sebastiaofortes.security.security.Model;

import javax.persistence.GeneratedValue;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usua")
public class Usuarios implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	
	
	@Column(name = "nome")
	private String login;
	
	private String email;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	
	@ManyToMany
	
	// pelo fato dessas anotações estarem mescladas sua ide pode não reconhecer a falta do import
	// pode também acusar algum erro diferente da falta de import mas a causa do erro muitas vezes pode ser
	// a falta de import não reconhecida pela IDE.
	
	@JoinTable( 
	        name = "usuarios_roles", 
	        joinColumns = @JoinColumn(
	          name = "usuario_id", referencedColumnName = "nome"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_id", referencedColumnName = "nomeRole")) 
	private List<Role> roles;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}
	
	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
