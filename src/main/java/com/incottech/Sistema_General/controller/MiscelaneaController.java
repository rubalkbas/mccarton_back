package com.incottech.Sistema_General.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.model.Contacto;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/miscelanea")
public class MiscelaneaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MiscelaneaController.class);
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	 
	//Correo de contacto
	@PostMapping("/emailContacto")
	public void emailContacto(@RequestBody Contacto contacto
			) 
					throws MessagingException, IOException
	{
		LOGGER.info("Entra a controller para enviar correo de contacto.");
		
        MimeMessage msg = javaMailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
              
        helper.setFrom(mailUsername);
           
        helper.setTo(mailUsername);

        helper.setSubject("Urgente: Correo de contacto");
        
        String imgLogo = "http://104.192.6.36:81/asdk/imagesGlobal/1612816317005ecommerce_Inco_oscuro.png";
        
        
        helper.setText( "<!DOCTYPE>\r\n" + 
        		"<html>\r\n" + 
        		"<head>\r\n" + 
        		"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
        		"  <style type=\"text/css\">\r\n" + 
        		"  body {margin: 0; padding: 0; min-width: 100%!important;}\r\n" + 
        		"  img {height: auto;}\r\n" + 
        		"  .content {width: 100%; max-width: 600px;}\r\n" + 
        		"  .header {padding: 40px 30px 20px 30px;}\r\n" + 
        		"  .innerpadding {padding: 30px 30px 30px 30px;}\r\n" + 
        		"  .borderbottom {border-bottom: 1px solid #05AFF2;}\r\n" + 
        		"  .subhead {font-size: 15px; color: #ffffff; font-family: sans-serif; letter-spacing: 10px;}\r\n" + 
        		"  .h1, .h2, .bodycopy {color: #153643; font-family: sans-serif;}\r\n" + 
        		"  .h1 {font-size: 33px; line-height: 38px; font-weight: bold;}\r\n" + 
        		"  .h2 {padding: 0 0 15px 0; font-size: 24px; line-height: 28px; font-weight: bold;}\r\n" + 
        		"  .bodycopy {font-size: 16px; line-height: 22px;}\r\n" + 
        		"  .button {text-align: center; font-size: 18px; font-family: sans-serif; font-weight: bold; padding: 0 30px 0 30px;}\r\n" + 
        		"  .button a {color: #ffffff; text-decoration: none;}\r\n" + 
        		"  .footer {padding: 20px 30px 15px 30px;}\r\n" + 
        		"  .footercopy {font-family: sans-serif; font-size: 14px; color: #ffffff;}\r\n" + 
        		"  .footercopy a {color: #ffffff; text-decoration: underline;}\r\n" + 
        		"  \r\n" + 
        		"  @media only screen and (max-width: 550px), screen and (max-device-width: 550px) {\r\n" + 
        		"  body[yahoo] .hide {display: none!important;}\r\n" + 
        		"  body[yahoo] .buttonwrapper {background-color: transparent!important;}\r\n" + 
        		"  body[yahoo] .button {padding: 0px!important;}\r\n" + 
        		"  body[yahoo] .button a {background-color: #e05443; padding: 15px 15px 13px!important;}\r\n" + 
        		"  body[yahoo] .unsubscribe {display: block; margin-top: 20px; padding: 10px 50px; background: #2f3942; border-radius: 5px; text-decoration: none!important; font-weight: bold;}\r\n" + 
        		"  }\r\n" + 
        		"\r\n" + 
        		"  </style>\r\n" + 
        		"</head>\r\n" + 
        		"\r\n" + 
        		"<body yahoo bgcolor=\"#EEF9FF\">\r\n" + 
        		"<table width=\"100%\" bgcolor=\"#EEF9FF\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
        		"<tr>\r\n" + 
        		"  <td>   \r\n" + 
        		"    <table bgcolor=\"#ffffff\" class=\"content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
        		"      <tr>\r\n" + 
        		"        <td bgcolor=\"#05AFF2\" class=\"header\">\r\n" + 
        		"\r\n" + 
        		"          <table class=\"col425\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; max-width: 425px;\">  \r\n" + 
        		"            <tr>\r\n" + 
        		"              <td  style=\"padding: 0 20px 20px 0;\">\r\n" + 
        		"                <img class=\\\"fix\\\" src=\""+imgLogo+"\" border=\"0\" alt=\"\" />\r\n" + 
        		"              </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"			<tr>\r\n" + 
        		"              <td height=\"70\">\r\n" + 
        		"                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
        		"                  <tr>\r\n" + 
        		"                    <td class=\"subhead\" style=\"padding: 0 0 0 3px;\">\r\n" + 
        		"                      URGENTE\r\n" + 
        		"                    </td>\r\n" + 
        		"                  </tr>\r\n" + 
        		"                  <tr>\r\n" + 
        		"                    <td class=\"h1\" style=\"padding: 5px 0 0 0;\">\r\n" + 
        		"                      CORREO DE CONTACTO\r\n" + 
        		"                    </td>\r\n" + 
        		"                  </tr>\r\n" + 
        		"                </table>\r\n" + 
        		"              </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"          </table>\r\n" + 
        		"        </td>\r\n" + 
        		"      </tr>\r\n" + 
        		"      <tr>\r\n" + 
        		"        <td class=\"innerpadding borderbottom\">\r\n" + 
        		"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
        		"            <tr>\r\n" + 
        		"              <td class=\"h2\">\r\n" + 
        		"                Alguien intento comunicarse\r\n" + 
        		"              </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"            <tr>\r\n" + 
        		"              <td class=\"bodycopy\">\r\n" + 
        		"				 "+contacto.getMessage()+" \r\n" + 
        		"			  </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"          </table>\r\n" + 
        		"        </td>\r\n" + 
        		"      </tr>\r\n" + 
        		"      <tr>\r\n" + 
        		"        <td class=\"innerpadding borderbottom\">\r\n" + 
        		"          <table class=\"col380\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; max-width: 380px;\">  \r\n" + 
        		"            <tr>\r\n" + 
        		"              <td>\r\n" + 
        		"                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
        		"                  <tr>\r\n" + 
        		"                    <td class=\"bodycopy\">\r\n" + 
        		"                      Datos de contacto\r\n" + 
        		"                    </td>\r\n" + 
        		"                  </tr>\r\n" + 
        		"                  <tr>\r\n" + 
        		"                    <td style=\"padding: 20px 0 0 0;\">\r\n" + 
        		"                      <table>\r\n" + 
        		"                        <tr> <td>Nombre:</td> <td> "+contacto.getName()+" </td> <tr>\r\n" + 
        		"						<tr> <td>E-mail:</td> <td> "+contacto.getEmail()+" </td> <tr>\r\n" + 
        		"						<tr> <td>Telefono:</td> <td> "+contacto.getPhone()+" </td> <tr>\r\n" + 
        		"\r\n" + 
        		"                      </table>\r\n" + 
        		"                    </td>\r\n" + 
        		"                  </tr>\r\n" + 
        		"                </table>\r\n" + 
        		"              </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"          </table>\r\n" + 
        		"\r\n" + 
        		"        </td>\r\n" + 
        		"      </tr>\r\n" + 
        		"      <tr>\r\n" + 
        		"        <td class=\"footer\" bgcolor=\"#44525f\">\r\n" + 
        		"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
        		"            <tr>\r\n" + 
        		"              <td align=\"center\" class=\"footercopy\">\r\n" + 
        		"                &reg; Copyright © 2021 All Rights Reserved<br/>\r\n" + 
        		"              </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"          </table>\r\n" + 
        		"        </td>\r\n" + 
        		"      </tr>\r\n" + 
        		"    </table>\r\n" + 
        		"    </td>\r\n" + 
        		"  </tr>\r\n" + 
        		"</table>\r\n" + 
        		"</body>\r\n" + 
        		"</html>", true );

        javaMailSender.send(msg);
        
        LOGGER.info("Sale de controller para enviar correo de contacto.");
   
	}


}
