package com.autolavado.Autolavado.controller;

import com.autolavado.Autolavado.entity.Cliente;
import com.autolavado.Autolavado.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteService clienteService;
  private final PasswordEncoder passwordEncoder;

  // Constructor con inyección de dependencias
  @Autowired
  public ClienteController(ClienteService clienteService, PasswordEncoder passwordEncoder) {
    this.clienteService = clienteService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping
  public List<Cliente> listarClientes() {
    return clienteService.listarClientes();
  }

  @GetMapping("/{id}")
  public Optional<Cliente> obtenerClientePorId(@PathVariable Long id) {
    return clienteService.obtenerClientePorId(id);
  }

  @PostMapping("/register")
  public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
    Optional<Cliente> clienteExistente = clienteService.obtenerPorEmail(cliente.getEmail());

    if (clienteExistente.isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe");
    }

    cliente.setPassword(passwordEncoder.encode(cliente.getPassword())); // 🔹 Encripta la contraseña

    Cliente nuevoCliente = clienteService.guardarCliente(cliente);
    return ResponseEntity.ok(nuevoCliente);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Cliente loginRequest) {
    Optional<Cliente> cliente = clienteService.obtenerPorEmail(loginRequest.getEmail());

    if (cliente.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), cliente.get().getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
    }

    return ResponseEntity.ok("Inicio de sesión exitoso");
  }

  @DeleteMapping("/{id}")
  public void eliminarCliente(@PathVariable Long id) {
    clienteService.eliminarCliente(id);
  }
}

