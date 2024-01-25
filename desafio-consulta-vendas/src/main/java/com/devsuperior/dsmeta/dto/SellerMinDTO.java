package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

import java.util.List;

public class SellerMinDTO {

    private String name;
    private Double amount;

    public SellerMinDTO(String name, Double total) {
        this.name = name;
        this.amount = total;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "SellerMinDTO{" +
                "name='" + name + '\'' +
                ", total=" + amount +
                '}';
    }
}
