/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rubén Vazquez Acosta
 *
 */
@Entity
@Table(name = "gen_aux_confirmacion_token", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_token")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = RegUsuarios.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "ID_USUARIO")
    private RegUsuarios usuarios;

    public ConfirmationToken(RegUsuarios usuarios) {
        this.usuarios = usuarios;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    
}
