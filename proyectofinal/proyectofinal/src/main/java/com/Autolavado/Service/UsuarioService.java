package com.Autolavado.Service;

import java.util.List;
import java.util.Optional;

import com.Autolavado.Entity.Usuario;


public interface UsuarioService {
    List<Usuario> getAllUsers();
    Optional<Usuario> getUsuarioById(Integer id);
    Usuario saveUser(Usuario usuario);
    //User updateUser(Integer id, Usuario usuarioDetails);
    void deleteUsuario(Integer id);
    String encryptPassword(String password);
    boolean login(String email, String password);
    void sendEmail(String to, String subject, String body);
    Usuario findByEmailAndDocument(String email, String document);

    String generateTemporaryPassword();


    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> getUserById(Integer id);

    void deleteUsuario(Long id);
}
