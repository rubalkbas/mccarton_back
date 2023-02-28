package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.incottech.Sistema_General.domain.entity.ImgBanner;
/**
 * @author Mauricio Soto
 * @version 1.0
 * @since   2020-12-28
 */

@Repository
public interface BannerRepository extends JpaRepository< ImgBanner , Long > {

	@Query(value = "select estatus from bdgeneral.gen_aux_img_banner where id_img_banner = :idImgBanner", nativeQuery = true)
	 String getEstatusImgBanner(@Param("idImgBanner") int id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update bdgeneral.gen_aux_img_banner set estatus = 0 WHERE id_img_banner = :idImgBanner", nativeQuery = true)
	void editEstatusInactivo(@Param("idImgBanner") int id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update bdgeneral.gen_aux_img_banner set estatus = 1 WHERE id_img_banner = :idImgBanner", nativeQuery = true)
	void editEstatusActivo(@Param("idImgBanner") int id);
	
	@Query(value = "select * from bdgeneral.gen_aux_img_banner where id_img_banner = :idImgBanner", nativeQuery = true)
	 ImgBanner getAllImgBanner(@Param("idImgBanner") Long id);
	
	/**@Query(value = "select * from bdgeneral.gen_aux_img_banner where estatus = 1 ", nativeQuery = true)
	List<ImgBanner> findActivos();**/
	
	@Query(value = "SELECT * FROM gen_aux_img_banner WHERE estatus = 1 ORDER BY RAND() LIMIT 6;", nativeQuery = true)
	List <ImgBanner> consultaProductosAleatorios();
}
