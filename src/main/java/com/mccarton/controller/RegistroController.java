package com.mccarton.controller;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.UsuarioEntity;
import com.mccarton.service.RegistroService;
import com.mccarton.service.UsuarioService;

@RestController
@RequestMapping("/registro")
public class RegistroController {


	@Autowired
    private RegistroService registroService;

    @PostMapping(path = "/usuario", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerUser(@ModelAttribute ClienteEntity user) throws MessagingException {
    	registroService.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/confirmar/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirmRegistration(@PathVariable("idUsuario") Integer idUsuario) {
    	registroService.confirmRegistration(idUsuario);
        return ResponseEntity.ok().build();
    }
	
}
