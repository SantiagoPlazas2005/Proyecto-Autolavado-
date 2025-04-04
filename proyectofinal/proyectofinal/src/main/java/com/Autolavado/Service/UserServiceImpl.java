package com.Autolavado.Service;

import com.Autolavado.Entity.Usuario;
import com.Autolavado.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Usuario saveUser(Usuario usuario) {
        // Encripta la contraseña solo si no está ya encriptada (64 caracteres en SHA-256)
        if (usuario.getPassword() != null && usuario.getPassword().length() != 64) {
            usuario.setPassword(encryptPassword(usuario.getPassword()));
        }
        return userRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean login(String email, String password) {
        Optional<Usuario> usuarioOpt = userRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String encryptedPassword = encryptPassword(password);

            if (usuario.getPassword().equals(encryptedPassword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contrerasrojaselkinstiven@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public Usuario findByEmailAndDocument(String email, String document) {
        return null;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> getUserById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteUsuario(Long id) {

    }

    @Override
    public String generateTemporaryPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            password.append(chars.charAt(randomIndex));
        }
        return password.toString();
    }

    @Override
    public String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
}
