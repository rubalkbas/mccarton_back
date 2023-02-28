package com.incottech.Sistema_General.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Getter
@Setter
@NoArgsConstructor
public class CategoriaPadreHija implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private boolean hasSubCategory;
	private int parentId;

}
