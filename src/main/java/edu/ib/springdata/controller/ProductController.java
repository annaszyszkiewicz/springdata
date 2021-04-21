package edu.ib.springdata.controller;

import edu.ib.springdata.repository.entity.Product;
import edu.ib.springdata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService products;

    @Autowired
    public ProductController(ProductService products) {
        this.products = products;
    }

    @GetMapping("/product")
    public Optional<Product> getById(@RequestParam Long id) {
        return products.findById(id);
    }

    @GetMapping("/product/all")
    public Iterable<Product> getAll() {
        return products.findAll();
    }

    @PostMapping("/admin/product")
    public Product postById(@RequestBody Product product) {
        return products.save(product);
    }

    @PutMapping("/admin/product")
    public Product putById(@RequestBody Product product) {
        return products.save(product);
    }

    @PatchMapping("/admin/product/{id}")
    public @ResponseBody
    void patchById(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        Product product = products.findById(id).get();
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Product.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, product, v);
        });
        products.save(product);
    }
}
