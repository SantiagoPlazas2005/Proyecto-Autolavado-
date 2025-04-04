package com.Autolavado.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.Autolavado.Entity.Usuario;
import com.Autolavado.Service.UsuarioService;

@RestController
@RequestMapping("/api/easypark")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/getAll")
    public List<Usuario> getAllUsers(){
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getUserById(@PathVariable Integer id){
        return usuarioService.getUserById(id);
    }

    @PostMapping
    public Usuario  createUser(@RequestBody Usuario usuario){
        return usuarioService.saveUser(usuario);
    }

    // Manejo de error para email duplicado
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateEmailError(DataIntegrityViolationException ex) {
        return "El email ya está registrado.";
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        usuarioService.deleteUsuario(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginUser) {
        boolean isAuthenticated = usuarioService.login(loginUser.getEmail(), loginUser.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Inicio de sesión exitoso"));
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales incorrectas"));
        }
    }

    @GetMapping("/sendTestEmail")
    public String sendTestEmail() {
        usuarioService.sendEmail("escontreras-2023a@corhuila.edu.co", "Prueba de correo", "¡Hola! Este es un correo de prueba desde Spring Boot.");
        return "Correo enviado con éxito";
    }

    @PostMapping("/recover-password")
    public ResponseEntity<?> recoverPassword(@RequestBody Usuario recoveryUser) {
        Usuario usuario = usuarioService.findByEmailAndDocument(recoveryUser.getEmail(), recoveryUser.getDocument());

        if (usuario != null) {
            String newPassword = usuarioService.generateTemporaryPassword();
            usuario.setPassword(usuarioService.encryptPassword(newPassword));
            usuarioService.saveUser(usuario); // Guarda la nueva contraseña encriptada



            // Enviar correo con la nueva contraseña temporal
            String subject = "Recuperación de contraseña - EasyPark";
            String body = "Hola, " + usuario.getEmail() + "\n\n" +
                    "Tu nueva contraseña temporal es: " + newPassword + "\n\n" +
                    "Por favor, inicia sesión y cambia tu contraseña lo antes posible.";

            usuarioService.sendEmail(usuario.getEmail(), subject, body);

            return ResponseEntity.ok(Collections.singletonMap("message", "Se ha enviado una nueva contraseña a tu correo."));
        } else {
            return ResponseEntity.status(404).body(Collections.singletonMap("error", "No se encontró un usuario con esos datos."));
        }
    }


}