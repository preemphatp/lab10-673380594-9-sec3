package com.example.lab10.controller;

import com.example.lab10.model.Product;
import com.example.lab10.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/products")
public class ProductController {

    // ── Constructor Injection (DIP — SOLID) ─────────────
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ══════════════════════════════════════════════════════
    // ✅ ตัวอย่างที่ทำเสร็จแล้ว — ศึกษาแล้วทำ endpoint ที่เหลือ
    // ══════════════════════════════════════════════════════

    /**
     * GET /products/{id}
     * คืน Mono<Product> — ค้นหา Product 1 รายการ
     *
     * ทดสอบ: GET http://localhost:8080/products/1
     */
    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable String id) {
        return service.getById(id);
    }

    // ══════════════════════════════════════════════════════
    // ❌ TODO: เติม method body ด้านล่างนี้
    // ══════════════════════════════════════════════════════

    /**
     * GET /products
     * คืน Flux<Product> ทุกรายการ
     *
     * ทดสอบ: GET http://localhost:8080/products
     */
    @GetMapping
    public Flux<Product> getAll() {
        return service.getAll();
    }

    /**
     * POST /products
     * รับ Product จาก request body แล้วบันทึก
     *
     * ทดสอบ: POST http://localhost:8080/products
     *        Body: { "name": "...", "price": 999.0, ... }
     */
    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return service.save(product);
    }

    /**
     * DELETE /products/{id}
     * ลบ Product และคืน Mono<Void>
     *
     * ทดสอบ: DELETE http://localhost:8080/products/1
     */
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    /**
     * GET /products/category/{category}
     * คืน Flux<Product> ที่กรองตาม category
     *
     * ทดสอบ: GET http://localhost:8080/products/category/Electronics
     */
    @GetMapping("/category/{category}")
    public Flux<Product> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    /**
     * GET /products/{id}/price
     * คืน Mono<Double> ราคาหลังส่วนลด
     *
     * ทดสอบ: GET http://localhost:8080/products/1/price
     */
    @GetMapping("/{id}/price")
    public Mono<Double> getDiscountedPrice(@PathVariable String id) {
        return service.getDiscountedPrice(id);
    }
}