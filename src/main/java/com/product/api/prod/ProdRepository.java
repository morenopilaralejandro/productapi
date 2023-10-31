package com.product.api.prod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdRepository extends JpaRepository<Prod, Long> {

	List<Prod> findByCatId(Long catId);
	
}
