package com.incottech.Sistema_General.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.incottech.Sistema_General.domain.entity.Usuario;
import com.incottech.Sistema_General.repository.UsuarioRepository;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-11-09
 */

@Service
public class DetalleUsuarioInternoServiceImpl implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String idEmpleado) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.obtenerPorIdEmpleado(idEmpleado, "1")
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el ID: " + idEmpleado));
	
		return DetalleUsuarioImpl.build(usuario);
	}

}
