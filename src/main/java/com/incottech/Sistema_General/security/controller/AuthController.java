package com.incottech.Sistema_General.security.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.domain.entity.ConfirmationToken;
import com.incottech.Sistema_General.domain.entity.RegUsuarios;
import com.incottech.Sistema_General.domain.entity.Usuario;
import com.incottech.Sistema_General.domain.entity.UsuariosInfo;
import com.incottech.Sistema_General.model.ParametrosRegistroUsuario;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.model.SignupRequest;
import com.incottech.Sistema_General.payload.request.LoginRequest;
import com.incottech.Sistema_General.payload.response.JwtResponse;
import com.incottech.Sistema_General.repository.RegUsuarioRepository;
import com.incottech.Sistema_General.repository.UsuarioRepository;
import com.incottech.Sistema_General.repository.UsuariosInfoRepository;
import com.incottech.Sistema_General.security.jwt.JwtUtils;
import com.incottech.Sistema_General.security.repository.ConfirmationTokenRepository;
import com.incottech.Sistema_General.security.service.DetalleUsuarioImpl;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-11-09
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authentication")
public class AuthController {
	

	@Value("${sistemaglobal.app.pathMicroServ}")
	private String pathMicroServ;
	
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RegUsuarioRepository regUsuarioRepository;
	
	@Autowired
	UsuariosInfoRepository usuariosInfoRepository;

//	@Autowired
//	PerfilRepository perfilRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	
	/////////////////////////
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

	///////////////////////////
	@PersistenceContext // or even @Autowired
    private EntityManager entityManager;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		RegUsuarios regusuario = new RegUsuarios();
		Respuesta response = new Respuesta();
		
		regusuario = regUsuarioRepository.usuarioHabilitado(loginRequest.getCorreo());		
		
		if(regusuario == null) {
			
			response.setMensaje("El correo aún no ha sido Registrado!!!");
			response.setValor("0");
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}else if(regusuario.getConfirmaCorreo() == 0) {
			
			response.setMensaje("El correo aún no ha sido confirmado!!!");
			response.setValor("1");
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}else if(regusuario.getConfirmaCorreo() == 1){
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getPass()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			DetalleUsuarioImpl userDetails = (DetalleUsuarioImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt, 
													 userDetails.getId(), 
													 userDetails.getUsername(), 
													 userDetails.getEmail(),
													 roles));			
			
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);		
		
	}

