package com.devsuperior.dsvendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsvendas.dto.SalesSuccessDto;
import com.devsuperior.dsvendas.dto.SalesSunDto;
import com.devsuperior.dsvendas.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
	
	@Query("SELECT new com.devsuperior.dsvendas.dto.SalesSunDto(obj.seller, SUM(obj.amount)) " 
			+ " FROM Sale AS obj GROUP BY obj.seller")
	List<SalesSunDto> amountGroupedBySeller();
	
	@Query("SELECT new com.devsuperior.dsvendas.dto.SalesSuccessDto(obj.seller, SUM(obj.visited), SUM(obj.deals)) " 
			+ " FROM Sale AS obj GROUP BY obj.seller")
	List<SalesSuccessDto> successGropedBySeller();

}
