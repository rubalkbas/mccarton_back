package com.mccarton.service;

import javax.mail.MessagingException;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;

public interface IRegistroService {

	SingleResponse<ClienteEntity> registerUser(ClienteEntity clienteDireccion) throws MessagingException ;
	
	SingleResponse<ClienteEntity> resetPassword(ClienteEntity clienteDireccion) throws MessagingException ;
}
