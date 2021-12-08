package com.sebastiaofortes.security.security;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityApplication {
	



	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
		System.out.println("Execute as instruções SQL abaixo no seu BD para criar o primeiro usuário :)");
		System.out.println("Obs: a senha do usuário criado com esse código é 123");
		System.out.println("INSERT INTO USUA (ID, SENHA, NOME) VALUES (1, '"+new BCryptPasswordEncoder().encode("123")+"', 'sebastiaofortes4@gmail.com');");
		System.out.println("INSERT INTO ROLE ( NOME_ROLE ) VALUES ('ROLE_ADMIN');");	
		System.out.println("INSERT INTO USUARIOS_ROLES ( USUARIO_ID , ROLE_ID ) VALUES ( 1, 'ROLE_ADMIN');");
	}

}
