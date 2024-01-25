package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO(obj.seller.name, sum(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE date BETWEEN :dataInicial AND :dataFinal " +
            "GROUP BY obj.seller.name ")
    Page<SellerMinDTO> searchSummarySellerWithAmount(LocalDate dataInicial, LocalDate dataFinal, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.amount, obj.date, obj.seller.name) "
            + "FROM Sale obj "
            + "WHERE date between :minDate AND :maxDate "
            + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name , '%')) "
            + "GROUP BY obj.id ")
    Page<SaleMinDTO> searchReportWithSeller(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
