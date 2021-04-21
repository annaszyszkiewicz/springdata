package edu.ib.springdata.controller;

import edu.ib.springdata.repository.entity.Customer;
import edu.ib.springdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customers;

    @Autowired
    public CustomerController(CustomerService customers) {
        this.customers = customers;
    }

    @GetMapping("/customer")
    public Optional<Customer> getById(@RequestParam Long id) {
        return customers.findById(id);
    }

    @GetMapping("/customer/all")
    public Iterable<Customer> getAll() {
        return customers.findAll();
    }

    @PostMapping("/admin/customer")
    public Customer postById(@RequestBody Customer customer) {
        return customers.save(customer);
    }

    @PutMapping("/admin/customer")
    public Customer putById(@RequestBody Customer customer) {
        return customers.save(customer);
    }

    @PatchMapping("/admin/customer")
    public @ResponseBody
    void patchById(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        Customer customer = customers.findById(id).get();
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Customer.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, customer, v);
        });
        customers.save(customer);
    }
}
