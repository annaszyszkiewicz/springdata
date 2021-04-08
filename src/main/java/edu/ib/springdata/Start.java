package edu.ib.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private ProductRepo productRepo;

    @Autowired
    public Start(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @EventListener (ApplicationReadyEvent.class)
    public void runExample(){
        Product product = new Product("Mydło", 10.99F,true);
        productRepo.save(product);
        product = new Product("Szampon", 20.99F,false);
        productRepo.save(product);
        product = new Product("Ręcznik", 50.00F,true);
        productRepo.save(product);
        product = new Product("Pasta do zębów", 10.00F,true);
        productRepo.save(product);
    }
}
