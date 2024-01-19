package com.devsuperior.desafiocomponentes;

import com.devsuperior.desafiocomponentes.entities.Order;
import com.devsuperior.desafiocomponentes.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiocomponentesApplication implements CommandLineRunner {
	@Autowired
	private OrderService orderService;

	public DesafiocomponentesApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(DesafiocomponentesApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Order order1 = new Order(1034, 150.0, 20.0);
		Order order2 = new Order(2282, 800.0, 10.0);
		Order order3 = new Order(123, 95.9, 0.0);
		System.out.printf("Pedido %s\nValor total: R$ %.2f", order1.getCode(), this.orderService.totalOrder(order1));
		System.out.println();
		System.out.println();
		System.out.printf("Pedido %s\nValor total: R$ %.2f", order2.getCode(), this.orderService.totalOrder(order2));
		System.out.println();
		System.out.println();
		System.out.printf("Pedido %s\nValor total: R$ %.2f", order3.getCode(), this.orderService.totalOrder(order3));
	}
}