package com.hakaton.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCredenciales(String destinatario, String contrasena) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Tus credenciales para el sistema de votación");
        mensaje.setText("Tu contraseña generada es: " + contrasena + "\nPor favor, inicia sesión en el sistema con tu DNI y esta contraseña.");
        mailSender.send(mensaje);
    }
}
