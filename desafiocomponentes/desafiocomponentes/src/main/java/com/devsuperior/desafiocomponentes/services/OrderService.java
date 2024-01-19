package com.devsuperior.desafiocomponentes.services;

import com.devsuperior.desafiocomponentes.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private ShippingService shippingService;

    public OrderService() {
    }

    public double totalOrder(Order order) {
        double discount = order.getBasic() * order.getDiscount() / 100.0;
        double orderWithDiscount = order.getBasic() - discount;
        return orderWithDiscount + this.shippingService.shipment(orderWithDiscount);
    }
}