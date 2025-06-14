package ecommerceBESB.ecommerce.Orders.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ecommerceBESB.ecommerce.Errors.Exceptions.OrderNotFoundException;
import ecommerceBESB.ecommerce.Orders.Order;
import ecommerceBESB.ecommerce.Orders.Repositories.OrderRepository;
import ecommerceBESB.ecommerce.Orders.Requests.OrderSaveRequest;
import ecommerceBESB.ecommerce.Products.Repositories.ProductRepository;
import ecommerceBESB.ecommerce.User.Repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAllOrders();
    }

    public List<Order> getOrderByUserId(UUID id){
        return orderRepository.findOrderByUserId(id);
    }

    public Order makeNewOrder(OrderSaveRequest orderReq){
        Order order = Order.builder()
            .product(productRepository.findProductById(orderReq.productId()).orElseThrow(() -> new OrderNotFoundException("Order Not found! (Invalid Product!)")))
            .user(userRepository.findUserById(orderReq.userId()).orElseThrow(() -> new OrderNotFoundException("Order Not found! (Invalid User!)")))
            .produtoId(orderReq.productId().toString())
            .usuarioId(orderReq.userId().toString())
            .build();
        return orderRepository.save(order);
    }

    public String deleteOrder(UUID id) throws OrderNotFoundException{
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return "Order Deleted";
        }else{
            throw new OrderNotFoundException("Order Not found!");
        }
    }
}


