package com.miranda.inventory_service.service;

import com.miranda.inventory_service.entity.Product;
import com.miranda.inventory_service.repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    private final ProductRepository repository;

    public InventoryService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void reserve(Integer productId) {
        String thread = Thread.currentThread().getName();

        try {
            log.info("[{}] Início da reserva", thread);

            Product product = repository.findById(productId)
                    .orElseThrow();

            log.info("[{}] Estoque lido = {}", thread, product.getQuantity());

            if (product.getQuantity() <= 0) {
                log.warn("[{}] Sem estoque!", thread);
                throw new RuntimeException("Sem estoque");
            }

            sleep(1100);

            product.setQuantity(product.getQuantity() - 1);

            log.info("[{}] Estoque após decremento = {}", thread, product.getQuantity());

            repository.saveAndFlush(product);

            log.info("[{}] Reserva concluída", thread);

        } catch (Exception e) {
            log.error("[{}] Erro ao reservar estoque", thread, e);
            throw e;
        }
    }


    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
