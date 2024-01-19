package com.devsuperior.desafiocomponentes.services;

import org.springframework.stereotype.Service;

@Service
public class ShippingService {
    public ShippingService() {
    }

    public double shipment(double amount) {
        if (amount < 100.0) {
            return 20.0;
        } else {
            return amount >= 100.0 && amount < 200.0 ? 12.0 : 0.0;
        }
    }
}
