package edu.ib.springdata;

import edu.ib.springdata.repository.CustomerRepository;
import edu.ib.springdata.repository.OrderRepository;
import edu.ib.springdata.repository.ProductRepository;
import edu.ib.springdata.repository.UserDtoRepository;
import edu.ib.springdata.repository.entity.Customer;
import edu.ib.springdata.repository.entity.Order;
import edu.ib.springdata.repository.entity.Product;
import edu.ib.springdata.repository.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbMockData {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private UserDtoRepository userDtoRepository;

    @Autowired
    public DbMockData(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository, UserDtoRepository userDtoRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.userDtoRepository = userDtoRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {

        Iterable customers = customerRepository.findAll();
        if (!customers.iterator().hasNext()) {
            Customer customer = new Customer("Jan Kowalski", "Wroclaw");
            customerRepository.save(customer);
        }

        Iterable productsList = productRepository.findAll();
        if (!productsList.iterator().hasNext()) {
            Product product = new Product("Korek", 2.55f, true);
            Product product1 = new Product("Rura", 5f, true);
            Set<Product> products = new HashSet<>() {
                {
                    add(product);
                    add(product1);
                }
            };
            productRepository.save(product);
            productRepository.save(product1);
        }

        Iterable orders = orderRepository.findAll();
        if (!orders.iterator().hasNext()) {
            Customer customer2 = new Customer("Kamil Kowal", "Gdynia");
            customerRepository.save(customer2);
            Product product2 = new Product("Pasek", 1.55f, true);
            Set<Product> products = new HashSet<>() {
                {
                    add(product2);
                }
            };
            productRepository.save(product2);
            Order order = new Order(customer2, products, LocalDateTime.now(), "in progress");
            orderRepository.save(order);
        }

        Iterable users = userDtoRepository.findAll();
        if (!users.iterator().hasNext()) {
            UserDtoBuilder userBuilder = new UserDtoBuilder();

            User user1 = new User("Jankowalski", "pass123", "ROLE_CUSTOMER");
            UserDto userCustomer = userBuilder.setUser(user1);
            userDtoRepository.save(userCustomer);

            User user2 = new User("admin", "passAdmin", "ROLE_ADMIN");
            UserDto userAdmin = userBuilder.setUser(user2);
            userDtoRepository.save(userAdmin);

        }


    }
}
