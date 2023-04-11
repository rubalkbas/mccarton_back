package com.mccarton.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.DireccionEntity;
import com.mccarton.model.entity.UsuarioEntity;
import com.mccarton.service.RegistroService;
import com.mccarton.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"enctype", "Authorization"})
@RequestMapping("/registro")
public class RegistroController {


	@Autowired
    private RegistroService registroService;

    @PostMapping(path = "/cliente", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleResponse<ClienteEntity>> registerUser(@ModelAttribute ClienteEntity user) throws MessagingException {
    	
    	SingleResponse<ClienteEntity> response = new SingleResponse<>();
    	response = registroService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(path = "/password/{contraseña}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleResponse<ClienteEntity>> resetPassword(@ModelAttribute ClienteEntity user, @PathVariable("contraseña") String contraseña) throws MessagingException {
    	
    	SingleResponse<ClienteEntity> response = new SingleResponse<>();
    	response = registroService.confirmPassword(user,contraseña);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/confirmar/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirmRegistration(@PathVariable("idUsuario") String idUsuario) {
    	registroService.confirmRegistration(idUsuario);
        return ResponseEntity.ok().build();
    }
	
}
