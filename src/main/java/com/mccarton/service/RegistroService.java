package com.mccarton.service;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.UsuarioEntity;
import com.mccarton.repository.IClienteRepository;
import com.mccarton.repository.IUsuarioRepository;

@Service
public class RegistroService {

	public static final Integer ESTATUS_ACTIVO = 1;
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private IClienteRepository usuarioRepository;

    public void registerUser(ClienteEntity user) throws MessagingException {
        // Código para registrar al usuario

        // Enviar correo de confirmación
    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    	helper.setTo(user.getCorreoElectronico());
        helper.setSubject("Confirmación de registro");
        String htmlContent = "<html><body>" +
        	    "<p>Hola " + user.getNombre() + ",</p>" +
        	    "<p>Gracias por registrarte en nuestra aplicación. Para confirmar tu registro, haz clic en el botón a continuación:</p>" +
        	    "<a href='http://localhost:8090/registro/confirmar/" + user.getIdCliente() + "'><button style='background-color: #008CBA; color: white; padding: 10px;'>Confirmar registro</button></a>" +
        	    "<p>Saludos,<br>El equipo de nuestra aplicación</p>" +
        	    "</body></html>";
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }

    public void confirmRegistration(Integer userId) {
    	Optional<ClienteEntity> usuario = Optional.empty();
    	
    	try {
    		usuario = usuarioRepository.findById(userId);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
    	
    	if (usuario.isPresent()) {
    		usuario.get().setEstatus(ESTATUS_ACTIVO); 
		}
    	
    	
    	try {
    		 usuarioRepository.save(usuario.get());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
    }
	
}
