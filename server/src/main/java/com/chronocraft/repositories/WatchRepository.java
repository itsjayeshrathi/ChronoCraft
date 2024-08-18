package com.chronocraft.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chronocraft.entities.Watch;
@Repository
public interface WatchRepository extends JpaRepository<Watch, Integer> {
	List<Watch> findByCategoryId(int category);
//	Optional<Watch> findById(int id);
	List<Watch> findAllByOrderByPriceAsc();
	List<Watch> findAllByOrderByPriceDesc();
	@Query("SELECT w FROM Watch w WHERE LOWER(w.brand) LIKE LOWER(CONCAT('%', :brand, '%'))")
	List<Watch> findByTitleContainingIgnoreCase(String brand);
	//List<Watch> findByBrandContainingIgnoreCase(@Param("brand") String brand);
	
}
