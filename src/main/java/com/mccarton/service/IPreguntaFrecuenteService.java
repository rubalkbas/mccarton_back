package com.mccarton.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.PreguntaFrecuente;

public interface IPreguntaFrecuenteService {

	SingleResponse<List<PreguntaFrecuente>>consultaPreguntaFrecuentes();
	SingleResponse<PreguntaFrecuente> guardarPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente);
	SingleResponse<PreguntaFrecuente>actualizarPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente);
	SingleResponse<PreguntaFrecuente>eliminarPreguntaFrecuente(Integer idPreguntaFrecuente);
	SingleResponse<PreguntaFrecuente> actualizarEstatusPreguntaFrecuente(Integer idPreguntaFrecuente,Integer estatus);
	SingleResponse<List<PreguntaFrecuente>> consultarPreguntasFrecuentesActivas();
	SingleResponse<Page<PreguntaFrecuente>> consultarPorPaginas(Integer numeroPagina, Integer tamanioPagina,
	String campo, String campoBusqueda,String direccion);
}
