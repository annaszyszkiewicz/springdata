package edu.ib.springdata.controller;

import edu.ib.springdata.repository.entity.Order;
import edu.ib.springdata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderService orders;

    @Autowired
    public OrderController(OrderService orders) {
        this.orders = orders;
    }

    @GetMapping("/order")
    public Optional<Order> getById(@RequestParam Long id) {
        return orders.findById(id);
    }

    @GetMapping("/order/all")
    public Iterable<Order> getAll() {
        return orders.findAll();
    }

    @PostMapping("/admin/order")
    public Order postById(@RequestBody Order order) {
        return orders.save(order);
    }

    @PutMapping("/admin/order")
    public Order putById(@RequestBody Order order) {
        return orders.save(order);
    }

    @PatchMapping("/admin/order/{id}")
    public @ResponseBody
    void patchById(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        Order order = orders.findById(id).get();
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Order.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, order, v);
        });
        orders.save(order);
    }
}
