package io.github.udemy.clientesapi.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.github.udemy.clientesapi.controller.exception.UsuarioCadastradoException;
import io.github.udemy.clientesapi.model.entity.Usuario;
import io.github.udemy.clientesapi.model.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {
	
	
	@MockBean
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Test
	void loadUserByUsernameSucesso() {
		Usuario usuario = new Usuario();
		usuario.setUsername("jean");
		usuario.setPassword("123");
		
		when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(usuario));
		
		UserDetails user = usuarioService.loadUserByUsername("jean");
		
		assertTrue(user.getUsername().equals("jean"));
	}
	
	@Test
	void loadUserByUsernameFalha() {
		Usuario usuario = new Usuario();
		usuario.setUsername("jean");
		usuario.setPassword("123");
		
		when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		
		UsernameNotFoundException thrown = assertThrows(
	    		UsernameNotFoundException.class,
	            () -> usuarioService.loadUserByUsername("jean"),
	            "Login não encontrado."
	     );

	     assertTrue(thrown.getMessage().contains("Login não encontrado."));
	}
	
	@Test
	void salvarUsuarioSucesso() {
		Usuario usuario = new Usuario();
		usuario.setUsername("jean");
		usuario.setPassword("123");
		
		when(usuarioRepository.existsByUsername(anyString())).thenReturn(false);
		when(usuarioRepository.save(usuario)).thenReturn(usuario);
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		assertTrue(usuarioSalvo.getUsername().equals("jean"));
		
	}
	
	@Test
	void salvarUsuarioFalha() {
		Usuario usuario = new Usuario();
		usuario.setUsername("jean");
		usuario.setPassword("123");
		
		when(usuarioRepository.existsByUsername(anyString())).thenReturn(true);
		when(usuarioRepository.save(usuario)).thenReturn(usuario);
		
		UsuarioCadastradoException thrown = assertThrows(
				UsuarioCadastradoException.class,
	            () -> usuarioService.salvar(usuario),
	            "Usuário já cadastrado!"
	     );

	     assertTrue(thrown.getMessage().contains("Usuário já cadastrado!"));
		
		
	}
	
}
