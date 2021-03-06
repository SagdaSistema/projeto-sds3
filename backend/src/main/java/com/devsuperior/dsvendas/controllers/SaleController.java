package com.devsuperior.dsvendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsvendas.dto.SaleDto;
import com.devsuperior.dsvendas.dto.SalesSuccessDto;
import com.devsuperior.dsvendas.dto.SalesSunDto;
import com.devsuperior.dsvendas.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	private SaleService service;
	
	@GetMapping
	public ResponseEntity<Page<SaleDto>> findAll(Pageable pageable){
		Page<SaleDto> list = service.findAll(pageable);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/sum-by-seller")
	public ResponseEntity<List<SalesSunDto>> amountGroupedBySeller(){
		List<SalesSunDto> list = service.amountGroupedBySeller();
		return ResponseEntity.ok(list);
	}
	@GetMapping(value = "/succes-by-seller")
	public ResponseEntity<List<SalesSuccessDto>> successGroupedBySeller(){
		List<SalesSuccessDto> list = service.successGroupedBySeller();
		return ResponseEntity.ok(list);
	}

}
