package com.sebastiaofortes.security.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.sebastiaofortes.security.security.Model.Usuarios;

@Repository
@Transactional
public class ImplementsUserDetailService implements UserDetailsService{

	@Autowired
	private Usuariorepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuarios usuario = ur.findByLogin(login);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado !");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities()); // O tipo User (do security.core) é uma  implementação do tipo UserDetails por isso pode ser retorndo nessa função
	}

}