//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@RequestBody ParametrosRegistroUsuario parametros) throws MessagingException, IOException {
//		
//		RegUsuarios regusuario = new RegUsuarios();
//		Respuesta response = new Respuesta();
//		
//		regusuario = regUsuarioRepository.usuarioHabilitado(parametros.getEmail());	
//		
//		if (regusuario != null) {
//			
//			response.setMensaje("El correo ya ha sido registrado!!!");
//			response.setValor("9");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//			
//		}
//
//		// Create new user's account
//		RegUsuarios user = new RegUsuarios(new String(parametros.getUsername()), 
//													  parametros.getEmail(),
//							           encoder.encode(parametros.getPassword()));
//
//		//set de estatus
//		user.setEstatus("1");
//		//set fecha actual alta
//		Date fechaActual = new Date();		
//		user.setFechaAlta(fechaActual);
//		
//		//concatena el nombre del usuario
//		user.setNombreUsuario(parametros.getUsername() + " " + parametros.getApePa() + " " + parametros.getApeMa());
//		
//		//primer paso se guarda en tabla usuarios
//		regUsuarioRepository.save(user);
//		
//		//se recupera el id del usuario guardardo 
//		Long x = user.getId();	
//		//instancia de la nueva entidad creda por mau
//		UsuariosInfo infoUser = new UsuariosInfo();
//
//		//set del id recuperado guardado 
//		infoUser.setIdUsuario(x);
//		
//		//aqui van los demas set del formulario
//		infoUser.setNombreUsuario(parametros.getUsername());
//		infoUser.setApePa(parametros.getApePa());
//		infoUser.setApeMa(parametros.getApeMa());
//		infoUser.setEstado(parametros.getEstado());
//		infoUser.setMunicipio(parametros.getMunicipio());
//		infoUser.setLocalidad(parametros.getLocalidad());
//		infoUser.setTipoZona(parametros.getTipoZona());
//		infoUser.setCodigoPostal(parametros.getCodigoPostal());
//		infoUser.setCalle(parametros.getCalle());
//		infoUser.setNumeroExterior(parametros.getNumeroExterior());
//		infoUser.setNumeroInterior(parametros.getNumeroInterior());
//		
//		infoUser.setTelefono(parametros.getTelefono());
//		infoUser.setIdentificacion(parametros.getIdentificacion());
//		infoUser.setNotas(parametros.getNotas());
//		infoUser.setFechaAlta(fechaActual);
//		infoUser.setEstatus("1");
//		
//		//se guarda en la segunda entidad creada por mau
//		usuariosInfoRepository.save(infoUser);
//
//		ConfirmationToken confirmationToken = new ConfirmationToken(user);
//		
//		confirmationTokenRepository.save(confirmationToken);
//		
//		//envioSimple(signUpRequest.getEmail()); // correo normal
//		envioHtml(parametros.getEmail(),confirmationToken.getConfirmationToken()); // correo en html
//		response.setMensaje("Usuario Registrado Correctamente!");
//		return ResponseEntity.ok(response);
//	}
	
	
	@PostMapping("/signup/registro")
	public ResponseEntity<?> registerUserExterno(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, IOException {
		Respuesta response = new Respuesta();
		// Create new user's account
		RegUsuarios user = new RegUsuarios(new String(signUpRequest.getUsername()), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		//poner de inicio el estatus del usuario activo
		user.setEstatus("1");
 
		//instancia de la nueva entidad creda por mau
		UsuariosInfo infoUser = new UsuariosInfo();
		
		//fecha actual
		Date fechaActual = new Date();
//		String strDateFormat = "dd-MMM-yyyy";
//		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);	
//		objSDF.format(fechaActual);//		
//		user.setFechaAlta(objSDF.format(fechaActual));
		
		//set para guardar la fecha actual
		user.setFechaAlta(fechaActual);   
		
		//guardamos en tabla usuarios
		regUsuarioRepository.save(user);
		
		//recuperamos el id que se creo al guardar usuarios
		Long x = user.getId();		
		
		//set para guardar el id del usuario recuperado
		infoUser.setIdUsuario(x);
		
		//guardar en tabla de usuarios info
		usuariosInfoRepository.save(infoUser);
		
		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		
		confirmationTokenRepository.save(confirmationToken);
		
		//envioSimple(signUpRequest.getEmail()); // correo normal
		envioHtml(signUpRequest.getEmail(),confirmationToken.getConfirmationToken()); // correo en html
		response.setMensaje("Usuario Registrado Correctamente!");
		return ResponseEntity.ok(response);
	}
	
//	//correo normal
//	void envioSimple(String correo) {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        
//        msg.setTo("eduardo.0702.nunez@gmail.com");
//
//        msg.setSubject("Testing from Spring Boot");
//        msg.setText("Hello World \n Spring Boot Email");
//        
//        javaMailSender.send(msg);
//
//    }
//	
	//correo html
	void envioHtml(String correo, String token) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(correo); // cambiar a que entre el correo personal solamente
       // helper.setTo("eduardo.0702.nunez@gmail.com"); // correo propio para prubas si no cambiar estatus confirmacion en BD

        helper.setSubject("Completa tu Registro!!!");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("Para confirmar su cuenta, haga clic aquí: "
        		+ pathMicroServ + "/api/auth/confirmar-cuenta?token="+token , true);

        

        javaMailSender.send(msg);

    }
	@PostMapping("/confirmar-cuenta")
    public void confirmUserAccount(@RequestParam("token")String confirmationToken,HttpServletResponse resp) throws IOException
    {
        ConfirmationToken token = new ConfirmationToken();
        token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
        	RegUsuarios user = regUsuarioRepository.findByCorreoIgnoreCase(token.getUsuarios().getCorreo());
            user.setConfirmaCorreo(1);
            regUsuarioRepository.save(user);
            
            String url = "http://104.192.6.36:7070/eCommerceFront/#" +  "/sign-in";
            resp.sendRedirect(url);
            
          //  modelAndView.setViewName("accountVerified");
        }
        else
        {
           // modelAndView.addObject("message","The link is invalid or broken!");
            //modelAndView.setViewName("error");
        }

       
    }
	
	
}
