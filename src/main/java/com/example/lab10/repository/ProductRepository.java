package com.example.lab10.repository;

import com.example.lab10.model.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepository {

    // ── In-memory storage ────────────────────────────────
    private final Map<String, Product> store = new ConcurrentHashMap<>();

    // ── Constructor: ใส่ข้อมูลตัวอย่าง ──────────────────
    public ProductRepository() {
        store.put("1", new Product("1", "iPhone 15 Pro (673380594-9 SEC 3)",
                "Electronics", "Apple", 50, 39900.0, "MEMBER"));
        store.put("2", new Product("2", "MacBook Air M3",
                "Electronics", "Apple", 20, 49900.0, "NONE"));
        store.put("3", new Product("3", "Samsung Galaxy S24",
                "Electronics", "Samsung", 30, 29900.0, "SEASONAL"));
    }

    // ── 1. หา Product 1 รายการ ───────────────────────────
    
    public Mono<Product> findById(String id) {
        Product product = store.get(id);
        return product != null ? Mono.just(product) : Mono.empty();
    }

    // ── 2. หา Product ทั้งหมด ────────────────────────────
  
    public Flux<Product> findAll() {
        return Flux.fromIterable(store.values());
    }

    // ── 3. บันทึก Product ────────────────────────────────
   
    public Mono<Product> save(Product product) {
        store.put(product.getId(), product);
        return Mono.just(product);
    }

    // ── 4. ลบ Product ────────────────────────────────────

    public Mono<Void> deleteById(String id) {
        store.remove(id);
        return Mono.empty();
    }

    // ── 5. กรองตาม category ──────────────────────────────
   
    public Flux<Product> findByCategory(String category) {
        return findAll()
                .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category));
    }
}