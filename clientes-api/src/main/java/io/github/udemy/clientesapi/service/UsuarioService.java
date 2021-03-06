package io.github.udemy.clientesapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.udemy.clientesapi.controller.exception.UsuarioCadastradoException;
import io.github.udemy.clientesapi.model.entity.Usuario;
import io.github.udemy.clientesapi.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	public Usuario salvar(Usuario usuario) {
		boolean exists = usuarioRepository.existsByUsername(usuario.getUsername());
		if(exists){
			throw new UsuarioCadastradoException(usuario.getUsername());
		}
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
			if (usuarioOptional.isPresent()) {
				Usuario usuario = usuarioOptional.get();
				return User
						.builder()
						.username(usuario.getUsername())
						.password(usuario.getPassword())
						.roles("USER")
						.build();
			}else {
				throw new UsernameNotFoundException("Login não encontrado.");
			}
		
	}
}
