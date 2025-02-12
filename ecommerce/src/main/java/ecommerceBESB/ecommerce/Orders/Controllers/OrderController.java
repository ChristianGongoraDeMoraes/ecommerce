package ecommerceBESB.ecommerce.Orders.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBESB.ecommerce.Orders.Order;
import ecommerceBESB.ecommerce.Orders.Requests.OrderSaveRequest;
import ecommerceBESB.ecommerce.Orders.Services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Api/Order")
@Transactional
public class OrderController {
    private final OrderService orderService;
    
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderByUserId(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByUserId(id));
    }
    

    @PostMapping
    public ResponseEntity<Order> makeNewOrder(@RequestBody OrderSaveRequest orderReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.makeNewOrder(orderReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrder(id));
    }
}

