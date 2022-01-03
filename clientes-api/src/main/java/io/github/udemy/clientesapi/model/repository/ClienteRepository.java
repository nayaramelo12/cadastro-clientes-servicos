package io.github.udemy.clientesapi.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import io.github.udemy.clientesapi.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
