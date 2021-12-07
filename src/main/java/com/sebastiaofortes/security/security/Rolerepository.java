package com.sebastiaofortes.security.security;

import com.sebastiaofortes.security.security.Model.Role;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface Rolerepository extends CrudRepository<Role, String>{
	// O Spring Data JPA se encarrega de criar automaticamente os métodos de busca de dados declarados na interface.
	// para isso ele busca os nomes dos atributos da classe de entidade nos nomes dos métodos declarados na interface.
	// portanto o nome do método deve conter o nome do atributo da classe de entidade que se deseja buscar no banco.
	Role findByNomeRole (String nomeRole); 
	long deleteByNomeRole(String nomeRole); 
	
	@Modifying 
	@Query(value = "DELETE FROM USUARIOS_ROLES u WHERE u.USUARIO_ID = ?1 AND u.ROLE_ID = ?2", nativeQuery = true)
	void deleteOneUR(String usuario, String nomerole);
	
	@Modifying 
	@Query(value = "DELETE FROM USUARIOS_ROLES u WHERE u.ROLE_ID = ?1", nativeQuery = true)
	void deleteUR(String nomerole);
	
	@Modifying 
	@Query(value = "INSERT INTO USUARIOS_ROLES (USUARIO_ID, ROLE_ID) VALUES (?1, ?2)", nativeQuery = true)
	void insertUR(String usuario, String role);
	
	@Modifying 
	@Query(value = "SELECT * FROM USUARIOS_ROLES", nativeQuery = true)
	List<String[]> selectUR();
}


