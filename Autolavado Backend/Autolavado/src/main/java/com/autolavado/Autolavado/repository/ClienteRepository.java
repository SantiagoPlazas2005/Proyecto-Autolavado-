package com.autolavado.Autolavado.repository;

import com.autolavado.Autolavado.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Cliente findByEmail(String email);
  Cliente findByEmailAndContraseña(String email, String contraseña); // Nueva consulta para validar login
}
