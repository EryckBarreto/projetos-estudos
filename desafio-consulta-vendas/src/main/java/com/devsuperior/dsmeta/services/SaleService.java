package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	private LocalDate dateStringToDate(String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate formattedDate = LocalDate.parse(date, formatter);
		return formattedDate;
	}
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SellerMinDTO> getSummary(String dataInicial, String dataFinal, Pageable pageable) {

		LocalDate maxDate, minDate;

		if (dataFinal.isBlank()) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = dateStringToDate(dataFinal);
		}

		if (dataInicial.isBlank()) {
			minDate = maxDate.minusYears(1);
		} else {
			minDate = dateStringToDate(dataInicial);
		}

		Page<SellerMinDTO> result = repository.searchSummarySellerWithAmount(minDate, maxDate, pageable);
		return result;
	}

	public Page<SaleMinDTO> getReport(String dataInicial, String dataFinal, String name, Pageable pageable) {

		LocalDate maxDate, minDate;

		if (dataFinal.isBlank()) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = dateStringToDate(dataFinal);
		}

		if (dataInicial.isBlank()) {
			minDate = maxDate.minusYears(1);
		} else {
			minDate = dateStringToDate(dataInicial);
		}

		Page<SaleMinDTO> result = repository.searchReportWithSeller(minDate, maxDate, name, pageable);
		return result;
	}



}
